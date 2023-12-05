package ade.myanimelist.anime.database.entity;

import java.sql.Timestamp;
import java.util.List;

public class User {
    int id;
    private String username;
    private String password;
    private String idTele;
    private String userNameTele;
    private Timestamp joined;

    List<AnimeStatsUser> statsUsers;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIdTele() {
        return idTele;
    }

    public void setIdTele(String idTele) {
        this.idTele = idTele;
    }

    public String getUserNameTele() {
        return userNameTele;
    }

    public void setUserNameTele(String userNameTele) {
        this.userNameTele = userNameTele;
    }

    public List<AnimeStatsUser> getStatsUsers() {
        return statsUsers;
    }

    public void setStatsUsers(List<AnimeStatsUser> statsUsers) {
        this.statsUsers = statsUsers;
    }

    public Timestamp getJoined() {
        return joined;
    }

    public void setJoined(Timestamp joined) {
        this.joined = joined;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
