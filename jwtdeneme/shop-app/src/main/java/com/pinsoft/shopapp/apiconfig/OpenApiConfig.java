package com.pinsoft.shopapp.apiconfig;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
		info = @Info(
				title = "Shop API", 
				description = "CRUD Operation", 
				summary = "This shop-api will add and create", 
				version = "v1", 
				contact = @Contact(name = "admin", email = "help.support@gmail.com"
				)

			),
		servers = {
				@Server(
						description = "Dev",
						url = "http://localhost:8080"
				),
				@Server(
						description = "Test",
						url = "http://localhost:8080"
				)
		}

)

public class OpenApiConfig {

}
