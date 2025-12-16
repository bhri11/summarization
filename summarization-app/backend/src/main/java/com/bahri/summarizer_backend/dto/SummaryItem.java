package com.bahri.summarizer_backend.dto;

import java.time.LocalDateTime;

public class SummaryItem {
    private Long id;
    private String originalText;
    private String summaryText;
    private LocalDateTime createdAt;

    public SummaryItem() {
    }

    public SummaryItem(Long id, String originalText, String summaryText, LocalDateTime createdAt) {
        this.id = id;
        this.originalText = originalText;
        this.summaryText = summaryText;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getOriginalText() {
        return originalText;
    }

    public String getSummaryText() {
        return summaryText;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setOriginalText(String originalText) {
        this.originalText = originalText;
    }

    public void setSummaryText(String summaryText) {
        this.summaryText = summaryText;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
