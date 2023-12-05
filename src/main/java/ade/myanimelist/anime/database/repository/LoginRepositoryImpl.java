package ade.myanimelist.anime.database.repository;

import ade.myanimelist.anime.database.ConnectionDatabase;

import java.sql.*;

public class LoginRepositoryImpl implements LoginRepository{
    @Override
    public boolean isUsernameAndPasswordValid(String username, String password) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionDatabase.getDataSource().getConnection();
            String sql = "select * from user where username = ? and password = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                connection.close();
                preparedStatement.close();
                resultSet.close();
                return true;
            }
        } catch (SQLException exception) {
            throw new RuntimeException("Error executing SQL query : " + exception.getMessage(), exception);
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return false;
    }
}
