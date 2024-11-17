package my.cld.library.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition
public class SwaggerConfig {

    @Bean
    public OpenAPI docket() {
        return new OpenAPI()
                .info(new Info()
                        .title("Library Management System")
                        .description("APIs for borrowing books in the library")
                        .version("1.0"));
    }


}
