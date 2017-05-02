package com.azawisza.social.domain.user;

import com.azawisza.social.domain.model.User;
import com.azawisza.social.domain.post.PostConverter;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static com.azawisza.social.domain.user.UserFollowResult.*;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

/**
 * Created by azawisza
 */
public class UserServiceTest {

    private UserRepository userRepository;
    private PostConverter postConverter;
    private CurrentDateSupplier currentDateSupplier;
    private UserService service;

    @Before
    public void setUp() {
        currentDateSupplier = Mockito.mock(CurrentDateSupplier.class);
        postConverter = new PostConverter();
        userRepository = Mockito.mock(UserRepository.class);
        service = new UserService(userRepository, postConverter, currentDateSupplier);
    }

    @Test
    public void shouldFollowUser() {
        //given
        Optional<User> user = of(new User().withName("user").withUid(1L));
        Optional<User> tobefollowed = of(new User().withName("tobefollowed").withUid(2L));
        when(userRepository.getUser("user")).thenReturn(user);
        when(userRepository.getUser("tobefollowed")).thenReturn(tobefollowed);
        //when
        UserFollowResult userFollowResult = service.followUser("user", "tobefollowed");
        //then
        assertThat(userFollowResult).isEqualTo(SUCCESS);
        assertThat(user.get().getFollowed().contains(tobefollowed.get())).isTrue();
    }

    @Test
    public void shouldNotFollowUserTwice() {
        //given
        Optional<User> tobefollowed = of(new User().withName("tobefollowed"));
        User user1 = new User().withName("user");
        user1.getFollowed().add(tobefollowed.get());
        Optional<User> user = of(user1);
        when(userRepository.getUser("user")).thenReturn(user);
        when(userRepository.getUser("tobefollowed")).thenReturn(tobefollowed);
        //when
        UserFollowResult userFollowResult = service.followUser("user", "tobefollowed");
        //then
        assertThat(userFollowResult).isEqualTo(FOLLOWED_ALREADY);
    }

    @Test
    public void shouldNotFollowUserThatDoesNotExists() {
        //given
        User user1 = new User().withName("user");
        Optional<User> user = of(user1);
        when(userRepository.getUser("user")).thenReturn(user);
        when(userRepository.getUser("tobefollowed")).thenReturn(Optional.empty());
        //when
        UserFollowResult userFollowResult = service.followUser("user", "tobefollowed");
        //then
        assertThat(userFollowResult).isEqualTo(NO_SUCH_USER_TO_FOLLOW);
    }

    @Test
    public void shouldNotFollowUserWhenDoesNotExists() {
        //given
        Optional<User> tobefollowed = of(new User().withName("tobefollowed"));
        Optional<User> user = empty();
        when(userRepository.getUser("user")).thenReturn(user);
        when(userRepository.getUser("tobefollowed")).thenReturn(tobefollowed);
        //when
        UserFollowResult userFollowResult = service.followUser("user", "tobefollowed");
        //then
        assertThat(userFollowResult).isEqualTo(USER_DOES_NOT_EXISTS);
    }
}