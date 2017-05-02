package com.azawisza.social.domain.post;

import com.azawisza.social.api.dto.PostDTO;
import com.azawisza.social.domain.model.Post;
import com.azawisza.social.domain.model.User;
import org.springframework.stereotype.Component;

import static java.text.DateFormat.getDateTimeInstance;

/**
 * Created by azawisza
 */
@Component
public class PostConverter {

    public PostDTO toService(Post post) {
        String formattedDate = getDateTimeInstance().format(post.getDateCreated());
        PostDTO postDTO = new PostDTO()
                .withPid(post.getPid())
                .withCreated(formattedDate)
                .withText(post.getText())
                .withTitle(post.getTitle());
        return postDTO;
    }

    public Post toBackend(String postTitle, String postText, User user) {
        return new Post().withText(postText).withTitle(postTitle).withUser(user);
    }

}
