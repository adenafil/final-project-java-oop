import ade.myanimelist.anime.api.JikanAPI;
import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.exception.JikanQueryException;
import net.sandrohc.jikan.model.anime.Anime;;
import net.sandrohc.jikan.model.character.CharacterBasic;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.util.List;

public class APITest {
    @Test
    void testAPI() {
        Jikan jikan = new Jikan();
    }

    @Test
    void testSearchAnime() throws JikanQueryException {
        int index = 1;
        List<Anime> anime = JikanAPI.searchAnimeByTitle("Naruto");

        for (Anime isi : anime) {
            System.out.println(isi.titleEnglish);
            System.out.println(isi.images.getJpg().imageUrl);
            System.out.println(index++);
        }
    }

    @Test
    void testGetPopulerAnime() throws JikanQueryException {
        Jikan jikan = new Jikan();

        List<Anime> animes = jikan.query()
                .anime()
                .top()
                .execute()
                .collectList()
                .block();

        for (Anime anime : animes) {
            System.out.println(anime.duration.toMinutes());
            System.out.println(anime.titleEnglish);
            System.out.println(anime.images.getJpg().largeImageUrl);
            System.out.println(anime.episodes);
            System.out.println(anime.season);
            System.out.println(anime.year);
            System.out.println(anime.type);
            System.out.println(anime.genres);
            System.out.println(anime.rank);
            System.out.println(anime.score);
            System.out.println(anime.popularity);
            System.out.println(anime.synopsis);
            System.out.println(anime.source);
            System.out.println(anime.studios);
            System.out.println(anime.aired);
            System.out.println(anime.rating);
            System.out.println(anime.demographics.toString());
            System.out.println("producers" + anime.producers);

            anime.genres.forEach(e -> System.out.println(e.name));
            anime.genres.forEach(e -> System.out.println(e.malId));
            System.out.println(anime.rating.name());
            anime.studios.forEach(e -> System.out.println(e.name));
            OffsetDateTime tesLagi = anime.aired.from;
            System.out.println(tesLagi);
        }
    }

    @Test
    void testGetCharacterAnime() throws JikanQueryException {
        Jikan jikan = new Jikan();

        List<CharacterBasic> characterList = jikan.query()
                .anime()
                .characters(52991)
                .execute()
                .collectList()
                .block();

        for (CharacterBasic character : characterList) {
            System.out.println(character.getCharacter().name);
            System.out.println(character.character.images.getJpg().imageUrl);
        }
    }

    @Test
    void getAnimeById() throws JikanQueryException {
//        Jikan jikan = new Jikan();
//
//        Anime anime = jikan.query().anime().get(52991).execute().block();
//        System.out.println(anime.titleEnglish);
        Anime anime = JikanAPI.getAnimeById(20);
        System.out.println(anime.title);

    }
}