package com.example.py_backend.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.example.py_backend.repository.NoticeRepository;
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

    public void crawlNotices() {
        int page = 1;
        boolean flag = false;

        while(!flag){
            String url = (page==1)?
            "https://www.syu.ac.kr/academic/academic-notice/"
            : "https://www.syu.ac.kr/academic/academic-notice/page"+page+"/";
            try {
                Document doc = Jsoup.connect(url).get();

                Elements articles = doc.select("tbody tr"); // 공지 리스트 항목

                if(articles.isEmpty()) break;

                for (Element article : articles) {
                    String title = article.selectFirst("td.step2 a.itembx span.tit").text();
                    String link = article.selectFirst("td.step2 a.itembx").absUrl("href");
                    String author = article.selectFirst("td.step3").text();
                    String date = article.selectFirst("td.step4").text();

                    if (title == null || link == null || date == null) {
                        continue; // 누락된 게 있으면 skip
                    }

                    if (noticeRepository.existsByUrl(link)){
                        flag = true; break;
                    }

                    System.out.println("제목: " + title);
                    System.out.println("링크: " + link);
                    System.out.println("작성자: "+ author);
                    System.out.println("날짜: " + date);
                    System.out.println("---------------------------------");

                    Notice notice = new Notice();
                    notice.setTitle(title);
                    notice.setUrl(link);
                    notice.setAuthor(author);
                    
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy. MM. dd");
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
    
    public List<Notice> searchNoticesByKeywords(List<String> keywords) {
        Set<Notice> result = new HashSet<>();
        for(String k : keywords) {
            result.addAll(noticeRepository.findByTitleContaining(k));
        }
        return new ArrayList<>(result);
    }

        
}
