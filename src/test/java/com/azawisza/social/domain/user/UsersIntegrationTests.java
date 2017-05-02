package com.azawisza.social.domain.user;

import com.azawisza.social.IntegrationTestBase;
import com.azawisza.social.api.dto.AddPostRQ;
import com.azawisza.social.api.dto.FollowUserRS;
import com.azawisza.social.api.dto.GetPostsRS;
import com.azawisza.social.api.dto.PostDTO;
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
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by azawisza
 */
public class UsersIntegrationTests extends IntegrationTestBase {

    @Autowired
    public CurrentDateSupplier dateSupplier;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        Mockito.when(dateSupplier.get()).thenReturn(new Date(10000));
    }

    @Test
    public void shouldFollowAnotherUser() throws Exception {
        //given
        String follower = json(followerUser("user1"));
        //when
        ResultActions perform = mockMvc.perform(
                post("/user/user2/follow")
                        .content(follower)
                        .contentType(APPLICATION_JSON_VALUE));
        //then
        perform.andExpect(status().is(200));
        perform.andExpect(content().json(json(new FollowUserRS())));
    }

    @Test
    public void shouldAddPostOfExisitingUser() throws Exception {
        //given
        String newPostForExistingUser = json(new AddPostRQ().withPostText("post text").withPostTitle("title"));
        //when
        ResultActions perform = mockMvc.perform(
                post("/user/user1/posts")
                        .content(newPostForExistingUser)
                        .contentType(APPLICATION_JSON_VALUE));
        //then
        verifyPostWasAdded();
    }

    @Test
    public void shouldAddNotExistingUser() throws Exception {
        //given
        String newPostForExistingUser = json(new AddPostRQ().withPostText("post text").withPostTitle("title"));
        //when
        ResultActions perform = mockMvc.perform(
                post("/user/usernotexists/posts")
                        .content(newPostForExistingUser)
                        .contentType(APPLICATION_JSON_VALUE));
        //then
        perform.andExpect(status().is(201));
    }

    private void verifyPostWasAdded() throws Exception {
        ResultActions check = mockMvc.perform(MockMvcRequestBuilders.get("/user/user1/posts"));
        check.andExpect(MockMvcResultMatchers.content()
                .contentType(APPLICATION_JSON_UTF8_VALUE))
                .andExpect(MockMvcResultMatchers.content().json(
                        json(new GetPostsRS()
                                .withPosts(asList(postDTO(), postDTO1(), new PostDTO()
                                        .withCreated("Jan 1, 1970 1:00:10 AM")
                                        .withPid(6L)
                                        .withText("post text").withTitle("title"))))))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }


}
