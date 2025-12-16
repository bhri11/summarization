package com.bahri.summarizer_backend.repository;

import com.bahri.summarizer_backend.model.Summary;
import com.bahri.summarizer_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SummaryRepository extends JpaRepository<Summary, Long> {

    List<Summary> findByUserOrderByCreatedAtDesc(User user);
}
