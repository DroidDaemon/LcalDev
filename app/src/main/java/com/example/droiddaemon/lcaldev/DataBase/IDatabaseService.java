package com.example.droiddaemon.lcaldev.DataBase;

public interface IDatabaseService {

    String getUsername();

    void setUsername(String username);

    void removeUsername();

    String getEmail();

    void setEmail(String email);

    void removeEmail();


}
