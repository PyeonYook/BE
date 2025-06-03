package com.example.py_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.py_backend.entity.Notice;
import com.example.py_backend.service.KeywordService;
import com.example.py_backend.service.NoticeService;

import java.util.List;

@RestController
@RequestMapping("/api/keyword")
public class KeywordController {
    @Autowired KeywordService keywordService;
    @Autowired NoticeService noticeService;

    @PostMapping("/add") //키워드 추가
    public ResponseEntity<?> addKeyword(@RequestParam Integer userId, @RequestParam Integer keywordType, @RequestParam String keyword) {
        Integer ok = keywordService.addKeyword(userId, keywordType, keyword);
        if(ok == 1) return ResponseEntity.badRequest().body("3개 이상 추가할 수 없습니다."); //3개이상
        else if( ok == 2 ) return ResponseEntity.badRequest().body("중복된 키워드가 있습니다."); //중복
        return ResponseEntity.ok("키워드 추가 성공");
    }

    @GetMapping("/list") //키워드 리스트
    public ResponseEntity<List<String>> list(@RequestParam Integer userId) {
        return ResponseEntity.ok(keywordService.getUserKeywords(userId));
    }

    @GetMapping("/notices") //키워드 포함된 공지 리스트
    public ResponseEntity<List<Notice>> getMyKeywordNotices(@RequestParam Integer userId) {
        List<String> keywords = keywordService.getUserKeywords(userId);
        List<Notice> notices = noticeService.searchNoticesByKeywords(keywords);
        return ResponseEntity.ok(notices);
    }
}