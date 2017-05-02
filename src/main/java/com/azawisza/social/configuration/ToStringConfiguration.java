package com.azawisza.social.configuration;

import org.apache.commons.lang.SystemUtils;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Created by azawisza
 */
public class ToStringConfiguration {

    private static final long serialVersionUID = 1L;

    public static ToStringBuilder getToStringBuilder(Object object) {
        ToStringBuilder builder = new ReflectionToStringBuilder(object, new MyToStringStyle());
        return builder;
    }

    static class MyToStringStyle extends ToStringStyle {

        MyToStringStyle() {
            super();
            this.setUseIdentityHashCode(false);
            this.setUseClassName(true);
            this.setUseShortClassName(true);
            this.setContentStart("[");
            this.setFieldSeparator(SystemUtils.LINE_SEPARATOR + "  ");
            this.setFieldSeparatorAtStart(true);
            this.setContentEnd(SystemUtils.LINE_SEPARATOR + "]");
        }

        private Object readResolve() {
            return ToStringStyle.SIMPLE_STYLE;
        }

    }
}

