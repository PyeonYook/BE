package com.example.py_backend.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name= "Notice")
public class Notice {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)   // notice_id 자동 증가
    @Column(name = "notice_id", nullable = false)
    private Integer noticeId;

    @Column(nullable = false)
    private Integer noticeType;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, unique = true, length = 255)
    private String url;

    @Column(nullable = false)
    private String author;

    @Column(name = "published_at", nullable = false)
    private LocalDateTime publishedAt;

    @Column(name = "crawled_at")
    private LocalDateTime crawledAt;

    //get,set
    public Integer getNoticeId() {
        return noticeId;
    }
    public void setNoticeId(Integer noticeId) {
        this.noticeId = noticeId;
    }

    public Integer getNoticeType() {
        return noticeType;
    }
    public void setNoticeType(Integer noticeType) {
        this.noticeType = noticeType;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }

    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDateTime getPublishedAt() {
        return publishedAt;
    }
    public void setPublishedAt(LocalDateTime publishedAt) {
        this.publishedAt = publishedAt;
    }

    public LocalDateTime getCrawledAt() {
        return crawledAt;
    }
    public void setCrawledAt(LocalDateTime crawledAt) {
        this.crawledAt = crawledAt;
    }
}
