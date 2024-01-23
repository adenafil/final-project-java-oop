package ade.animelist.database.repository;

import ade.animelist.database.DatabaseConnection;
import ade.animelist.database.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * sebuah application programming interface class untuk melakukan login
 */
public class LoginRepositoryImpl implements LoginRepository{

    /**
     * Method untuk mengecek apakah user ini sudah ada di database atau tidak
     * @param username sebauh username user
     * @param password sebuah password user
     * @return true jika ada dan sebaliknya
     */
    @Override
    public boolean doesUsernameAndPasswordExist(String username, String password) {

        try (Connection connection = DatabaseConnection.getDataSource().getConnection()) {

            String sql = "select * from user where username = ? and password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                ConfigRepository configRepository = new ConfigRepositoryImpl();
                User user = new User(
                        resultSet.getString("username"),
                        null,
                        resultSet.getInt("id")
                );
                configRepository.writeConfig(user);
                System.out.println(resultSet.getString("username") + " is login");
                return true;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return false;
    }
}
