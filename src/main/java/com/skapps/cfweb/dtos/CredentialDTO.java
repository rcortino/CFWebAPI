package com.skapps.cfweb.dtos;

import java.io.Serializable;

public class CredentialDTO implements Serializable {

    private String username;
    private String password;

    public CredentialDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public CredentialDTO() {}

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
}
