package com.azawisza.social.domain.user;

import com.azawisza.social.IntegrationTestBase;
import com.azawisza.social.api.dto.AddPostRQ;
import com.azawisza.social.api.dto.GetPostsRS;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Date;

import static com.azawisza.social.TestHelper.*;
import static java.util.Arrays.asList;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 * Created by azawisza
 */
public class SocialApplicationIntegrationTest extends IntegrationTestBase {


    @Autowired
    public CurrentDateSupplier dateSupplier;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        Mockito.when(dateSupplier.get()).thenReturn(new Date(10000));
    }

    @Test
    public void shouldFollowNewUserAndDisplayPosts() throws Exception {
        //given
        String newPostForExistingUser = json(new AddPostRQ().withPostText("new user post text").withPostTitle("new user post title"));
        String follower = json(followerUser("adam"));

        //when
        mockMvc.perform(post("/user/usernotexistsyet/posts")
                .content(newPostForExistingUser).contentType(APPLICATION_JSON_VALUE));
        mockMvc.perform(
                post("/user/usernotexistsyet/follow")
                        .content(follower).contentType(APPLICATION_JSON_VALUE));
        //then
        ResultActions performFollowed = mockMvc.perform(MockMvcRequestBuilders.get("/user/adam/posts/followed"));
        performFollowed.andExpect(MockMvcResultMatchers.content().json(
                json(new GetPostsRS().withPosts(asList(postDTO(), postDTO1(), newUserPost())))));
    }

}
