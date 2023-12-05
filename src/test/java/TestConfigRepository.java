import ade.myanimelist.anime.database.entity.User;
import ade.myanimelist.anime.database.repository.ConfigRepository;
import ade.myanimelist.anime.database.repository.ConfigRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestConfigRepository {
    private ConfigRepository configRepository;
    @BeforeEach
    void setUp() {
        configRepository = new ConfigRepositoryImpl();
    }

    @Test
    void writeConfig() {
        User user = new User();
        user.setId(3);
        configRepository.writeConfig(user);
    }

    @Test
    void tesGetConfig() {
        System.out.println("id user rn = " + configRepository.getCurrentUserId());
    }
}
