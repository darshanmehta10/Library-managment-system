package com.msu.elibrary.services;

import org.springframework.stereotype.Service;

import com.msu.elibrary.entities.UDKCategory;

import java.util.List;

/**
 * @author VPatel
 */
@Service
public interface UDKService {
    public List<UDKCategory> getAllUDK();
}
