package com.msu.elibrary.exceptions;

import javax.security.auth.message.AuthException;

/**
 * @author VPatel
 */
public class UserExistException extends AuthException {
    public UserExistException(){
        super("Email for user exists");
    }
    public UserExistException(final String email){
        super(String.format("Email '%s' already exists in DB.", email));
    }

}
