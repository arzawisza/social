package com.azawisza.social.api.dto;

import com.azawisza.social.configuration.ToStringConfiguration;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Created by azawisza
 */
public class ApplicationError {

    private String message;
    private String code;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ApplicationError withMessage(final String message) {
        this.message = message;
        return this;
    }

    public ApplicationError withCode(final String code) {
        this.code = code;
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
