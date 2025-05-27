package com.example.py_backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.py_backend.service.NoticeService;

@RestController
public class NoticeController {

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
}
