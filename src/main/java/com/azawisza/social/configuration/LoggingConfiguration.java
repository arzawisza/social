package com.azawisza.social.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

import javax.servlet.Filter;

/**
 * Created by azawisza
 */
@Configuration
public class LoggingConfiguration {

    @Bean
    public Filter createFilter() {
        CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();
        filter.setIncludePayload(true);
        filter.setIncludeQueryString(true);
        filter.setMaxPayloadLength(5100);
        filter.setIncludeClientInfo(true);
        filter.setIncludeHeaders(true);
        return filter;
    }

}