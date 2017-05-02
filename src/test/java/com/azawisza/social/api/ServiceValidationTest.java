package com.azawisza.social.api;

import com.azawisza.social.IntegrationTestBase;
import com.azawisza.social.api.dto.AddPostRQ;
import com.azawisza.social.api.dto.FollowUserRQ;
import org.junit.Test;
import org.springframework.test.web.servlet.ResultActions;

import static com.azawisza.social.TestHelper.error;
import static java.util.stream.Stream.generate;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

/**
 * Created by azawisza
 */
public class ServiceValidationTest extends IntegrationTestBase {

    @Test
    public void shouldValidatePostTitleAndTextEmpty() throws Exception {
        //given
        String newPostForExistingUser = json(new AddPostRQ().withPostText("").withPostTitle(""));
        //when
        ResultActions perform = sendPost(newPostForExistingUser, "/user/user1/posts");
        //then
        perform.andExpect(content().json(
                json(error("addPostRQtitleNotEmpty addPostRQtextNotEmpty ", "BAD_REQUEST"))));
        //when
        //then
    }

    @Test
    public void shouldValidatePostTitleTooLong() throws Exception {
        //given
        String tooLongTitle = generate(() -> "1").limit(300).reduce("", String::concat);
        String newPostForExistingUser = json(new AddPostRQ().withPostText("some text").withPostTitle(tooLongTitle));
        //when
        ResultActions perform = sendPost(newPostForExistingUser, "/user/user1/posts");
        //then
        perform.andExpect(content().json(
                json(error("addPostRQtitleSize ", "BAD_REQUEST"))));
        //when
        //then
    }

    @Test
    public void shouldValidateUserNameInPath() throws Exception {
        //given

        String post = json(new AddPostRQ().withPostText("some text").withPostTitle("title"));
        //when
        ResultActions perform = sendPost(post, "/user/userInvalid/posts");
        //then
        perform.andExpect(content().json(
                json(error("Invalid user name: userInvalid", "BAD_REQUEST"))));
        //when
        //then
    }

    @Test
    public void shouldValidateUserName() throws Exception {
        //given

        String follower = json(new FollowUserRQ().withUserName("userInvalid"));
        //when
        ResultActions perform = sendPost(follower, "/user/user21/follow");
        //then
        perform.andExpect(content().json(
                json(error("followUserRQuserNamePattern ", "BAD_REQUEST"))));
        //when
        //then
    }

    private ResultActions sendPost(String newPostForExistingUser, String urlTemplate) throws Exception {
        return mockMvc.perform(
                post(urlTemplate)
                        .content(newPostForExistingUser)
                        .contentType(APPLICATION_JSON_VALUE));
    }


}
