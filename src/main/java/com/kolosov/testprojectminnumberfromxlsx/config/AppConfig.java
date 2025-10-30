package com.kolosov.testprojectminnumberfromxlsx.config;

import com.kolosov.testprojectminnumberfromxlsx.utils.FastSortService;
import com.kolosov.testprojectminnumberfromxlsx.utils.SortService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public SortService sortService() {
        return new FastSortService();
    }
}
