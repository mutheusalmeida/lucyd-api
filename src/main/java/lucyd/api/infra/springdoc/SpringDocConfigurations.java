package lucyd.api.infra.springdoc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SpringDocConfigurations {
	
	@Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(new Info()
                    .title("Lucyd API")
                    .description("Decision engine built to help create, delete, edit and execute a policy.")
                    .contact(new Contact()
                    .name("Matheus Almeida")
                    .email("mutheusalmeida@gmail.com")));
	}
}
