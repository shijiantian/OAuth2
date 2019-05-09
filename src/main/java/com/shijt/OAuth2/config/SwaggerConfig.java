package com.shijt.OAuth2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {


    private String accessTokenUri ="http://127.0.0.1:8083/oauth/token";

   @Bean
   public Docket createRestApi(){
       return new Docket(DocumentationType.SWAGGER_2)
               .apiInfo(apiInfo())
               .select()
//               .apis(RequestHandlerSelectors.any()) // 对所有api进行监控
//               .apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.boot")))
               .apis(RequestHandlerSelectors.basePackage("com.shijt.OAuth2"))
               .paths(PathSelectors.any()) // 对所有路径进行监控
               .build()
               .securitySchemes(Arrays.asList(securitySchema()));
   }

   private ApiInfo apiInfo(){
       return new ApiInfoBuilder()
               .title("springboot with OAuth2 document")
               .description("springboot,oauth2,mysql,gradle,swagger")
               .version("1.3")
               .contact(new Contact("shijiantian","https://www.github.com/shijiantian/OAuth2","jiantian.shi@gmail.com"))
               .build();
   }


    private OAuth securitySchema() {

        List<AuthorizationScope> authorizationScopeList = new ArrayList();
        authorizationScopeList.add(new AuthorizationScope("all","access all"));
        authorizationScopeList.add(new AuthorizationScope("read", "read all"));
        authorizationScopeList.add(new AuthorizationScope("write", "write all"));
        List<GrantType> grantTypes = new ArrayList();
        GrantType passwordCredentialsGrant = new ResourceOwnerPasswordCredentialsGrant(accessTokenUri);
        grantTypes.add(passwordCredentialsGrant);
        return new OAuth("oauth2", authorizationScopeList, grantTypes);
    }


}
