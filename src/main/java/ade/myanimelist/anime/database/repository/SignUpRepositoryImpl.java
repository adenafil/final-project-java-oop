package ade.myanimelist.anime.database.repository;

import ade.myanimelist.anime.database.ConnectionDatabase;
import ade.myanimelist.anime.database.entity.User;

import java.sql.*;

public class SignUpRepositoryImpl implements SignUpRepository{
    @Override
    public void insert(User user) {
        try (Connection connection = ConnectionDatabase.getDataSource().getConnection()) {
            String sql = "insert into user_myanimelist(username, password, id_tele, username_tele, joined) values(?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, user.getUsername());
                statement.setString(2, user.getPassword());
                statement.setInt(3, user.getIdTele());
                statement.setString(4, user.getUserNameTele());
                statement.setDate(5, user.getJoined());
                statement.executeUpdate();

                try (ResultSet resultSet = statement.getGeneratedKeys()) {
                    if (resultSet.next()) {
                        System.out.println(resultSet.getInt(3));
                    }
                }
            }
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public boolean doesUserExist(User user) {
        return false;
    }

    @Override
    public User getUserByUsername(String username) {
        return null;
    }
}
