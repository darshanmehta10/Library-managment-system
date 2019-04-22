package com.msu.elibrary.services.impl;

import com.msu.elibrary.entities.Faculty;
import com.msu.elibrary.entities.User;
import com.msu.elibrary.entities.enums.UserRoles;
import com.msu.elibrary.exceptions.UserExistException;
import com.msu.elibrary.repositories.FacultyRepository;
import com.msu.elibrary.repositories.UserRepository;
import com.msu.elibrary.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author VPatel
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FacultyRepository facultyRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User saveUser(User user) throws UserExistException {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new UserExistException();
        }
        Faculty faculty = facultyRepository.findOne(user.getFaculty().getId());
        user.setFaculty(faculty);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRole(UserRoles.USER);
        user.setEnabled(true);
        return userRepository.saveAndFlush(user);
    }

    @Override
    public User getUserById(Integer id) {
        return userRepository.findOne(id);
    }
}
