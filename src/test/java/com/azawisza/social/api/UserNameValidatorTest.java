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
        //when
        validator.validate("");
        //then
    }

    @Test(expected = UserNameNotValidException.class)
    public void shouldValidateNull() {
        //given
        //when
        validator.validate(null);
        //then
    }

    @Test(expected = UserNameNotValidException.class)
    public void shouldValidateCapitalLetters() {
        //given
        //when
        validator.validate("ABCD");
        //then
    }

    @Test(expected = UserNameNotValidException.class)
    public void shouldValidateCapitalMaxLength() {
        //given
        //when
        validator.validate(tooLongTitle);
        //then
    }

    @Test(expected = UserNameNotValidException.class)
    public void shouldValidateCapitalMinLength() {
        //given
        //when
        validator.validate("ab");
        //then
    }

}