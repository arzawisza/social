package com.azawisza.social.api;

import com.azawisza.social.api.dto.AddPostRS;
import com.azawisza.social.api.dto.ApplicationError;
import com.azawisza.social.api.dto.GetPostsRS;
import com.azawisza.social.api.dto.PostDTO;
import com.azawisza.social.domain.user.UserPostResult;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.springframework.http.HttpStatus.*;

/**
 * Created by azawisza
 */
@Component
public class PostsToRestEntityConverter {

    public ResponseEntity<AddPostRS> convertPostResult(UserPostResult userPostResult) {
        AddPostRS addPostRS = new AddPostRS().withPost(userPostResult.getPost());
        if (userPostResult.isUserCreated()) {
            return new ResponseEntity<>(addPostRS, CREATED);
        } else {
            return new ResponseEntity<>(addPostRS, OK);
        }
    }

    public ResponseEntity<GetPostsRS> convertUserPosts(Optional<List<PostDTO>> userPosts) {

        Stream.of(1,2,3)
                .flatMap(e -> Stream.of(""+e,""+(1+e)))
                .collect(Collectors.toList());

        ResponseEntity<GetPostsRS> response = userPosts
                .map(postDTOS -> new ResponseEntity<>(
                        new GetPostsRS().withPosts(postDTOS), OK))
                .orElseGet(() -> {
                    GetPostsRS getPostsRS = new GetPostsRS();
                    getPostsRS.withError(new ApplicationError()
                            .withCode(NOT_FOUND.name())
                            .withMessage("User not found"));
                    return new ResponseEntity<>(getPostsRS, NOT_FOUND);
                });
        return response;
    }

}
