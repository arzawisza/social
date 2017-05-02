package com.azawisza.social.api.dto;

import com.azawisza.social.configuration.ToStringConfiguration;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

/**
 * Created by azawisza
 */
public class GetPostsRS extends ResponseBase {

    private List<PostDTO> posts;

    public List<PostDTO> getPosts() {
        return posts;
    }

    public void setPosts(List<PostDTO> posts) {
        this.posts = posts;
    }

    public GetPostsRS withPosts(final List<PostDTO> posts) {
        this.posts = posts;
        return this;
    }

    public GetPostsRS withPosts(PostDTO... posts) {
        if (this.posts == null) {
            this.posts = new ArrayList<>();
        }
        this.posts.addAll(asList(posts));
        return this;
    }

    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    public String toString() {
        return ToStringConfiguration.getToStringBuilder(this).reflectionToString(this);
    }

    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

}
