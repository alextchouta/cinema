package org.sid.restcontrollermvcunittest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableSwagger2
public class RestControllerMvcUnittestApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestControllerMvcUnittestApplication.class, args);
    }

/*    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("org.sid.restcontrollermvcunittest.controller")).build();
    }*/

}
