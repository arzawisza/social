package com.azawisza.social.domain.user;

import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.function.Supplier;

/**
 * Created by azawisza
 */
@Component
public class CurrentDateSupplier implements Supplier<Date> {

    @Override
    public Date get() {
        return new Date();
    }
}
