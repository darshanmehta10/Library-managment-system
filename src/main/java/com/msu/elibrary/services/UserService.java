package com.msu.elibrary.services;

import com.msu.elibrary.entities.User;
import com.msu.elibrary.exceptions.UserExistException;

import org.springframework.stereotype.Service;

/**
 * @author VPatel
 */
@Service
public interface UserService {
    User getUserByUsername(String username);
    User saveUser(User user) throws UserExistException;
    User getUserById(Integer id);
}
