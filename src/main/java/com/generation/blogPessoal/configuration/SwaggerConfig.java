package com.generation.blogPessoal.configuration;

import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;


@Configuration
public class SwaggerConfig {
	
	@Bean
	public OpenAPI springblogPessoalOpenAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("Projeto Blog Pessoal")
						.description("Projeto Blog Pessoal desenvolvido no segundo bloco da Generation Brasil")
						.version("v0.0.1")
						.license(new License()
								.name("Generation Brazil")
								.url("\"https://brazil.generation.org/"))
						.contact(new Contact()
								.name("Github Malu")
								.url("https://github.com/malufilinto/")
								.email("malufilinto@gmail.com")))
		.externalDocs(new ExternalDocumentation()
				.description("Github Project")
				.url("https://github.com/malufilinto/blogPessoal"));
}

	@Bean
	public OpenApiCustomiser customerGlobalResponseStatus() {
		return openApi -> {
			openApi.getPaths().values().forEach(pathItem -> pathItem.readOperations().forEach(operation -> {
				ApiResponses api = operation.getResponses();

				api.addApiResponse("200", createApiResponse("Sucesso!"));
				api.addApiResponse("201", createApiResponse("Objeto Criado!"));
				api.addApiResponse("400", createApiResponse("Erro na requisição!"));
				api.addApiResponse("401", createApiResponse("Acesso não autorizado!"));
				api.addApiResponse("500", createApiResponse("Erro na aplicação!"));
			}));
		};
	}
	
	private ApiResponse createApiResponse(String message) {
		return new ApiResponse().description(message);
	
	}
}