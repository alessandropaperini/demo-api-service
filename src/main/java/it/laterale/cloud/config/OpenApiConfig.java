package it.laterale.cloud.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Value("${build.version}")
    private String buildVersion;

    @Bean
    public OpenAPI openApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Demo Api")
                        .description("OpenAPI definition of Demo Api project")
                        .version(this.buildVersion)
                        .contact(new Contact()
                                .name("Alessandro Paperini")
                                .url("https://lateralecloud.it")
                                .email("alessandro@lateralecloud.it")));
    }
}
