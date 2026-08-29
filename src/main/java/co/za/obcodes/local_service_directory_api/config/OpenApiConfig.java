/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.za.obcodes.local_service_directory_api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Obakeng Phale
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI localServiceDirectoryOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Local Services Directory API")
                        .description("REST API for discovering local services " +
                                "(clinics, taxi ranks, government offices, libraries, " +
                                "police stations) in the Soweto area.")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Obakeng Phale")
                                .email("your.email@example.com"))
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT")));
    }
}
