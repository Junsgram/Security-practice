package org.practice.club;

import jakarta.persistence.EntityListeners;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PracticeSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(PracticeSecurityApplication.class, args);
    }

}
