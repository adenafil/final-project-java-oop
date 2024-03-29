package ade.animelist.database.repository;

import ade.animelist.database.DatabaseConnection;
import ade.animelist.database.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * sebuah application programming interface class untuk melakukan register
 */
public class SignUpRepositoryImpl implements SignUpRepository{
    /**
     * Method untuk memasukan data user ke database user
     * @param user => class user
     * @return => true jika berhasil dan sebaliknya
     */
    @Override
    public boolean insert(User user) {

        try (Connection connection = DatabaseConnection.getDataSource().getConnection()) {
            String sql = "insert into user(username, password) values(?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());

            int update = preparedStatement.executeUpdate();

            System.out.println(update);

            if (update > 0) {
                LoginRepository loginRepository = new LoginRepositoryImpl();
                loginRepository.doesUsernameAndPasswordExist(user.getUsername(), user.getPassword());
                return true;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return false;

    }
}
