package com.bahri.summarizer_backend.controller;

import com.bahri.summarizer_backend.dto.SummaryItem;
import java.util.List;
import com.bahri.summarizer_backend.dto.SummarizeRequest;
import com.bahri.summarizer_backend.dto.SummarizeResponse;
import com.bahri.summarizer_backend.service.SummarizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class SummarizationController {

    private final SummarizationService summarizationService;

    @Autowired
    public SummarizationController(SummarizationService summarizationService) {
        this.summarizationService = summarizationService;
    }

    @PostMapping("/summarize")
    public SummarizeResponse summarize(@RequestBody SummarizeRequest request) {
        String summary = summarizationService.summarize(request.getText());
        return new SummarizeResponse(summary);
    }

    @GetMapping("/summaries")
    public List<SummaryItem> listSummaries() {
        return summarizationService.listDemoSummaries();
    }
}
