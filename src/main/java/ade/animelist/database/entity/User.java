package ade.animelist.database.entity;

/**
 * Class user
 */
public class User {
    // id user
    private int id;
    // Username user
    private String username;
    // Password user
    private String password;

    /**
     * Constructor username
     * @param username
     * @param password
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Consturctor User
     * @param username
     * @param password
     * @param id
     */
    public User(String username, String password, int id) {
        this.username = username;
        this.password = password;
        this.id = id;
    }

    /**
     * Method untuk mendapatkan username
     * @return -> username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Method untuk mengset username
     * @param username -> username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Method untuk mendapatkan password
     * @return -> Password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Method untuk mengset password
     * @param password -> Password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Method untuk mendapatkan id user
     * @return id user
     */
    public int getId() {
        return id;
    }

    /**
     * Methoed untuk mengset id user
     * @param id -> id user
     */
    public void setId(int id) {
        this.id = id;
    }
}
