package com.azawisza.social.api.dto;

import com.azawisza.social.configuration.ToStringConfiguration;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created by azawisza
 */
public class FollowUserRQ {

    @NotEmpty(message = "User name should not be empty")
    @Size(max = 32, message = "User name is too long")
    @Pattern(regexp = "^[a-z0-9_-]{3,32}$", message = "User name is not valid.")
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public FollowUserRQ withUserName(final String userName) {
        this.userName = userName;
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
