package ade.myanimelist.anime.database.repository;

import ade.myanimelist.anime.database.entity.User;

public interface SignUpRepository {
    void insert(User user);
}
