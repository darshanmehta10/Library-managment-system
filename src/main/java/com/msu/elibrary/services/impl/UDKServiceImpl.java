package com.msu.elibrary.services.impl;

import com.msu.elibrary.entities.UDKCategory;
import com.msu.elibrary.repositories.UDKRepository;
import com.msu.elibrary.services.UDKService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author VPatel
 */
@Service
public class UDKServiceImpl implements UDKService {
    @Autowired
    private UDKRepository udkRepository;

    @Override
    public List<UDKCategory> getAllUDK() {
        return udkRepository.findAll();
    }
}
