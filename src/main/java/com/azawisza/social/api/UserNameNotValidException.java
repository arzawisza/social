package com.azawisza.social.api;

/**
 * Created by azawisza
 */
public class UserNameNotValidException extends RuntimeException {

    private String userName;

    public UserNameNotValidException(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }
}
