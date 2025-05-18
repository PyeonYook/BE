package com.example.py_backend;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NoticeController {

    private final CrawlerService crawlerService;

    public NoticeController(CrawlerService crawlerService) {
        this.crawlerService = crawlerService;
    }

    @GetMapping("/crawl")
    public String startCrawling() {
        crawlerService.crawlNotices();
        return "크롤링 완료! 콘솔을 확인하세요.";
    }
}
