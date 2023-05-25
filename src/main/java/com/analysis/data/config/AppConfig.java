package com.analysis.data.config;

import com.analysis.data.service.ETLService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public CommandLineRunner commandLineRunner(ETLService service){
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                service.extractTransformAndLoadData();
            }
        };
    }





}
