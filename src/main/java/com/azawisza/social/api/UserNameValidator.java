package com.azawisza.social.api;

import org.springframework.stereotype.Component;

import static com.azawisza.social.configuration.ApplicationProperties.USERNAME_CONSTRAINT;
import static org.apache.commons.lang.StringUtils.isBlank;

/**
 * Created by azawisza
 */
@Component
public class UserNameValidator {

    public void validate(String userName) {
        if (isBlank(userName) || !userName.matches(USERNAME_CONSTRAINT)) {
            throw new UserNameNotValidException(userName);
        }
    }

}
