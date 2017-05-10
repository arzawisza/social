package com.azawisza.social.api;

import org.junit.Test;

import static com.azawisza.social.TestHelper.tooLongTitle;

/**
 * Created by azawisza
 */
public class UserNameValidatorTest {

    private UserNameValidator validator = new UserNameValidator();

    @Test(expected = UserNameNotValidException.class)
    public void shouldValidateBlank() {
        //given
        validator.validate("");
        //when
        //then
    }

    @Test(expected = UserNameNotValidException.class)
    public void shouldValidateNull() {
        //given
        validator.validate(null);
        //when
        //then
    }

    @Test(expected = UserNameNotValidException.class)
    public void shouldValidateCapitalLetters() {
        //given
        validator.validate("ABCD");
        //when
        //then
    }

    @Test(expected = UserNameNotValidException.class)
    public void shouldValidateCapitalMaxLength() {
        //given
        validator.validate(tooLongTitle);
        //when
        //then
    }

    @Test(expected = UserNameNotValidException.class)
    public void shouldValidateCapitalMinLength() {
        //given
        validator.validate("ab");
        //when
        //then
    }

}