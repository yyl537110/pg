package com.slj.pg.config;

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

/**
 * Created by yaoyl
 * on 2018/12/25.
 */
@Configuration
@EnableSwagger2
public class SpringfoxConfig {
    /**
     * 屏蔽部分的spring-boot自带的接口
     *
     * @return swagger docket
     */
    @Bean
    public Docket demoApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .host("140.143.236.173")
                .apiInfo(buildApiInf())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(Predicates.not(PathSelectors.regex("/error.*")))
                .build();
    }
    private ApiInfo buildApiInf() {
        Contact contact = new Contact("智能网格党建平台", "slj.com", "");
        return new ApiInfoBuilder()
                .title("智能网格党建平台1.0项目使用Swagger2 UI构建API文档")
                .contact(contact)
                .version("1.0")
                .build();
    }
}
