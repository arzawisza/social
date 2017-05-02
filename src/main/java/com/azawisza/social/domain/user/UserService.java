package com.azawisza.social.domain.user;

import com.azawisza.social.api.dto.AddPostRQ;
import com.azawisza.social.domain.model.Post;
import com.azawisza.social.domain.model.User;
import com.azawisza.social.domain.post.PostConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Optional;

import static com.azawisza.social.domain.user.UserFollowResult.*;

/**
 * Created by azawisza
 */
@Component
public class UserService {

    private final UserRepository userRepository;
    private final PostConverter postConverter;
    private final CurrentDateSupplier currentDateSupplier;

    @Autowired
    public UserService(UserRepository userRepository, PostConverter postConverter, CurrentDateSupplier currentDateSupplier) {
        this.userRepository = userRepository;
        this.postConverter = postConverter;
        this.currentDateSupplier = currentDateSupplier;
    }

    @Transactional
    public UserPostResult publishUserPost(AddPostRQ request, String userName) {
        Optional<Post> postOfExistingUser = updateIfUserExists(request, userName);
        if (postOfExistingUser.isPresent()) {
            return new UserPostResult()
                    .withPost(postConverter.toService(postOfExistingUser.get()));
        } else {
            Post newPostOfNewUser = createUserAndAddPost(request, userName);
            return new UserPostResult().withPost(postConverter.toService(newPostOfNewUser))
                    .withUserCreated(true);
        }
    }

    @Transactional
    public UserFollowResult followUser(String userName, String userToBeFollowed) {
        if (userName.equals(userToBeFollowed)) {
            return CANNOT_FOLLOW_YOURSELF;
        }
        Optional<User> user = userRepository.getUser(userName);
        if (!user.isPresent()) {
            return USER_DOES_NOT_EXISTS;
        }
        Optional<User> toBeFollowed = userRepository.getUser(userToBeFollowed);
        if (!toBeFollowed.isPresent()) {
            return NO_SUCH_USER_TO_FOLLOW;
        }
        UserAggregate userAggregate = UserAggregate.fromEntity(user.get());
        if (userAggregate.followUser(toBeFollowed.get())) {
            return SUCCESS;
        } else {
            return FOLLOWED_ALREADY;
        }
    }

    @Transactional
    private Optional<Post> updateIfUserExists(AddPostRQ request, String userName) {
        Optional<User> user = userRepository.getUser(userName);
        UserAggregate userAggregate;
        Post newPost;
        if (user.isPresent()) {
            userAggregate = UserAggregate.fromEntity(user.get());
            newPost = buildPostEntity(request, user.get());
            userAggregate.addPost(newPost);
            newPost = userRepository.save(newPost);
            return Optional.of(newPost);
        }
        return Optional.empty();
    }

    private Post createUserAndAddPost(AddPostRQ request, String userName) {
        UserAggregate userAggregate = UserAggregate.createNewEntity(userName);
        User root = userAggregate.getRoot();
        Post post = buildPostEntity(request, root);
        userAggregate.addPost(post);
        post = userRepository.save(post);
        return post;
    }

    private Post buildPostEntity(AddPostRQ request, User user) {
        Post post = postConverter.toBackend(request.getTitle(), request.getText(), user);
        post.withDateCreated(currentDateSupplier.get());
        return post;
    }

}
