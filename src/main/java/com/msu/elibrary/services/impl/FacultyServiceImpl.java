package com.msu.elibrary.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msu.elibrary.entities.Faculty;
import com.msu.elibrary.repositories.FacultyRepository;
import com.msu.elibrary.services.FacultyService;

/**
 * @author VPatel
 */
@Service
public class FacultyServiceImpl implements FacultyService {
    @Autowired
    private FacultyRepository facultyRepository;

    @Override
    public List<Faculty> getAllFaculties() {
        return facultyRepository.findAll();
    }

    @Override
    public Faculty getFacultyById(Integer id) {
        return facultyRepository.findOne(id);
    }
}
