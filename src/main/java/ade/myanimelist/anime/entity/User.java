package ade.myanimelist.anime.entity;

import java.util.Date;
import java.util.List;

public class User {
    private String username;
    private String password;
    private int idTele;
    private String userNameTele;
    private Date joined;

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

    public int getIdTele() {
        return idTele;
    }

    public void setIdTele(int idTele) {
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

    public Date getJoined() {
        return joined;
    }

}
