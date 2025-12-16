package com.bahri.summarizer_backend.config;

import com.bahri.summarizer_backend.model.User;
import com.bahri.summarizer_backend.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initUsers(UserRepository userRepository) {
        return args -> {
            userRepository.findByUsername("demo")
                    .orElseGet(() -> userRepository.save(new User("demo", "demo-password")));
        };
    }
}
