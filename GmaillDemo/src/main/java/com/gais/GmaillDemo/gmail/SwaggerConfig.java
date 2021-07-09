package com.gais.GmaillDemo.gmail;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@ComponentScan("com.*")
public class SwaggerConfig {
    //http://localhost:9494/swagger-ui.html
    //http://65.1.86.112:8000/api/hospital/getAllHospital
    //http://65.1.86.112:8000/swagger-ui.html

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select().apis(RequestHandlerSelectors.basePackage("com.gais"))
                .paths(PathSelectors.regex("/api.*"))
                .build();
    }
}
