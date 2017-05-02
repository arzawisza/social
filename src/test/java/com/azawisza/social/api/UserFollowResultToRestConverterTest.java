package com.azawisza.social.api;

import com.azawisza.social.api.dto.FollowUserRS;
import com.azawisza.social.domain.user.UserFollowResult;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by azawisza
 */
public class UserFollowResultToRestConverterTest {

    @Test
    public void shouldNotFound() {
        //given
        UserFollowResultToRestConverter converter = new UserFollowResultToRestConverter();
        //when
        ResponseEntity<FollowUserRS> noUserToFollow = converter.convert(UserFollowResult.NO_SUCH_USER_TO_FOLLOW);
        ResponseEntity<FollowUserRS> noUser = converter.convert(UserFollowResult.USER_DOES_NOT_EXISTS);
        //then
        assertThat(noUserToFollow.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(noUser.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void shouldReturnBadRequest() {
        //given
        UserFollowResultToRestConverter converter = new UserFollowResultToRestConverter();
        //when
        ResponseEntity<FollowUserRS> already = converter.convert(UserFollowResult.FOLLOWED_ALREADY);
        ResponseEntity<FollowUserRS> self = converter.convert(UserFollowResult.CANNOT_FOLLOW_YOURSELF);
        //then
        assertThat(already.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(self.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void shouldReturnOk() {
        //given
        UserFollowResultToRestConverter converter = new UserFollowResultToRestConverter();
        //when
        ResponseEntity<FollowUserRS> convert = converter.convert(UserFollowResult.SUCCESS);
        //then
        assertThat(convert.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

}