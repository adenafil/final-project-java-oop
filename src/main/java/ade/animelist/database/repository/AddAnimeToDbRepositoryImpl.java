package ade.animelist.database.repository;

import ade.animelist.api.JikanAPI;
import ade.animelist.database.DatabaseConnection;
import net.sandrohc.jikan.model.anime.Anime;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * sebuah api untuk menambahkan anime ke databse user
 */
public class AddAnimeToDbRepositoryImpl implements AddAnimeToDbRepository{
    /**
     * Method untuk menambahkan anime list yang ditonton
     * @param idUser => id user
     * @param idAnime => id anime
     * @param statusWatching => WATCHING, COMPLETED, DROP, ETC
     * @param currentEps => eps sekarang yang ditonton
     * @param maxEps => max episode of anime
     * @param nama => nama anime
     * @return true jika berhasil dan sebaliknya
     */
    @Override
    public boolean add(int idUser, int idAnime, String statusWatching, int currentEps, int maxEps, String nama) {
        try (Connection connection = DatabaseConnection.getDataSource().getConnection()) {
            String sql = "insert into user_animelist(id_user, id_animeapi, status_watching, current_episode, max_episode, nama) values(?, ?, ?, ?, ?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idUser);
            preparedStatement.setInt(2, idAnime);
            preparedStatement.setString(3, statusWatching);
            preparedStatement.setInt(4, currentEps);
            preparedStatement.setInt(5, maxEps);
            preparedStatement.setString(6, nama);

            int update = preparedStatement.executeUpdate();

            System.out.println(update);

            if (update > 0) {
                return true;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return false;
    }

    /**
     * Method untuk manambahkan progress yang sedang ditonton ke dalam database
     * @param idUser => id user
     * @param idAnime => id anime
     * @param statusWatching => status watching
     * @param currentEps => episode sekarang
     * @return => true jika berhasil dan sebaliknya
     */
    @Override
    public boolean addProgressWatchingAnime(int idUser, int idAnime, String statusWatching, int currentEps) {
        try (Connection connection = DatabaseConnection.getDataSource().getConnection()) {
            String sql = "update user_animelist set status_watching = ?, current_episode = ? where id_user = ? AND id_animeapi = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, statusWatching);
            preparedStatement.setInt(2, currentEps);
            preparedStatement.setInt(3, idUser);
            preparedStatement.setInt(4, idAnime);

            int update = preparedStatement.executeUpdate();

            System.out.println(update);

            if (update > 0) {
                return true;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return false;
    }

    /**
     * Method untuk mendapakna list anime user dari database
     * @return list anime user
     */
    @Override
    public List<Anime> getAllAnimeListInDatabaseUser() {
        List<Anime> result = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getDataSource().getConnection()) {
            ConfigRepository configRepository = new ConfigRepositoryImpl();
            String sql = "select * from user_animelist where id_user = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, configRepository.getCurrentUserId());

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Anime anime = new Anime();
                anime.title = resultSet.getString("nama");
                anime.malId = resultSet.getInt("id_animeapi");
                result.add(anime);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return result;
    }

    /**
     * Method untuk mendaptkan list anime user
     * @return list anime user
     */
    @Override
    public List<Anime> getAllAnimeListUser() {
        List<Anime> result = joinAllAsyncResults(getAllAnimeListUserAsync());
        return result;
    }

    /**
     * Method untuk mendapatkan anime user sekaligus fetch api untuk mengambil images
     * @return list anime user
     */
    @Override
    public List<CompletableFuture<Anime>> getAllAnimeListUserAsync() {
        List<CompletableFuture<Anime>> result = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getDataSource().getConnection()) {
            ConfigRepository configRepository = new ConfigRepositoryImpl();
            String sql = "select * from user_animelist where id_user = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, configRepository.getCurrentUserId());

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int animeId = resultSet.getInt("id_animeapi");
                CompletableFuture<Anime> animeFuture = CompletableFuture
                        .supplyAsync(() -> JikanAPI.getAnimeByIdAsync(animeId))
                        .thenCompose(Function.identity()); // Flatten the CompletableFuture
                result.add(animeFuture);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return result;
    }

    /**
     * Method untuk mengambil semua proses async yang sudah selesai
     * @param futures sebuah variabel future yang ditunggu proses asyncnya
     * @return list anime suer
     */
    @Override
    public List<Anime> joinAllAsyncResults(List<CompletableFuture<Anime>> futures) {
        CompletableFuture<Void> allOf = CompletableFuture.allOf(
                futures.toArray(new CompletableFuture[0]));

        try {
            allOf.get(); // Wait for all to complete
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        return futures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }

    /**
     * Method untuk mengecek apakah anime ini sudah ditambahkan user atau belum
     * @param malid => id mal
     * @return true jika ada dan sebaliknya
     */
    @Override
    public boolean doesThisAnimeExistInDatabase(int malid) {
        try (Connection connection = DatabaseConnection.getDataSource().getConnection()) {
            ConfigRepository configRepository = new ConfigRepositoryImpl();
            String sql = "select * from user_animelist where id_user = ? AND id_animeapi = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, configRepository.getCurrentUserId());
            preparedStatement.setInt(2, malid);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                if (resultSet.getInt("id_animeapi") == malid) return true;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return false;
    }

    /**
     * Mendapatkan status yang sedang ditonton berdasarkan mal id
     * @param malId => id anime mal
     * @return => status watching
     */
    @Override
    public String getStatusByMalId(int malId) {
        try (Connection connection = DatabaseConnection.getDataSource().getConnection()) {
            ConfigRepository configRepository = new ConfigRepositoryImpl();
            String sql = "select status_watching from user_animelist where id_animeapi = ? and id_user = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, malId);
            preparedStatement.setInt(2, configRepository.getCurrentUserId());

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString("status_watching");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    /**
     * Method untuk mendaptakn episode sekarang dari database
     * @param malId => mal id
     * @return => episode
     */
    @Override
    public int getCurrentEpsByMalId(int malId) {
        try (Connection connection = DatabaseConnection.getDataSource().getConnection()) {
            ConfigRepository configRepository = new ConfigRepositoryImpl();
            String sql = "select current_episode from user_animelist where id_animeapi = ? and id_user = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, malId);
            preparedStatement.setInt(2, configRepository.getCurrentUserId());

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("current_episode");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return -1;
    }

    /**
     * Method untuk menghapus anime dari list
     * @param malId => mal id
     * @return => true jika berhasil dan sebaliknya
     */
    @Override
    public boolean removeDatabaseAnimeUserById(int malId) {
        try (Connection connection = DatabaseConnection.getDataSource().getConnection()) {
            ConfigRepository configRepository = new ConfigRepositoryImpl();
            String sql = "DELETE from user_animelist where id_animeapi = ? and id_user = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, malId);
            preparedStatement.setInt(2, configRepository.getCurrentUserId());

            int update = preparedStatement.executeUpdate();

            System.out.println(update);

            if (update > 0) {
                return true;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }


}
