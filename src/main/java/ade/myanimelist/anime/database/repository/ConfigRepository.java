package ade.myanimelist.anime.database.repository;

import ade.myanimelist.anime.database.entity.User;

public interface ConfigRepository {
    void writeConfig(User user);
    int getCurrentUserId();
}
