package com.shophelperjavasb.shophelperjavasb.users.controllers;

import com.shophelperjavasb.shophelperjavasb.config.details.AuthenticatedUser;
import com.shophelperjavasb.shophelperjavasb.users.controllers.api.UsersApi;
import com.shophelperjavasb.shophelperjavasb.users.dto.NewUserDto;
import com.shophelperjavasb.shophelperjavasb.users.dto.ProfileDto;
import com.shophelperjavasb.shophelperjavasb.users.dto.UserDto;
import com.shophelperjavasb.shophelperjavasb.users.dto.UsersPage;
import com.shophelperjavasb.shophelperjavasb.users.services.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UsersController implements UsersApi {
    private final UsersService usersService;

    @Override
    public ResponseEntity<UserDto> signUp(NewUserDto newUserDto) {
        return ResponseEntity.status(201)
            .body(usersService.signUp(newUserDto));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public ResponseEntity<UsersPage> getAll() {
        return ResponseEntity.ok(usersService.getAll());
    }

    @PreAuthorize("isAuthenticated()")
    @Override
    public ResponseEntity<ProfileDto> getProfile(AuthenticatedUser currentUser) {
        int currentUserId = currentUser.getUser().getId();
        ProfileDto profile = usersService.getProfile(currentUserId);

        return ResponseEntity.ok(profile);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public ResponseEntity<UserDto> getUser(int userId) {
        return ResponseEntity.ok(usersService.getUser(userId));
    }

}
