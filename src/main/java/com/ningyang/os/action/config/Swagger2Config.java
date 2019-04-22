package com.ningyang.os.action.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author： kaider
 * @Date： 2018/06/29 14:43
 * @描述：
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    private static final String apiPackage = "com.ningyang.os.controller.api";

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(apiPackage))
                .paths(Predicates.not(PathSelectors.regex("/error.*")))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(setHeaderToken());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("OS系统API")
                .description("API接口文档")
                .version("v1.0")
                .build();
    }

    private List<Parameter> setHeaderToken() {
        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> parameters = new ArrayList<>();
        tokenPar.name("Authorization")
                .description("token令牌")
                .modelRef(new ModelRef("String"))
                .parameterType("header")
                .required(false)
                .build();

        parameters.add(tokenPar.build());
        return parameters;
    }

    /*private List<Parameter> setHeaderTokenBack() {
        ParameterBuilder tokenPar = new ParameterBuilder();
        ParameterBuilder authorizationCodePar = new ParameterBuilder();

        List<Parameter> parameters = new ArrayList<>();

        tokenPar.name("Authorization")
                .description("token令牌")
                .modelRef(new ModelRef("String"))
                .parameterType("header")
                .required(false)
                .build();

        authorizationCodePar.name("authorizationCode")
                .description("授权码")
                .modelRef(new ModelRef("String"))
                .parameterType("query")
                .required(false)
                .build();

        parameters.add(tokenPar.build());
        parameters.add(authorizationCodePar.build());

        return parameters;
    }*/

}
