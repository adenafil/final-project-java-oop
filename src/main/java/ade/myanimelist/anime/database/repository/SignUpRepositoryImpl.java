package ade.myanimelist.anime.database.repository;

import ade.myanimelist.anime.database.ConnectionDatabase;
import ade.myanimelist.anime.database.entity.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class SignUpRepositoryImpl implements SignUpRepository{
    @Override
    public void insert(User user) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionDatabase.getDataSource().getConnection();
            String sql = "insert into user(username, password, id_tele, username_tele) values(?, ?, ?, ?)";
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getIdTele());
            statement.setString(4, user.getUserNameTele());
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                System.out.println(resultSet.getInt(1));
                user.setId(resultSet.getInt(1));
            }
        } catch (SQLException exception) {
            throw new RuntimeException("Error executing SQL query : " + exception.getMessage(), exception);
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ConfigRepository config = new ConfigRepositoryImpl();
            config.writeConfig(user);
        }
    }
}
