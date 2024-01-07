package ade.animelist.api;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.Expiry;
import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.cache.JikanCache;
import net.sandrohc.jikan.exception.JikanQueryException;
import net.sandrohc.jikan.model.anime.Anime;
import net.sandrohc.jikan.model.enums.AgeRating;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.time.OffsetDateTime;
import java.util.*;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * Class ini berisi method method untuk mengquery atau mengfetch API dari JIkan API (unofficaial MAL).
 * Kami menggunakan "Java wrapper for the Jikan API V4" untuk mempermudah dalam membungkus sebuah hasil query yang
 * berberntuk JSON menjadi Object di java dengan class Anime.
 * Dependency -> https://github.com/SandroHc/reactive-jikan
 */
public class JikanAPI {
    // Membuat Object class Jikan
    private static Jikan jikan;
    // Membuat Object untuk mengaktifkan caching
    private static JikanCache jikanCache;

    /**
     * Mengkonfigurasi caching fitur dengan Caffeine.
     * Docs : https://github.com/SandroHc/reactive-jikan
     * Docs : https://github.com/ben-manes/caffeine
     */
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
     * Method ini digunakan untuk mencari anime yang dibungkus menjadi ArrayList
     * @param title -> Judul anime
     * @return -> list atau kumpulan anime
     * @throws JikanQueryException
     */
    public static List<Anime> searchAnimeByTitle(String title) throws JikanQueryException {
        // Dengan objek jikan lakukakan query anime, cari anime dengan rating 13 diberi judul sesuai parameter kemudian execute
        // dan jadikan lah menjadi collectionList
        return jikan.query().anime().search().rating(AgeRating.PG13).query(title).execute().collectList().block();
    }


    /**
     * Method untuk mendapatkan anime dengan id tertentu
     * @param id -> id anime pada website MAL
     * @return -> Object Anime
     * @throws JikanQueryException
     */
    public static Anime getAnimeById(int id) throws JikanQueryException {
        // Tolong carikan saya anime dengan id ini kemudian execute
        return jikan.query().anime().get(id).execute().block();
    }

    /**
     * Method ini untuk mendapatkan atau mencari anime dengan judul tertentu
     * @param title -> Judul anime
     * @return -> -> Kumpulan dari anime (MAX 25 yang didapat)
     * @throws JikanQueryException
     */
    public static Mono<List<Anime>> getTitleAndImageAnimeBySearchAsync(String title) throws JikanQueryException {
        // Mencari anime dengan mengfilter yang harus ditemani oleh orang tua
        return jikan.query().anime().search().rating(AgeRating.PG13)
                .query(title)
                .execute()
                .collectList();
    }

    /**
     * Method untuk mendapatkan top anime dengan limit 10
     * @return -> Kumpulan 10 top anime (Based On Rank of MAL)
     * @throws JikanQueryException
     */
    public static Mono<List<Anime>> getTopAnime() throws JikanQueryException {
        return jikan.query().anime().top()
                .limit(10)
                .execute()
                .collectList();
    }

    /**
     * Method untuk mendapatkan 40 rekomendasi anime (RANDOM ANIME)
     * @return -> kumpulan rekomendasi anime atau random anime
     */
    public static Mono<List<Anime>> getRecommendationAnimeAsync() {
        Jikan noCachingDek = new Jikan();
        return Flux.range(0, 40)
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

    /**
     * Method untuk mendapatkan anime dengan id tertentu melalui async java (CompletableFuture)
     * @param id -> Id MAL anime
     * @return -> CompletebleFUture dengan generic Objek Anime
     */
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
}
