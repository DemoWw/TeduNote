package com.wuwei.tedunote;

/**
 * Created by wuwei on 2017/9/23.
 */

public class User {

    /**
     * id : 48595f52-b22c-4485-9244-f4004255b972
     * name : demo
     * password : 86c99f04c9162ce2bc1be721b69bf187
     * token :
     * nick :
     */
    private String id;
    private String name;
    private String password;
    private String token;
    private String nick;

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
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", token='" + token + '\'' +
                ", nick='" + nick + '\'' +
                '}';
    }
}
