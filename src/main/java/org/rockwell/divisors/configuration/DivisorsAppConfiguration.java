package org.rockwell.divisors.configuration;

import org.rockwell.divisors.interfaces.IDivisorsResolver;
import org.rockwell.divisors.service.DivisorsMapper;
import org.rockwell.divisors.service.SimpleDivisorsResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DivisorsAppConfiguration {

    @Bean
    DivisorsMapper divisorsMapper() {
        return new DivisorsMapper();
    }

    @Bean
    IDivisorsResolver simpleResolver() {
        return new SimpleDivisorsResolver();
    }
}
