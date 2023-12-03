import ade.myanimelist.anime.database.ConnectionDatabase;
import ade.myanimelist.anime.database.entity.User;
import ade.myanimelist.anime.database.repository.SignUpRepository;
import ade.myanimelist.anime.database.repository.SignUpRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

public class TestDatabase {
    private SignUpRepository signUpRepository;

    @BeforeEach
    void setUp() {
        signUpRepository = new SignUpRepositoryImpl();
    }

    @Test
    void testConnection() throws SQLException {
        Connection connection = ConnectionDatabase.getDataSource().getConnection();
    }
    @Test
    void testSignup() {
        User user = new User();
        user.setUsername("Adebayor33");
        user.setPassword("mamaakutakut");
        user.setIdTele(12345678);
        user.setUserNameTele("adebayor43");
        user.setJoined(new Date(System.currentTimeMillis()));

        signUpRepository.insert(user);

    }
}
