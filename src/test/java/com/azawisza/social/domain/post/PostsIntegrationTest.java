package com.azawisza.social.domain.post;

import com.azawisza.social.IntegrationTestBase;
import com.azawisza.social.api.dto.GetPostsRS;
import org.junit.Test;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static com.azawisza.social.TestHelper.*;
import static java.util.Arrays.asList;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

/**
 * Created by azawisza
 */
public class PostsIntegrationTest extends IntegrationTestBase {

    @Test
    public void shouldRetrieveUserPosts() throws Exception {
        //given
        ResultActions perform = mockMvc.perform(MockMvcRequestBuilders.get("/user/user1/posts"));
        perform.andExpect(MockMvcResultMatchers.content()
                .contentType(APPLICATION_JSON_UTF8_VALUE))
                .andExpect(MockMvcResultMatchers.content().json(
                        json(new GetPostsRS()
                                .withPosts(asList(postDTO(), postDTO1())))))
                .andExpect(MockMvcResultMatchers.status().is(200));
        //when
        //then
    }

    @Test
    public void shouldRetrievePostsOfFollowedUser() throws Exception {
        //given
        ResultActions perform = mockMvc.perform(MockMvcRequestBuilders.get("/user/adam/posts/followed"));
        perform.andExpect(MockMvcResultMatchers.content()
                .contentType(APPLICATION_JSON_UTF8_VALUE))
                .andExpect(MockMvcResultMatchers.content().json(
                        json(new GetPostsRS()
                                .withPosts(asList(postDTO(), postDTO1())))))
                .andExpect(MockMvcResultMatchers.status().is(200));
        //when
        //then
    }

    @Test
    public void shouldInformThatUserDoesNotExists() throws Exception {
        //given
        ResultActions perform = mockMvc.perform(MockMvcRequestBuilders.get("/user/nosuchuser/posts"));
        perform.andExpect(MockMvcResultMatchers.content()
                .contentType(APPLICATION_JSON_UTF8_VALUE))
                .andExpect(MockMvcResultMatchers.content().json(
                        json(new GetPostsRS()
                                .withError(notFound()))))
                .andExpect(MockMvcResultMatchers.status().is(404));
        //when
        //then
    }


    @Test
    public void shouldInformThatUserDoesNotExists_whenFollowedPosts() throws Exception {
        //given
        ResultActions perform = mockMvc.perform(MockMvcRequestBuilders.get("/user/nosuchuser/posts/followed"));
        perform.andExpect(MockMvcResultMatchers.content()
                .contentType(APPLICATION_JSON_UTF8_VALUE))
                .andExpect(MockMvcResultMatchers.content().json(
                        json(new GetPostsRS()
                                .withError(notFound()))))
                .andExpect(MockMvcResultMatchers.status().is(404));
        //when
        //then
    }

}
