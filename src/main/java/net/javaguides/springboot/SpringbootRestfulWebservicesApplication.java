package net.javaguides.springboot;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Rest api documentation",
				description = "Rest api documentation",
				version = "v1.0",
				contact = @Contact(
						name = "Dimitrios",
						email = "dimvourd@gmail.com",
						url = "linkedin.com/dimvourd"
				),
				license = @License(
						name="Apache 2.0",
						url = "linkedin.com/dimvourd"
				)

		),externalDocs = @ExternalDocumentation(
				description = "Spring boot user management documentation",
				url = "linkedin.com/dimvourd"
)
)
public class SpringbootRestfulWebservicesApplication {
	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}
	public static void main(String[] args) {
		SpringApplication.run(SpringbootRestfulWebservicesApplication.class, args);
	}

}
