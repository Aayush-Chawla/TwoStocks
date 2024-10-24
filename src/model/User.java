package model;

public class User {
    private String username;
    private String password;
    private Portfolio portfolio;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.portfolio = new Portfolio();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Portfolio getPortfolio() {
        return portfolio;
    }
}
