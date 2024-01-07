package ade.animelist.database.repository;

import ade.animelist.components.entity.User;

public interface ConfigRepository {
    void writeConfig(User user);
    int getCurrentUserId();
    String getCurrentUsername();
}
