package ti.nepsther.bookstore.config;

import static springfox.documentation.builders.PathSelectors.regex;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
    public static Docket bookstoreApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("ti.nepsther.bookstore")) 
                .paths(regex("/api.*")) 
                .build()
                .apiInfo(metaInfo());
    }

	private static ApiInfo metaInfo() {

	       
		@SuppressWarnings("rawtypes")
		ApiInfo apiInfo = new ApiInfo(
                "Livraria API REST", 
                "API REST de cadastro de livros.", 
                "1.0", 
                "Terms of Service", 
                new Contact("Kenneth Luzolo", "https://www.likednin.com/KennethLuzolo","kenneth.nepsther.dev@gmail.com"), 
                "Apache License Version 2.0",
                "https://www.apache.org/licesen.html",  new ArrayList<VendorExtension>()
        );

        return apiInfo;
    }
}
