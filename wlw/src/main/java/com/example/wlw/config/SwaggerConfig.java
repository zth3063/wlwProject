package com.example.wlw.config;

import com.google.common.base.Predicates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import javax.servlet.ServletContext;

@EnableSwagger2
@Configuration
public class SwaggerConfig {
    @Autowired
    private ApplicationContext applicationContext;

    private Contact contact = new Contact("Zlj","localhost:9201/swagger-ui.html", "2114075654@qq.com");

    @Bean
    public Docket createRestApi() {
        ServletContext servletContext = applicationContext.getBean(ServletContext.class);
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(Predicates.not(PathSelectors.regex("/error.*")))
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("物联网实验2 接口 v1.0")
                .description("后端接口")
                .contact(contact)
                .version("1.0")
                .build();
    }
}
