package com.azawisza.social.configuration;


/**
 * Created by azawisza
 */
public class ApplicationProperties {

    public final static int MAX_MESSAGE_LENGTH = 140;
    public final static int MAX_TITLE_LENGTH = 40;
    public final static int MAX_USERNAME_LENGTH = 32;
    public final static int MIN_USERNAME_LENGTH = 3;
    public final static String USERNAME_CONSTRAINT = "^[a-z0-9_-]{"+MIN_USERNAME_LENGTH+","+MAX_USERNAME_LENGTH+"}$";

}
