package cn.tedu.tedunote.entity;

/**
 * Created by tarena on 2017/9/23.
 */

public class User {
    private String id;
    private String name;
    private String password;
    private String token;
    private String nick;

    public User() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    @Override
    public String toString() {
        return "{" +
                "id:" + id +
                ", name:" + name +
                ", password:" + password +
                ", token:" + token +
                ", nick:" + nick +
                '}';
    }
}
