package com.posinhodev.rickandmorty.context;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     * Bean to configure the swagger
     * @return information about which drivers will be seen in the swagger
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.posinhodev.rickandmorty.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    /**
     * Information that will be shown in the swagger-ui to the users who view it
     * @return project information for display in the swagger
     */
    private ApiInfo apiInfo() {
        return new ApiInfo(
                "PT-RickAndMorty Spring Boot",
                "Technical test to consume Rick And Morty api with Spring Boot",
                "V1",
                "Terms of service",
                new Contact("posinhodev", "https://github.com/posinhodev", "jose.possop12@gmail.com"),
                "Licence of API", "API License URL", Collections.emptyList()
        );
    }
}
