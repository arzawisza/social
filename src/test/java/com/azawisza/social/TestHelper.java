package com.azawisza.social;

import com.azawisza.social.api.dto.ApplicationError;
import com.azawisza.social.api.dto.FollowUserRQ;
import com.azawisza.social.api.dto.PostDTO;
import com.azawisza.social.domain.model.Post;

import java.util.Date;

import static java.util.stream.Stream.generate;

/**
 * Created by azawisza
 */
public class TestHelper {


    public static  String tooLongTitle = generate(() -> "1").limit(41).reduce("", String::concat);

    public static PostDTO postDTO() {
        return new PostDTO()
                .withCreated("Jan 17, 2015 12:37:52 AM")
                .withPid(2L)
                .withText("text of post2")
                .withTitle("Post title 2");
    }

    public static PostDTO postDTO1() {
        return new PostDTO()
                .withCreated("Jan 17, 2016 2:37:52 PM")
                .withPid(3L)
                .withText("text 123")
                .withTitle("Post title 2");
    }

    public static PostDTO newUserPost() {
        return new PostDTO()
                .withCreated("Jan 1, 1970 1:00:10 AM")
                .withPid(4L)
                .withText("new user post text")
                .withTitle("new user post title");
    }

    public static ApplicationError notFound() {
        return new ApplicationError()
                .withCode("NOT_FOUND")
                .withMessage("User not found");
    }

    public static Post post() {
        return new Post().withPid(1L).withText("text").withTitle("title").withDateCreated(new Date(1000000));
    }

    public static Post post2() {
        return new Post().withPid(2L).withText("text2").withTitle("title2").withDateCreated(new Date(2000000));
    }

    public static Post post3() {
        return new Post().withPid(3L).withText("text3").withTitle("title3").withDateCreated(new Date(3000000));
    }

    public static FollowUserRQ followerUser(String userName) {
        return new FollowUserRQ().withUserName(userName);
    }

    public static ApplicationError error(String message, String code) {
        return new ApplicationError().withMessage(message).withCode(code);
    }

}
