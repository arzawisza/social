package com.azawisza.social.domain.model;

import javax.persistence.*;


/**
 * Created by azawisza
 */

@Entity
@Table(name = "following_table")
public class Following {

    @Id
    private Long fid;

    @Column(nullable = false)
    private Long uid;

    @Column(nullable = false)
    private Long followedId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Following post = (Following) o;

        return fid.equals(post.fid);
    }

    @Override
    public int hashCode() {
        return fid.hashCode();
    }

}
