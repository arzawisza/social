package com.azawisza.social.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by azawisza
 */
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long pid;

    @Column(nullable = false)
    private String title;

    @Column(length = Integer.MAX_VALUE)
    private String text;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "uid")
    private User user;

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public User getUser() {
        return user;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public Post withPid(Long pid) {
        this.pid = pid;
        return this;
    }

    public Post withTitle(String title) {
        this.title = title;
        return this;
    }

    public Post withText(String text) {
        this.text = text;
        return this;
    }

    public Post withUser(User user) {
        this.user = user;
        return this;
    }

    public Post withDateCreated(final Date created) {
        this.dateCreated = created;
        return this;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Post post = (Post) o;
        if (pid == null) {
            return post.pid == null;
        } else {
            return pid.equals(post.pid);
        }
    }

    @Override
    public int hashCode() {
        return pid != null ? pid.hashCode() : 0;
    }
}
