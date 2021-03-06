package com.azawisza.social.api.dto;

import com.azawisza.social.configuration.ToStringConfiguration;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

/**
 * Created by azawisza
 */
public class AddPostRQ {

    @NotEmpty
    @Size(max = 255, message = "Post title is too long")
    private String title;

    @NotEmpty(message = "Post text should not be empty")
    @Size(max = Integer.MAX_VALUE, message = "Post text is too long")
    private String text;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public AddPostRQ withPostTitle(final String postTitle) {
        this.title = postTitle;
        return this;
    }

    public AddPostRQ withPostText(final String postText) {
        this.text = postText;
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
