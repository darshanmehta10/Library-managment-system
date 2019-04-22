package com.msu.elibrary.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.msu.elibrary.entities.Faculty;

/**
 * @author VPatel
 */
@Service
public interface FacultyService {
    public List<Faculty> getAllFaculties();
    public Faculty getFacultyById(Integer id);
}
