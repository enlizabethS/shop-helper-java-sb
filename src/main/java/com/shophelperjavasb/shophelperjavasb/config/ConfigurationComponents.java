package com.shophelperjavasb.shophelperjavasb.config;

import com.shophelperjavasb.shophelperjavasb.config.details.StandardResponseDto;
import io.swagger.v3.core.converter.AnnotatedType;
import io.swagger.v3.core.converter.ModelConverters;
import io.swagger.v3.core.converter.ResolvedSchema;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static com.shophelperjavasb.shophelperjavasb.config.OpenApiDocumentation.*;

@Configuration
public class ConfigurationComponents {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                    .allowedOrigins("http://localhost:3000")
                    .allowCredentials(true)
//                    .allowedMethods("HEAD","GET","POST","PUT","DELETE","PATCH")
//                    .allowedHeaders("*")
                ;
            }
        };
    }

    @Bean
    public OpenAPI openApi() {
        ResolvedSchema resolvedSchema = ModelConverters.getInstance()
            .resolveAsResolvedSchema(
                new AnnotatedType(StandardResponseDto.class).resolveAsRef(false));

        return new OpenAPI()
            .components(new Components()
                .addSchemas("EmailAndPassword", emailAndPassword())
                .addSecuritySchemes("cookieAuth", securityScheme())
                .addSchemas("StandardResponseDto", resolvedSchema.schema.description("StandardResponseDto")))
            .addSecurityItem(buildSecurity())
            .paths(buildAuthenticationPath())
            .info(new Info().title("Todo Service API").version("0.1"));
    }
}
