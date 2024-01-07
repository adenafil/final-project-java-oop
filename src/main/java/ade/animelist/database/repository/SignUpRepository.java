package ade.animelist.database.repository;

import ade.animelist.components.entity.User;

public interface SignUpRepository {
    boolean insert(User user);
}
