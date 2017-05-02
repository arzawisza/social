package com.azawisza.social.api.dto;

import com.azawisza.social.configuration.ToStringConfiguration;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Created by azawisza
 */
public class PostDTO {

    private Long pid;
    private String title;
    private String text;
    private String created;

    public Long getPid() {
        return pid;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
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

    public String getCreated() {
        return created;
    }

    public PostDTO withPid(Long pid) {
        this.pid = pid;
        return this;
    }

    public PostDTO withTitle(String title) {
        this.title = title;
        return this;
    }

    public PostDTO withText(String text) {
        this.text = text;
        return this;
    }

    public PostDTO withCreated(String created) {
        this.created = created;
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
