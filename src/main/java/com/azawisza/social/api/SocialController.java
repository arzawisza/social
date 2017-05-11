package com.azawisza.social.api;

import com.azawisza.social.api.dto.*;
import com.azawisza.social.domain.post.PostsService;
import com.azawisza.social.domain.user.UserFollowResult;
import com.azawisza.social.domain.user.UserPostResult;
import com.azawisza.social.domain.user.UserService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Created by azawisza
 */
@RestController
@ApiResponses(value = {@ApiResponse(code = 400, message = "Request validation error")})
public class SocialController {

    @Autowired
    private UserService userService;
    @Autowired
    private PostsService postsService;
    @Autowired
    private UserFollowResultToRestConverter followToRest;
    @Autowired
    private UserNameValidator userNameValidator;
    @Autowired
    private PostsToRestEntityConverter postsToRest;
    @Autowired
    private RequestValidationErrorConverter validationErrorConverter;

    private final Logger log = LoggerFactory.getLogger(SocialController.class);

    @PostMapping(path = "/user/{userName}/posts", produces = {APPLICATION_JSON_VALUE}, consumes = {APPLICATION_JSON_VALUE})
    @ApiResponses(value = {@ApiResponse(code = 201, message = "New user created")})
    public ResponseEntity<AddPostRS> addPost(@Valid @RequestBody AddPostRQ addPostRQ, @PathVariable(required = true) String userName) {
        userNameValidator.validate(userName);
        UserPostResult userPostResult = userService.publishUserPost(addPostRQ, userName);
        return postsToRest.convertPostResult(userPostResult);
    }

    public
    @PostMapping(path = "/user/{userName}/follow", produces = {APPLICATION_JSON_VALUE}, consumes = {APPLICATION_JSON_VALUE})
    ResponseEntity followUser(@Valid @RequestBody FollowUserRQ request, @PathVariable(required = true) String userName) {
        userNameValidator.validate(userName);
        UserFollowResult userFollowResult = userService.followUser(request.getUserName(), userName);
        return followToRest.convert(userFollowResult);
    }

    public
    @GetMapping(path = "/user/{userName}/posts", produces = {APPLICATION_JSON_VALUE})
    ResponseEntity<GetPostsRS> fetchUserPosts(@PathVariable(required = true) String userName) {
        userNameValidator.validate(userName);
        Optional<List<PostDTO>> userPosts = postsService.findUserPosts(userName);
        return postsToRest.convertUserPosts(userPosts);
    }

    public
    @GetMapping(path = "/user/{userName}/posts/followed", produces = {APPLICATION_JSON_VALUE})
    ResponseEntity<GetPostsRS> fetchUserFollowedPosts(@PathVariable(required = true) String userName) {
        userNameValidator.validate(userName);
        Optional<List<PostDTO>> userPosts = postsService.findPostsFollowedByUser(userName);
        return postsToRest.convertUserPosts(userPosts);
    }

    @ExceptionHandler(NoResultException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ApplicationError processNoResultException(NoResultException ex) {
        ApplicationError applicationError = new ApplicationError().withCode(NOT_FOUND.name()).withMessage("User not found");
        log.error(applicationError.toString());
        return applicationError;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ApplicationError processValidationError(MethodArgumentNotValidException ex) {
        ApplicationError error = validationErrorConverter.convert(ex);
        log.error(error.toString());
        return error;
    }


    @ExceptionHandler(UserNameNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ApplicationError processUserNameNotValidException(UserNameNotValidException ex) {

        ApplicationError error = new ApplicationError()
                .withMessage("Invalid user name: " + ex.getUserName())
                .withCode(HttpStatus.BAD_REQUEST.name());
        log.error(error.toString());
        return error;
    }
}
