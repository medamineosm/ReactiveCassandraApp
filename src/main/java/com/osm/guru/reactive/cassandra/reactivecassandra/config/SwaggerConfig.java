package com.osm.guru.reactive.cassandra.reactivecassandra.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.web.reactive.config.ResourceHandlerRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurationSupport;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebFlux;

@Configuration
@EnableSwagger2WebFlux
public class SwaggerConfig extends WebFluxConfigurationSupport {

    @Bean
    public Jackson2JsonEncoder jackson2JsonEncoder(ObjectMapper mapper) {
        return new Jackson2JsonEncoder(mapper);
    }

    @Bean
    public Jackson2JsonDecoder jackson2JsonDecoder(ObjectMapper mapper) {
        return new Jackson2JsonDecoder(mapper);
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:META-INF/resources/");
    }

}
