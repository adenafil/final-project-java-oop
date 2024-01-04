package ade.animelist.api;

import ade.animelist.database.DatabaseConnection;
import ade.animelist.database.repository.ConfigRepository;
import ade.animelist.database.repository.ConfigRepositoryImpl;
import ade.animelist.util.ImageRenderer;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.Expiry;
import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.cache.JikanCache;
import net.sandrohc.jikan.exception.JikanQueryException;
import net.sandrohc.jikan.model.anime.Anime;
import net.sandrohc.jikan.model.anime.AnimeStatus;
import net.sandrohc.jikan.model.common.Genre;
import net.sandrohc.jikan.model.enums.AgeRating;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.OffsetDateTime;
import java.util.*;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;

public class JikanAPI {
    // an api object from library -> https://github.com/SandroHc/reactive-jikan
    private static Jikan jikan;
    // activated jikanCache for a better perform
    private static JikanCache jikanCache;

    // i activated all of the feature on the below statement
    // everything i got from docs in https://github.com/SandroHc/reactive-jikan
    // thanks for the developer, so i dont have fetch api manually with java OkHTTP
    static {
        jikanCache = new JikanCache() {
            protected final Cache<String, JikanValueHolder> cache = Caffeine.newBuilder().maximumSize(10_000).expireAfter(new JikanExpiry()).build();

            public void put(String key, Object value, OffsetDateTime expires) {
                cache.put(key, new JikanValueHolder(value, expires));
            }

            public Optional<Object> get(String key) {
                return Optional.ofNullable(cache.getIfPresent(key)).map(holder -> holder.value);
            }

            class JikanValueHolder {
                public final Object value;
                public final long expireTime;

                public JikanValueHolder(Object value, OffsetDateTime expires) {
                    this.value = value;
                    this.expireTime = TimeUnit.SECONDS.toNanos(expires.toEpochSecond());
                }
            }

            class JikanExpiry implements Expiry<String, JikanValueHolder> {
                public long expireAfterCreate(String key, JikanValueHolder value, long currentTime) {
                    return value.expireTime;
                }

                public long expireAfterUpdate(String key, JikanValueHolder value, long currentTime, long currentDuration) {
                    return currentDuration; // Do not modify expire date
                }

                public long expireAfterRead(String key, JikanValueHolder value, long currentTime, long currentDuration) {
                    return currentDuration; // Do not modify expire date
                }
            }
        };

        jikan = new Jikan.JikanBuilder().cache(jikanCache).build();
    }

    /**
     * This method is used to look for anime by title
     * @param title => i.g : 'naruto', 'dr. stone', 'demon slayer' and etc
     * @return list of all anime that got from api
     * @throws JikanQueryException
     */
    public static List<Anime> searchAnimeByTitle(String title) throws JikanQueryException {
//        jikan = new Jikan();

//        List<Anime> searchResult = jikan.query().anime().search().query(title).execute().collectList().block();

        return jikan.query().anime().search().rating(AgeRating.PG13).query(title).execute().collectList().block();
    }

    /**
     * method for creating data model
     * @param animeList (an list of result that is got by api)
     * @return object[][] to make model on table
     * @throws IOException
     */

    /**
     * this method is depreceted, im not gonna deleted for now untill
     * my project beeen finised by myself, so yeah the probelm of this method is
     * it took so long to create ImageIcon, that is why the solution that i got is to
     * parallel all that imageIcon been created.
     * @param animeList
     * @return a data model objek
     * @throws IOException
     */
    @Deprecated
    public static Object[][] animeToDataTable(List<Anime> animeList) throws IOException {
        int totalAnime = animeList.size();
        Object[][] result = new Object[totalAnime][6];
        int i = 0;
        for (Anime anime : animeList) {
            result[i][0] = anime.malId;
            result[i][1] = ImageRenderer.createImageIconByURL(anime.images.jpg.largeImageUrl);
            result[i][2] = anime.title;
            result[i][3] = anime.score;
            result[i][4] = anime.rating;
            result[i][5] = anime.episodes;
            i++;
        }

        return result;
    }

    /**
     * this is a new solution that i got when stuck from bad perform .
     * honestly i've ever thought that will use lazy way in java
     * but i got confused so i looked for in google, ai or anywhere
     * about how to java asnyc, parallel and concurrent work
     * @param animeList
     * @return an list of data[] model
     */
    public static List<Object[]> animeToDataTableNew(List<Anime> animeList) {
        List<CompletableFuture<Object[]>> futures = new ArrayList<>();

        for (Anime anime : animeList) {
            CompletableFuture<Object[]> future = CompletableFuture.supplyAsync(() -> {
                ImageIcon imageIcon = ImageRenderer.createImageIconByURL(anime.images.jpg.largeImageUrl);
//                    System.out.println(anime.title);
                return new Object[]{
                        anime.malId,
                        imageIcon,
                        anime.title,
                        anime.score,
                        anime.rating,
                        anime.episodes
                };
            });

            futures.add(future);
        }

        // Combine all CompletableFuture into one CompletableFuture representing all of them
        CompletableFuture<Void> allOf = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));

        // Wait for all CompletableFuture to complete
        try {
            allOf.get(); // This will block until all tasks are completed
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        // Retrieve the results from CompletableFuture
        List<Object[]> resultList = futures.stream()
                .map(future -> {
                    try {
                        return future.get(); // Get the result from each CompletableFuture
                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                        return null;
                    }
                })
                .filter(result -> result != null)
                .toList();

        return resultList;
    }


//    public static int lengthAnimeResultOfAPI(List<Anime> animeList) {
//        int total = 0;
//        for (Anime anime : animeList) {
//            total++;
//        }
//        return total;
//    }

//    private static ImageIcon createImageIconByURL(String imageURL) throws IOException {
//        // Implementasi createImageIconByURL
//        // Pastikan bahwa ini sudah dioptimalkan sebaik mungkin
//        Image image = ImageIO.read(new URL(imageURL));
//        if (image != null) return new ImageIcon(image);
//        throw new IOException("Failed to read image from URL: " + imageURL);
//    }

    /**
     * this method is used for getting anime by the id of MAL(myAnimeList website)
     * i dont use a subcribe method for this
     * @param id => (an id from official MAL)
     * @return () => an object Anime
     * @throws JikanQueryException
     */
    public static Anime getAnimeById(int id) throws JikanQueryException {
        return jikan.query().anime().get(id).execute().block();
    }

    public static List<Anime> getTitleAndImageAnimeBySearch(String title) throws JikanQueryException {
        List<Anime> results = jikan.query().anime().search().rating(AgeRating.PG13)
                .query(title)
                .limit(1)
                .execute()
                .collectList()
                .block();

//        jikan.query().anime().search().rating(AgeRating.PG13).query()

        return results;
    }

    public static Mono<List<Anime>> getTitleAndImageAnimeBySearchAsync(String title) throws JikanQueryException {
        return jikan.query().anime().search().rating(AgeRating.PG13)
                .query(title)
                .execute()
                .collectList();
    }

    public static Mono<List<Anime>> getTopAnime() throws JikanQueryException {
        return jikan.query().anime().top()
                .limit(10)
                .execute()
                .collectList();
    }

//    public static Mono<List<Anime>> getRecommendationAnimeAsync() {
//        return Flux.range(0, 5)
//                .flatMap(i -> Mono.fromCallable(() -> jikan.query().random().anime().execute()))
//                .flatMap(animeMono -> Mono.justOrEmpty(animeMono.block())) // Extract the Anime from Mono
//                .collectList();
//    }

    public static Mono<List<Anime>> getRecommendationAnimeAsync() {
        Jikan noCachingDek = new Jikan();
        return Flux.range(0, 15)
                .flatMap(i -> Mono.defer(() -> {
                    try {
                        return Mono.justOrEmpty(noCachingDek.query().random().anime().execute());
                    } catch (JikanQueryException e) {
                        return Mono.error(e);
                    }
                }))
                .flatMap(animeMono -> Mono.justOrEmpty(animeMono).block())
                .collectList();
    }

//    static int index = 0;
//    public static Mono<List<Anime>> getAnimeInDatabaseUser(int[] id) {
//        Jikan noCachingDek = new Jikan();
//        index++;
//        if (id.length == index) index = 0;
//        return Flux.range(0, id.length)
//                .flatMap(i -> Mono.defer(() -> {
//                    try {
//                        return Mono.justOrEmpty(noCachingDek.query().anime().get(id[index]).execute());
//                    } catch (JikanQueryException e) {
//                        return Mono.error(e);
//                    }
//                }))
//                .flatMap(animeMono -> Mono.justOrEmpty(animeMono).block())
//                .collectList();
//    }


    public static CompletableFuture<Anime> getAnimeByIdAsync(int id) {
        CompletableFuture<Anime> future = new CompletableFuture<>();

        CompletableFuture.runAsync(() -> {
            try {
                Anime anime = jikan.query().anime().get(id).execute().block();
                future.complete(anime);
            } catch (JikanQueryException e) {
                future.completeExceptionally(e);
            }
        });

        return future;
    }

//    public static List<CompletableFuture<Anime>> getAllAnimeListUserAsync() {
//        List<CompletableFuture<Anime>> result = new ArrayList<>();
//
//        try (Connection connection = DatabaseConnection.getDataSource().getConnection()) {
//            ConfigRepository configRepository = new ConfigRepositoryImpl();
//            String sql = "select * from user_animelist where id_user = ?";
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setInt(1, configRepository.getCurrentUserId());
//
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            while (resultSet.next()) {
//                int animeId = resultSet.getInt("id_animeapi");
//                CompletableFuture<Anime> animeFuture = CompletableFuture
//                        .supplyAsync(() -> JikanAPI.getAnimeByIdAsync(animeId))
//                        .thenCompose(Function.identity()); // Flatten the CompletableFuture
//                result.add(animeFuture);
//            }
//
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//
//        return result;
//    }

    public static List<Anime> joinAllAsyncResults(List<CompletableFuture<Anime>> futures) {
        CompletableFuture<Void> allOf = CompletableFuture.allOf(
                futures.toArray(new CompletableFuture[0]));

        try {
            allOf.get(); // Wait for all to complete
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        return futures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }



//    public static Mono<List<Anime>> getRecomendationAnime() throws JikanQueryException {
//        Mono<List<Anime>> results = null;
//        for (int i = 0; i < 5; i++) {
//            Anime anime = jikan.query().random().anime().execute().block();
//            results.
//        }
//
//        return null;
//    }
//


//    public static void main(String[] args) throws JikanQueryException {
//        List<Anime> animeList = searchAnimeByTitle("naruto");
//
//        Object[][] result = animeToDataTable(animeList);
//
//        for (Object[] obj : result) {
//            for (Object e : obj) {
//                System.out.println(e);
//            }
//        }
//    }

}
