package com.azawisza.social.domain.user;

/**
 * Created by azawisza
 */
public enum UserFollowResult {
    USER_DOES_NOT_EXISTS,
    NO_SUCH_USER_TO_FOLLOW,
    FOLLOWED_ALREADY,
    CANNOT_FOLLOW_YOURSELF,
    SUCCESS
}
