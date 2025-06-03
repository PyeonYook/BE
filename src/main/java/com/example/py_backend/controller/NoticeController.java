package com.example.py_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.py_backend.entity.Notice;
import com.example.py_backend.service.NoticeService;

@RestController
@RequestMapping("/api/Notice")
public class NoticeController {
    @Autowired NoticeService noticeService;

    private final NoticeService crawlerService;

    public NoticeController(NoticeService crawlerService) {
        this.crawlerService = crawlerService;
    }

    @GetMapping("/crawl")
    public String startCrawling() {
        while(true){
            crawlerService.crawlNotices();
            try {
	            Thread.sleep(3600000); //1시간 대기
            } catch (InterruptedException e) {
	            e.printStackTrace();
            }
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Notice>> getAllNotices() {
        List<Notice> notices = noticeService.getAllNotices();
        return ResponseEntity.ok(notices);
    }
}
