package com.azawisza.social.domain.user;

import com.azawisza.social.api.dto.PostDTO;

/**
 * Created by azawisza
 */
public class UserPostResult {

    private boolean userCreated;
    private PostDTO post;

    public UserPostResult() {
    }


    public boolean isUserCreated() {
        return userCreated;
    }

    public void setUserCreated(boolean userCreated) {
        this.userCreated = userCreated;
    }


    public UserPostResult withUserCreated(final boolean userCreated) {
        this.userCreated = userCreated;
        return this;
    }

    public UserPostResult withPost(final PostDTO post) {
        this.post = post;
        return this;
    }

    public PostDTO getPost() {
        return post;
    }

    public void setPost(PostDTO post) {
        this.post = post;
    }
}
