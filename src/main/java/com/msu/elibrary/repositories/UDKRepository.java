package com.msu.elibrary.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.msu.elibrary.entities.UDKCategory;

/**
 * @author VPatel
 */
public interface UDKRepository extends JpaRepository<UDKCategory,Integer> {
}
