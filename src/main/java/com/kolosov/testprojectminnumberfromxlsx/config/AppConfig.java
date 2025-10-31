package com.kolosov.testprojectminnumberfromxlsx.config;

import com.kolosov.testprojectminnumberfromxlsx.services.sort.SortServiceImpl;
import com.kolosov.testprojectminnumberfromxlsx.services.sort.SortService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public SortService sortService() {
        return new SortServiceImpl();
    }
}
