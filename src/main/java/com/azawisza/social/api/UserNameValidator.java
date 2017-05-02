package com.azawisza.social.api;

import org.springframework.stereotype.Component;

import static org.apache.commons.lang.StringUtils.isBlank;

/**
 * Created by azawisza
 */
@Component
public class UserNameValidator {

    public void validate(String userName) {
        if (isBlank(userName) || !userName.matches("^[a-z0-9_-]{3,32}$")) {
            throw new UserNameNotValidException(userName);
        }
    }

}
