package com.example.py_backend.repository;

import java.time.LocalDateTime;
import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.py_backend.entity.Notice;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Integer>{

    // 중복확인
    boolean existsByUrl(String url);

    // 키워드 찾기
    List<Notice> findByTitleContaining(String keyword);

    // 새로운 공지
    List<Notice> findByPublishedAtAfter(LocalDateTime date);
}