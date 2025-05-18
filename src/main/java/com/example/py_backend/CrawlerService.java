package com.example.py_backend;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CrawlerService {

    public void crawlNotices() {
        String url = "https://www.syu.ac.kr/academic/academic-notice/";

        try {
            Document doc = Jsoup.connect(url).get();

            Elements articles = doc.select("tbody tr"); // 공지 리스트 항목

            for (Element article : articles) {
                String title = article.selectFirst("td.step2 a.itembx span.tit").text();
                String link = article.selectFirst("td.step2 a.itembx").absUrl("href");
                String date = article.selectFirst("td.step4").text();

                if (title == null || link == null || date == null) {
                    continue; // 누락된 게 있으면 skip
                }

                System.out.println("제목: " + title);
                System.out.println("링크: " + link);
                System.out.println("날짜: " + date);
                System.out.println("---------------------------------");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
