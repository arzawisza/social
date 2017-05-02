package com.azawisza.social.domain.post;

import com.azawisza.social.api.dto.PostDTO;
import com.azawisza.social.domain.user.UserRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static com.azawisza.social.TestHelper.*;
import static java.util.Arrays.asList;
import static java.util.Optional.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by azawisza
 */
public class PostsServiceTest {


    private PostsRepository repository;
    private UserRepository userRepository;
    private PostsService service;


    @Before
    public void setUp() {
        repository = mock(PostsRepository.class);
        userRepository = mock(UserRepository.class);
        service = new PostsService(new PostConverter(), repository, userRepository);
    }

    @Test
    public void shouldFindAndSortPosts() {
        //given
        when(userRepository.userExists("user")).thenReturn(true);
        when(repository.fetchUserPosts("user"))
                .thenReturn(of(asList(post2(), post(), post3())));
        //when
        Optional<List<PostDTO>> user = service.findUserPosts("user");
        //then
        assertThat(user.isPresent()).isTrue();
        assertThat(user.get().size()).isEqualTo(3);
        assertThat(user.get().get(0).getCreated()).isEqualTo("Jan 1, 1970 1:50:00 AM");
        assertThat(user.get().get(1).getCreated()).isEqualTo("Jan 1, 1970 1:33:20 AM");
        assertThat(user.get().get(2).getCreated()).isEqualTo("Jan 1, 1970 1:16:40 AM");
    }

    @Test
    public void shouldFindAndSortPostsOfFollowedUser() {
        //given
        when(userRepository.userExists("user")).thenReturn(true);
        when(repository.fetchPostsOfUsersFollowedByUser("user"))
                .thenReturn(of(asList(post2(), post(), post3())));
        //when
        Optional<List<PostDTO>> user = service.findPostsFollowedByUser("user");
        //then
        assertThat(user.isPresent()).isTrue();
        assertThat(user.get().size()).isEqualTo(3);
        assertThat(user.get().get(0).getCreated()).isEqualTo("Jan 1, 1970 1:50:00 AM");
        assertThat(user.get().get(1).getCreated()).isEqualTo("Jan 1, 1970 1:33:20 AM");
        assertThat(user.get().get(2).getCreated()).isEqualTo("Jan 1, 1970 1:16:40 AM");
    }
}