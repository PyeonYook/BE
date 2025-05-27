package com.example.py_backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.py_backend.entity.Keyword;
import com.example.py_backend.repository.KeywordRepository;

@Service
public class KeywordService {
    @Autowired KeywordRepository keywordRepository;
    
     public Integer addKeyword(Integer userId, String keyword) {
        List<Keyword> myKeywords = keywordRepository.findByUserId(userId);

        if(myKeywords.size() >= 3) { //3개이상거부
            return 1;
        }

        if (myKeywords.stream().anyMatch(k -> k.getKeyword().equals(keyword))) { //중복방지
            return 2;
        }

        Keyword k = new Keyword();
        k.setUserId(userId);
        k.setKeyword(keyword);
        keywordRepository.save(k);
        return 0;
    }

    public List<String> getUserKeywords(Integer userId) {
        return keywordRepository.findByUserId(userId).stream().map(Keyword::getKeyword).toList();
    }
} 
