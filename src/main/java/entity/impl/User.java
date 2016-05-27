package entity.impl;

/**
 * Created by EgorVeremeychik on 24.05.2016.
 */
public class User {
    private String login;

    public User(){}

    public User(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
