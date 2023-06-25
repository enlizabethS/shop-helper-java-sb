package com.shophelperjavasb.shophelperjavasb.config;

import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.Paths;
import io.swagger.v3.oas.models.headers.Header;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.parameters.RequestBody;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

import java.util.Collections;

public class OpenApiDocumentation {
    static Paths buildAuthenticationPath() {
        return new Paths()
            .addPathItem("/api/auth/login", buildAuthenticationPathItem())
            .addPathItem("/api/auth/logout", buildLogoutPathItem());
    }

    private static PathItem buildLogoutPathItem() {
        return new PathItem().post(
            new Operation()
                .addTagsItem("Authentication")
                .responses(new ApiResponses()
                    .addApiResponse("200", new ApiResponse().description("Successful logout"))));
    }

    private static PathItem buildAuthenticationPathItem() {
        return new PathItem().post(
            new Operation()
                .addTagsItem("Authentication")
                .requestBody(buildAuthenticationRequestBody())
                .responses(new ApiResponses()
                    .addApiResponse("200",
                        new ApiResponse()
                            .description("Successful authentication")
                            .content(new Content().addMediaType("application/json",
                                new MediaType().schema(new Schema<>().$ref("StandardResponseDto"))))
                            .headers(
                                Collections
                                    .singletonMap("Set-Cookie",
                                        new Header()
                                            .example("JSESSIONID=1234")
                                            .description("Идентификатор сессии"))))
                    .addApiResponse("401",
                        new ApiResponse()
                            .description("Wrong login or password")
                            .content(new Content()
                                .addMediaType("application/json",
                                    new MediaType()
                                        .schema(new Schema<>().$ref("StandardResponseDto")))))));
    }

    static RequestBody buildAuthenticationRequestBody() {
        return new RequestBody().content(
            new Content()
                .addMediaType("application/x-www-form-urlencoded",
                    new MediaType()
                        .schema(new Schema<>()
                            .$ref("EmailAndPassword"))));
    }

    static SecurityRequirement buildSecurity() {
        return new SecurityRequirement().addList("CookieAuthentication");
    }

    static Schema<?> emailAndPassword() {
        return new Schema<>()
            .type("object")
            .description("Email и пароль пользователя")
            .addProperty("username", new Schema<>().type("string"))
            .addProperty("password", new Schema<>().type("string"));
    }

    static SecurityScheme securityScheme() {
        return new SecurityScheme()
            .name("cookieAuth")
            .type(SecurityScheme.Type.APIKEY)
            .in(SecurityScheme.In.COOKIE)
            .name("JSESSOINID");
    }
}

//@Configuration
//public class OpenApiDocumentation {
//    @Bean
//    public OpenAPI customOpenApi() {
//        return new OpenAPI()
//            .paths(buildAuthenticationPath())
//            .components(buildComponents())
//            .info(buildInfo());
//    }
//
//    private Paths buildAuthenticationPath() {
//        return new Paths()
//            .addPathItem("/api/auth/login", buildAuthenticationPathItem());
//    }
//
//    private PathItem buildAuthenticationPathItem() {
//        return new PathItem().post(
//            new Operation()
//                .requestBody(buildAuthenticationRequestBody())
//                .responses(buildAuthenticationResponses())
//        );
//    }
//
//    private RequestBody buildAuthenticationRequestBody() {
//        return new RequestBody().content(
//            new Content()
//                .addMediaType("application/x-www-form-urlencoded",
//                    new MediaType()
//                        .schema(new Schema<>()
////                                .$ref("UsernameAndPassword")))
//                            .type("object")
//                            .description("что-то там...")
//                            .addProperty("username", new Schema<>().type("string"))
//                            .addProperty("password", new Schema<>().type("string"))))
//        );
//    }
//
//    private ApiResponses buildAuthenticationResponses() {
//        return new ApiResponses()
//            .addApiResponse("200",
//                new ApiResponse()
//                    .content(new Content()
//                        .addMediaType("application/json", new MediaType().schema(new Schema<>()
//                            .addProperty("username", new Schema<>().type("string"))
//                            .addProperty("email", new Schema<>().type("string"))
//                        )))
//            );
//    }
//
//    private Info buildInfo() {
//        return new Info()
//            .title("Shop-helper")
//            .version("1.0.0")
//            .title("Shop REST API");
//    }
//
//    private Components buildComponents() {
//        Schema<?> usernameAndPassword = new Schema<>()
//            .type("object")
//            .description("Username and password of the user")
//            .addProperty("username", new Schema<>().type("string"))
//            .addProperty("password", new Schema<>().type("string"));
//
//        return new Components()
//            .addSchemas("UsernameAndPassword", usernameAndPassword);
//    }
//}
