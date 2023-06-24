package com.shophelperjavasb.shophelperjavasb.users.controllers.api;

import com.shophelperjavasb.shophelperjavasb.users.dto.NewUserDto;
import com.shophelperjavasb.shophelperjavasb.users.dto.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tags(value = {
    @Tag(name = "Authentication")
})
@RequestMapping("/api/auth")
public interface AuthApi {
    @Operation(summary = "SignIn")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "User",
            content = {
                @Content(mediaType = "application/json",
                    schema = @Schema(implementation = UserDto.class))
            }
        )
    })
    @PostMapping("/signup")
    ResponseEntity<UserDto> signUp(@RequestBody NewUserDto newUserDto);

    @PostMapping("/login")
    void login();

    @GetMapping("/logout")
    void logout();
}
