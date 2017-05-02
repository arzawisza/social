package com.azawisza.social.domain.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by azawisza
 */
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long uid;

    @Column(unique = true, nullable = false)
    private String name;

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy = "user")
    private Set<Post> post = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "following_table",
            joinColumns = @JoinColumn(name = "uid"),
            inverseJoinColumns = @JoinColumn(name = "followedId"))
    private List<User> followed = new ArrayList<>();


    public List<User> getFollowed() {
        return followed;
    }

    public Long getUid() {
        return uid;
    }

    public String getName() {
        return name;
    }

    public Set<Post> getPosts() {
        return post;
    }

    public User withUid(Long uid) {
        this.uid = uid;
        return this;
    }

    public User withName(String name) {
        this.name = name;
        return this;
    }

    public User withPost(Set<Post> post) {
        this.post = post;
        return this;
    }

    public User withPost(List<User> followed) {
        this.followed = followed;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;
        if (uid == null) {
            return user.uid == null;
        } else {
            return uid.equals(user.uid);
        }
    }

    @Override
    public int hashCode() {
        return uid != null ? uid.hashCode() : 0;
    }
}
