package com.example.py_backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Keyword")
public class Keyword {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer keywordId;

    private Integer userId;

    private Integer keywordType;

    private String keyword;

    //get,set
    public Integer getKeywordId() { return keywordId; }
    public void setKeywordId(Integer keywordId) { this.keywordId = keywordId; }

    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }
    
    public Integer getKeywordType() { return keywordType; }
    public void setKeywordType(Integer keywordType) { this.keywordType = keywordType; }

    public String getKeyword() { return keyword; }
    public void setKeyword(String keyword) { this.keyword = keyword; }

    
}
