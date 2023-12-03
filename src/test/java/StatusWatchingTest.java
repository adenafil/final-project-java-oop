import ade.myanimelist.anime.database.entity.StatusWatching;
import org.junit.jupiter.api.Test;

public class StatusWatchingTest {
    @Test
    void testEnum() {
        for (StatusWatching status : StatusWatching.values()) {
            System.out.println("status : " + status.getDescription());
        }
    }
}
