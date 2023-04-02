package com.example.api.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Nullable;

public class User {
    @Nullable
    @JsonProperty
    private Integer id;

    @JsonProperty
    private String username;

    @JsonProperty
    private String password;

    public User() {

    }

    public User(Integer id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    @Nullable
    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
