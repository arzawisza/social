package com.azawisza.social.domain.post;

import com.azawisza.social.api.dto.PostDTO;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import static com.azawisza.social.TestHelper.post;

/**
 * Created by azawisza
 */
public class PostConverterTest {

    @Test
    public void shouldConvertToDto() {
        //given
        PostConverter converter = new PostConverter();
        //when
        PostDTO postDTO = converter.toService(post());
        //then
        Assertions.assertThat(postDTO).isEqualTo(new PostDTO().withCreated("Jan 1, 1970 1:16:40 AM")
                .withPid(1L).withText("text").withTitle("title"));
    }
}