package com.azawisza.social.domain.post;

import com.azawisza.social.domain.model.Post;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.of;
import static javax.transaction.Transactional.TxType.SUPPORTS;

/**
 * Created by azawisza
 */
@Component
public class PostsRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(SUPPORTS)
    public Optional<List<Post>> fetchUserPosts(String userName) {
        return fetchPosts("SELECT p FROM Post p where p.user.name=:p_name", userName);
    }

    @Transactional(SUPPORTS)
    public Optional<List<Post>> fetchPostsOfUsersFollowedByUser(String userName) {
        return fetchPosts("SELECT p FROM Post p where p.user.uid IN " +
                "(select ft.followedId from Following ft, User u where ft.uid = u.uid AND u.name=:p_name)", userName);
    }

    private Optional<List<Post>> fetchPosts(String qlString, String userName) {
        List<Post> posts = entityManager.createQuery(qlString, Post.class)
                .setParameter("p_name", userName)
                .getResultList();
        return of(posts);
    }

}
