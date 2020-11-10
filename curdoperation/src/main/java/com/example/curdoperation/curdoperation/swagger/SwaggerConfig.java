package com.example.curdoperation.curdoperation.swagger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Configuration
@EnableSwagger2
@ComponentScan("com.*")
public class SwaggerConfig {

    public static final Contact DEFAULT_CONTACT = new Contact("City Dolphine", "https://cotydolphine.com",
            "support@citydolphine.com");

    public static final ApiInfo DEFAULT_API_INFO = new ApiInfo("AccentGURU API Title", "Accent API Description", "1.0",
            "urn:tos", DEFAULT_CONTACT, "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0");

    private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES = new HashSet<String>(
            Arrays.asList("application/json", "application/text"));

    @Bean
    public Docket api() {
        //Adding Header
       /* ParameterBuilder aParameterBuilder = new ParameterBuilder();
        Parameter p = aParameterBuilder.name("Authorization")                 // name of header
                         .modelRef(new ModelRef("string"))
                         .parameterType("header")               // type - header
                         .defaultValue("Bearer em9uZTpteXBhc3N3b3Jk")        // based64 of - zone:mypassword
                         .required(true)                // for compulsory
                         .build();

        List<Parameter> aParameters = new ArrayList();
        aParameters.add(p); */            // add parameter

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(DEFAULT_API_INFO)
                .produces(DEFAULT_PRODUCES_AND_CONSUMES)
                .consumes(DEFAULT_PRODUCES_AND_CONSUMES)
                .select().apis(RequestHandlerSelectors.basePackage("com.example"))
                .paths(PathSelectors.regex("/api.*"))
                .build();
        //.globalOperationParameters(aParameters);
    }
}