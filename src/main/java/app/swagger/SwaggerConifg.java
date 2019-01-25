package app.swagger;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

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
public class SwaggerConifg extends WebMvcConfigurationSupport{

	public static String API_DOCS = "/v2/api-docs";
	public static String CONFIGURATION_UI = "/v2/api-docs";
	public static String  SWAGGER_RESOURCES = "/swagger-resources";
	public static String  CONFIG_SECURITY = "/configuration/security";
	public static String  SWAGGER_UI = "/swagger-ui.html";
	public static String  WEB_JARS= "/webjars/**";
 
	@Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("app.controllers"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(metaData());
    }
	
	 private ApiInfo metaData() {
	        return new ApiInfoBuilder()
	                .title("Secured Notes API")
	                .description("\"Secured API for Notes\"")
	                .version("1.0.0")
	                .license("Apache License Version 2.0")
	                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0\"")
	                .contact(new Contact("Sourabh Bhavsar", "", ""))
	                .build();
	    }
	
	  @Override
	    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
	        registry.addResourceHandler("swagger-ui.html")
	                .addResourceLocations("classpath:/META-INF/resources/");
	 
	        registry.addResourceHandler("/webjars/**")
	                .addResourceLocations("classpath:/META-INF/resources/webjars/");
	    }
}
