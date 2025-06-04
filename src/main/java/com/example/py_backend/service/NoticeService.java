package com.example.py_backend.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.py_backend.repository.NoticeRepository;

import jakarta.annotation.PostConstruct;

import com.example.py_backend.entity.Notice;
import java.io.IOException;
import java.time.*;
import java.time.format.*;
import java.util.*;

@Service
public class NoticeService {

    private final NoticeRepository noticeRepository;

    public NoticeService(NoticeRepository noticeRepository){
        this.noticeRepository = noticeRepository;
    }

    @PostConstruct
    public void onStartup(){
        crawlNotices();
    }

    @Scheduled(fixedRate = 60*60*1000)
    public void scheduledCrawl(){
        crawlNotices();
    }

    public void crawlNotices() {

        for(int i=0;i<2;i++){
            int page = 1;
            boolean flag = false;
            String url = "";

            if(i==0) url = "https://www.syu.ac.kr/academic/academic-notice/";
            else url = "https://www.syu.ac.kr/academic/scholarship-information/scholarship-notice/";
            
            while(!flag){
                
                if(page!=1) url += "/page/"+page+"/";
                try {
                    Document doc = Jsoup.connect(url).get();

                    Elements articles = doc.select("tbody tr"); // 공지 리스트 항목

                    if(articles.isEmpty()) break;

                    for (Element article : articles) {
                        Element tElement = article.selectFirst("td.step2 a.itembx span.tit");
                        String title = tElement!=null?tElement.text() : null;
                        Element lElement = article.selectFirst("td.step2 a.itembx");
                        String link = lElement!=null? lElement.absUrl("href") : null;
                        Element aElement = article.selectFirst("td.step3");
                        String author = aElement!=null?aElement.text() : null;
                        Element dElement = article.selectFirst("td.step4");
                        String date = dElement!=null?dElement.text() : null;

                        if (title == null || link == null || author == null ||date == null) {
                            continue; // 누락된 게 있으면 skip
                        }

                        if (noticeRepository.existsByUrl(link)){
                            flag = true; break;
                        }

                        Notice notice = new Notice();

                        notice.setNoticeType(i);
                        notice.setTitle(title);
                        notice.setUrl(link);
                        notice.setAuthor(author);
                        
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
                        notice.setPublishedAt(LocalDate.parse(date, formatter).atStartOfDay());

                        notice.setCrawledAt(LocalDateTime.now());

                        noticeRepository.save(notice);
                    }

                    page++;

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public List<Notice> getAllNotices() {
        return noticeRepository.findAll();
    }

    public List<Notice> searchNoticesByKeywords(List<String> keywords) {
        Set<Notice> result = new HashSet<>();
        for(String k : keywords) {
            result.addAll(noticeRepository.findByTitleContaining(k));
        }
        return new ArrayList<>(result);
    }
           
}
