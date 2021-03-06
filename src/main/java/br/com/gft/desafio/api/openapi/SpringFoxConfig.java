package br.com.gft.desafio.api.openapi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class SpringFoxConfig implements WebMvcConfigurer{
	

	@Bean
	public Docket apiDocket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
					.apis(RequestHandlerSelectors.basePackage("br.com.gft.desafio.api"))			
					.build()
				.apiInfo(apiInfo())
				.tags(new Tag("Cliente", "Gerência os clientes"))
				.tags(new Tag("Fornecedor", "Gerência os fornecedores"))
				.tags(new Tag("Produto", "Gerência os produtos"))
				.tags(new Tag("Vendas", "Gerência as vendas"));
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html")
		.addResourceLocations("classpath:/META-INF/resources/");
	
		registry.addResourceHandler("/webjars/**")
		.addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
	
	public ApiInfo apiInfo() {		
		return new ApiInfoBuilder()
				.title("Desafio - API")
				.description("API aberta para gerenciar vendas")
				.version("1.0")
				.contact(new Contact("Isaque Chaves", "https://www.linkedin.com/in/isaquechaves/", null))
				.build();
	}
}
