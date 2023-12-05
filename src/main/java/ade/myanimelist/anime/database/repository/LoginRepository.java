package ade.myanimelist.anime.database.repository;

public interface LoginRepository {
    boolean isUsernameAndPasswordValid(String username, String password);
}
