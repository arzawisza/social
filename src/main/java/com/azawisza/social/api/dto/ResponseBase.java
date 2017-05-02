package com.azawisza.social.api.dto;

import com.azawisza.social.configuration.ToStringConfiguration;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Created by azawisza
 */
public class ResponseBase {

    private ApplicationError error;

    public ApplicationError getError() {
        return error;
    }

    public void setError(ApplicationError error) {
        this.error = error;
    }

    public ResponseBase withError(final ApplicationError error) {
        this.error = error;
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
