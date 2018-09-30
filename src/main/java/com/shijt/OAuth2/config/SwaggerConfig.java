package com.shijt.OAuth2.config;

import com.google.common.base.Predicates;
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

@Configuration
@EnableSwagger2
public class SwaggerConfig {

   @Bean
   public Docket createRestApi(){
       return new Docket(DocumentationType.SWAGGER_2)
               .apiInfo(apiInfo())
               .select()
//               .apis(RequestHandlerSelectors.any()) // 对所有api进行监控
               .apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.boot")))
               .paths(PathSelectors.any()) // 对所有路径进行监控
               .build();
   }

   private ApiInfo apiInfo(){
       return new ApiInfoBuilder()
               .title("springboot集成OAuth2接口文档")
               .description("springboot,oauth2,mysql,gradle,swagger")
               .version("1.3")
               .contact(new Contact("shijiantian","https://www.github.com/shijiantian/OAuth2","jiantian.shi@gmail.com"))
               .build();
   }
}
