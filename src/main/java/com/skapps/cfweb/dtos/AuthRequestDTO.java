package com.skapps.cfweb.dtos;

import java.io.Serializable;

public class AuthRequestDTO implements Serializable {

    CredentialDTO credentials;

    public CredentialDTO getCredentials() {
        return credentials;
    }

    public void setCredentials(CredentialDTO credentials) {
        this.credentials = credentials;
    }
}
