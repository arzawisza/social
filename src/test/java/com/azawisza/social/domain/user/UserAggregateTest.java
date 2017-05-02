package com.azawisza.social.domain.user;

import com.azawisza.social.domain.model.Post;
import com.azawisza.social.domain.model.User;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import static com.azawisza.social.TestHelper.post;

/**
 * Created by azawisza
 */
public class UserAggregateTest {

    @Test
    public void shouldCreateForNewUser() {
        //given
        UserAggregate aggregate = UserAggregate.createNewEntity("user");
        //when
        //then
        Assertions.assertThat(aggregate.getRoot().getName()).isEqualTo("user");
    }

    @Test
    public void shouldCreateForExistingUser() {
        //given
        User user = new User().withName("user").withUid(1L);
        //when
        UserAggregate aggregate = UserAggregate.fromEntity(user);
        //then
        Assertions.assertThat(aggregate.getRoot().getName()).isEqualTo(user.getName());
    }

    @Test
    public void shouldAddPost() {
        //given
        User user = new User().withName("user").withUid(1L);
        UserAggregate aggregate = UserAggregate.fromEntity(user);
        Post post = post();
        //when
        aggregate.addPost(post);
        //then
        Assertions.assertThat(user.getPosts().contains(post()));
        Assertions.assertThat(post.getUser()).isEqualTo(user);
    }

    @Test
    public void shouldFollowUser() {
        //given
        User follower = new User().withName("user").withUid(1L);
        User user2 = new User().withName("user").withUid(2L);
        UserAggregate aggregate = UserAggregate.fromEntity(follower);
        //when
        boolean result = aggregate.followUser(user2);
        //then
        Assertions.assertThat(result).isTrue();
        Assertions.assertThat(follower.getFollowed().contains(user2)).isTrue();
    }

}