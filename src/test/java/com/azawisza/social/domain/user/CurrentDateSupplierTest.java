package com.azawisza.social.domain.user;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.Date;

/**
 * Created by azawisza
 */
public class CurrentDateSupplierTest {

    @Test
    public void shouldReturnDate() {
        //given
        CurrentDateSupplier supplier = new CurrentDateSupplier();
        //when
        Date date = supplier.get();
        //then
        Assertions.assertThat(date).isNotNull();
    }
}