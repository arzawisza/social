package com.azawisza.social.domain.user;

import com.azawisza.social.domain.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Optional;

/**
 * Created by azawisza
 */
@Component
public class UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    private final Logger log = LoggerFactory.getLogger(UserRepository.class);

    @Transactional
    public Optional<User> getUser(String name) {
        try {
            User singleUser = entityManager.createQuery("select u from User u where name=:p_name", User.class)
                    .setParameter("p_name", name)
                    .getSingleResult();
            return Optional.ofNullable(singleUser);
        } catch (NoResultException e) {
            log.error(e.getClass()+" : "+e.getMessage());
        }
        return Optional.empty();
    }

    @Transactional
    public boolean userExists(String name) {
        long userCount = entityManager.createQuery("select count(u) from User u where name=:p_name", Long.class)
                .setParameter("p_name", name)
                .getSingleResult();
        return userCount == 1;
    }

    @Transactional
    public <T> T save(T root) {
        T merged = entityManager.merge(root);
        entityManager.flush();
        return merged;
    }
}
