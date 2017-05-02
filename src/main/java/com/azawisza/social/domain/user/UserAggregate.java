package com.azawisza.social.domain.user;

import com.azawisza.social.domain.model.Post;
import com.azawisza.social.domain.model.User;

import java.util.List;
import java.util.Set;

/**
 * Created by azawisza
 */
public class UserAggregate {

    private User user;

    private UserAggregate(User user) {
        this.user = user;
    }

    public static UserAggregate fromEntity(User user) {
        return new UserAggregate(user);
    }

    public static UserAggregate createNewEntity(String userName) {
        User user = new User().withName(userName);
        return new UserAggregate(user);
    }

    public boolean followUser(User user) {
        List<User> followed = this.user.getFollowed();
        if (followed.contains(user)) {
            return false;
        } else {
            followed.add(user);
            return true;
        }
    }

    public void addPost(Post post) {
        Set<Post> posts = this.user.getPosts();
        post.withUser(this.user);
        posts.add(post);
    }

    public User getRoot() {
        return user;
    }

}
