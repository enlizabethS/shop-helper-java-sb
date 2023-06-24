package com.shophelperjavasb.shophelperjavasb.config;

import io.swagger.v3.oas.models.*;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.parameters.RequestBody;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpinApiConfig {
    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
            .paths(buildAuthenticationPath())
            .components(buildComponents())
            .info(buildInfo());
    }

    private Paths buildAuthenticationPath() {
        return new Paths()
            .addPathItem("/api/auth/login", buildAuthenticationPathItem());
    }

    private PathItem buildAuthenticationPathItem() {
        return new PathItem().post(
            new Operation()
                .requestBody(buildAuthenticationRequestBody())
                .responses(buildAuthenticationResponses())
        );
    }

    private RequestBody buildAuthenticationRequestBody() {
        return new RequestBody().content(
            new Content()
                .addMediaType("application/x-www-form-urlencoded",
                    new MediaType()
                        .schema(new Schema<>()
//                                .$ref("UsernameAndPassword")))
                            .type("object")
                            .description("что-то там...")
                            .addProperty("username", new Schema<>().type("string"))
                            .addProperty("password", new Schema<>().type("string"))))
        );
    }

    private ApiResponses buildAuthenticationResponses() {
        return new ApiResponses()
            .addApiResponse("200",
                new ApiResponse()
                    .content(new Content()
                        .addMediaType("application/json", new MediaType().schema(new Schema<>()
                            .addProperty("username", new Schema<>().type("string"))
                            .addProperty("email", new Schema<>().type("string"))
                        )))
            );
    }

    private Info buildInfo() {
        return new Info()
            .title("Shop-helper")
            .version("1.0.0")
            .title("Shop REST API");
    }

    private Components buildComponents() {
        Schema<?> usernameAndPassword = new Schema<>()
            .type("object")
            .description("Username and password of the user")
            .addProperty("username", new Schema<>().type("string"))
            .addProperty("password", new Schema<>().type("string"));

        return new Components()
            .addSchemas("UsernameAndPassword", usernameAndPassword);
    }
}
