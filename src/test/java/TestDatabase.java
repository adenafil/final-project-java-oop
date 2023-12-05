import ade.myanimelist.anime.database.ConnectionDatabase;
import ade.myanimelist.anime.database.entity.User;
import ade.myanimelist.anime.database.repository.LoginRepository;
import ade.myanimelist.anime.database.repository.LoginRepositoryImpl;
import ade.myanimelist.anime.database.repository.SignUpRepository;
import ade.myanimelist.anime.database.repository.SignUpRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestDatabase {
    private SignUpRepository signUpRepository;
    private LoginRepository loginRepository;

    @BeforeEach
    void setUp() {
        signUpRepository = new SignUpRepositoryImpl();
        loginRepository = new LoginRepositoryImpl();

    }

    @Test
    void testConnection() throws SQLException {
        Connection connection = ConnectionDatabase.getDataSource().getConnection();
    }
    @Test
    void testSignup() {
        User user = new User();
        user.setUsername("adenafil");
        user.setPassword("12345678");
        user.setIdTele("5239560896");
        user.setUserNameTele("adenafiltele");

        signUpRepository.insert(user);
    }

    @Test
    void testLogin() {
        // test sql injection
        String username = "adenafil'; #";
        String password = "12345678";

        boolean result = loginRepository.isUsernameAndPasswordValid(username, password);
        System.out.println("Kamu berhasil login ? " + result);
    }


}
