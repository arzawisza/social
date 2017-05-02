package com.azawisza.social.domain.post;

import com.azawisza.social.api.dto.PostDTO;
import com.azawisza.social.domain.model.Post;
import com.azawisza.social.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static java.lang.Long.compare;
import static java.util.Optional.empty;
import static java.util.stream.Collectors.toList;

/**
 * Created by azawisza
 */
@Component
public class PostsService {


    private final PostsRepository repository;
    private final UserRepository userRepository;
    private final PostConverter postConverter;

    @Autowired
    public PostsService(PostConverter postConverter, PostsRepository repository, UserRepository userRepository) {
        this.postConverter = postConverter;
        this.repository = repository;
        this.userRepository = userRepository;
    }

    public Optional<List<PostDTO>> findUserPosts(String userName) {
        if (userRepository.userExists(userName)) {
            Optional<List<Post>> posts = repository.fetchUserPosts(userName);
            return posts.map(this::convertPostsToDto);
        } else {
            return empty();
        }
    }

    public Optional<List<PostDTO>> findPostsFollowedByUser(String userName) {
        if (userRepository.userExists(userName)) {
            Optional<List<Post>> posts = repository.fetchPostsOfUsersFollowedByUser(userName);
            return posts.map(this::convertPostsToDto);
        } else {
            return empty();
        }
    }

    private List<PostDTO> convertPostsToDto(Collection<Post> posts) {
        List<PostDTO> result = posts.stream()
                .sorted((post1, post2) -> compare(post2.getDateCreated().getTime(), post1.getDateCreated().getTime()))
                .map(postConverter::toService)
                .collect(toList());
        return result;
    }

}
