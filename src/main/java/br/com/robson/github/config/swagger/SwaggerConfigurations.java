package br.com.robson.github.config.swagger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

import com.fasterxml.classmate.TypeResolver;

import br.com.robson.github.dto.GithubUser;
import br.com.robson.github.model.Owner;
import br.com.robson.github.model.Repository;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfigurations {
	
	private final TypeResolver typeResolver;

	public SwaggerConfigurations(final TypeResolver typeResolver) {
	    this.typeResolver = typeResolver;
	}

	@Bean
	public Docket repositoriesApi() {
		
		List<SecurityScheme> schemeList = new ArrayList<>();
        schemeList.add(new ApiKey("authkey", HttpHeaders.AUTHORIZATION , "header"));
		
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("br.com.robson.github"))
				.paths(PathSelectors.ant("/**"))
				.build()
				.ignoredParameterTypes(Owner.class, Repository.class)
				.securitySchemes(schemeList)	
				.apiInfo(getApiInfo())
				.additionalModels(typeResolver.resolve(GithubUser.class))
				.globalOperationParameters(Arrays.asList(
						new ParameterBuilder()
						.name("Authorization")
						.modelRef(new ModelRef("string"))
						.parameterType("header")
						.build()));
	}
	
	private ApiInfo getApiInfo() {
	    return new ApiInfo(
	            "API REST Repo GitHub",
	            "API REST para listar todos os repositórios de um determinado usuário do GitHub.",
	            "1.0.0",
	            null,
	            new Contact("Robson Bonfim",null,"robsonf.bonfim@gmail.com"),
	            "Apache License Version 2.0",
	            "https://www.apache.org/licenses/LICENSE-2.0",
	            Collections.emptyList()
	    );
	}

}
