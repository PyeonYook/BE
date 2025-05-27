package com.example.py_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.py_backend.entity.Keyword;

public interface KeywordRepository extends JpaRepository<Keyword, Integer>{
    List<Keyword> findByUserId(Integer userId);
}
