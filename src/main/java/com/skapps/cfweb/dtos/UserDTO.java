package com.skapps.cfweb.dtos;

import com.skapps.cfweb.entities.User;
import com.skapps.cfweb.objects.Token;

public class UserDTO {

    private CredentialDTO credential;
    private String firstName;
    private String lastName;
    private String email;
    private String avatar;
    private String token;
    private long userId;

    public UserDTO (User user, Token token) {
        userId = user.getUserId();
        credential = new CredentialDTO(user.getUsername(), user.getPassword());
        firstName = user.getFirstName();
        lastName = user.getLastName();
        email = user.getEmail();
        avatar = user.getAvatarUrl();
        this.token = token.getValue();
    }

    public CredentialDTO getCredential() {
        return credential;
    }

    public void setCredential(CredentialDTO credential) {
        this.credential = credential;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
