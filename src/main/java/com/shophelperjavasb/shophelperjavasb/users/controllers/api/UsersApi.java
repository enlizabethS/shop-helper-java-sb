package com.shophelperjavasb.shophelperjavasb.users.controllers.api;

import com.shophelperjavasb.shophelperjavasb.config.details.AuthenticatedUser;
import com.shophelperjavasb.shophelperjavasb.users.dto.NewUserDto;
import com.shophelperjavasb.shophelperjavasb.users.dto.ProfileDto;
import com.shophelperjavasb.shophelperjavasb.users.dto.UserDto;
import com.shophelperjavasb.shophelperjavasb.users.dto.UsersPage;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tags(value = {
    @Tag(name = "Users")
})
@RequestMapping("/api/users")
public interface UsersApi {
    @GetMapping
    ResponseEntity<UsersPage> getAll();

    @GetMapping("/profile")
    ResponseEntity<ProfileDto> getProfile(@Parameter(hidden = true) @AuthenticationPrincipal AuthenticatedUser currentUser);

    @GetMapping("/{user-id}")
    ResponseEntity<UserDto> getUser(@PathVariable("user-id") Long userId);
}
