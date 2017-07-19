package com.mcs.cysoft.swagger;

import org.springframework.beans.factory.annotation.Value;
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
public class CysoftSwaggerAutoConfiguration {
	
	@Value(value = "${swagger.package}")
	private String swaggerPackage;

	@Bean
    public Docket buildDocket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(buildApiInf())
                .select()
                .apis(RequestHandlerSelectors.basePackage(swaggerPackage))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo buildApiInf(){
        return new ApiInfoBuilder()
                    .title("zzj swagger file")
                    .description("springboot swagger2")
                    .termsOfServiceUrl("http://www.baidu.com")
                    .contact(new Contact("zzj", "http://blog.csdn.net/u014231523", "zzj@163.com"))
                    .build();

    }
}
