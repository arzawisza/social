package com.azawisza.social.api;

import com.azawisza.social.api.dto.ApplicationError;
import com.azawisza.social.api.dto.FollowUserRS;
import com.azawisza.social.domain.user.UserFollowResult;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import static com.azawisza.social.domain.user.UserFollowResult.SUCCESS;
import static com.azawisza.social.domain.user.UserFollowResult.USER_DOES_NOT_EXISTS;
import static org.springframework.http.HttpStatus.*;

/**
 * Created by azawisza
 */
@Component
public class UserFollowResultToRestConverter {


    public ResponseEntity<FollowUserRS> convert(UserFollowResult result) {

        if ((result == UserFollowResult.NO_SUCH_USER_TO_FOLLOW) || (result == USER_DOES_NOT_EXISTS)) {
            return new ResponseEntity<>(error(result.name(), NOT_FOUND.name()), NOT_FOUND);
        }
        if (result == SUCCESS) {
            return new ResponseEntity<>(new FollowUserRS(), OK);
        }
        return new ResponseEntity<>(error(result.name(), BAD_REQUEST.name()), BAD_REQUEST);
    }

    private FollowUserRS error(String message, String code) {
        FollowUserRS response = new FollowUserRS();
        response.withError(new ApplicationError()
                .withMessage(message)
                .withCode(code));
        return response;
    }

}
