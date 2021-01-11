package com.metheor.java.samples.classifieds.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.spring.web.readers.operation.ResponseMessagesReader;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
            .apis(RequestHandlerSelectors.basePackage("com.metheor.java.samples.classifieds.controller"))
            .paths(PathSelectors.ant("/v1/*"))
            .build()
            .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo("Classifieds Backend REST APIs", "Descriptions of APIs", "API ToS", "Terms of Service", new Contact("Darius Napigkit", "<TBD>", "dnapigkit@gmail.com"), "MIT License", "API License URL", Collections.emptyList());
        return apiInfo;
    }

}
