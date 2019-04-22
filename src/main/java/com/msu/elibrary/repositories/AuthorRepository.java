package com.msu.elibrary.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.msu.elibrary.entities.Author;

/**
 * @author VPatel
 */
public interface AuthorRepository extends JpaRepository<Author,Integer>{
}
