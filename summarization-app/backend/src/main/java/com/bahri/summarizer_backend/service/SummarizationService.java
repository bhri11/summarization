package com.bahri.summarizer_backend.service;

import com.bahri.summarizer_backend.model.Summary;
import com.bahri.summarizer_backend.model.User;
import com.bahri.summarizer_backend.repository.SummaryRepository;
import com.bahri.summarizer_backend.repository.UserRepository;
import org.springframework.stereotype.Service;
import com.bahri.summarizer_backend.dto.SummaryItem;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SummarizationService {

    private final UserRepository userRepository;
    private final SummaryRepository summaryRepository;

    public SummarizationService(UserRepository userRepository, SummaryRepository summaryRepository) {
        this.userRepository = userRepository;
        this.summaryRepository = summaryRepository;
    }

    public String summarize(String text) {
        if (text == null || text.isBlank())
            return "";

        // basit özet
        String cleaned = text.trim().replaceAll("\\s+", " ");
        String[] sentences = cleaned.split("(?<=[.!?])\\s+");
        String summary = sentences.length >= 2
                ? (sentences[0] + " " + sentences[1])
                : sentences[0];

        // demo user
        User demo = userRepository.findByUsername("demo")
                .orElseThrow(() -> new IllegalStateException("Demo user not found"));

        // DB kaydı
        summaryRepository.save(new Summary(text, summary, demo));

        return summary;
    }

    public List<SummaryItem> listDemoSummaries() {
        User demo = userRepository.findByUsername("demo")
                .orElseThrow(() -> new IllegalStateException("Demo user not found"));

        return summaryRepository.findByUserOrderByCreatedAtDesc(demo)
                .stream()
                .map(s -> new SummaryItem(s.getId(), s.getOriginalText(), s.getSummaryText(), s.getCreatedAt()))
                .collect(Collectors.toList());
    }

}
