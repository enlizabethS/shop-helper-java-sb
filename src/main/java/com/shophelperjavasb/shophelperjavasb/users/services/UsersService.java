package com.shophelperjavasb.shophelperjavasb.users.services;

import com.shophelperjavasb.shophelperjavasb.config.details.AuthenticatedUser;
import com.shophelperjavasb.shophelperjavasb.users.dto.ProfileDto;
import com.shophelperjavasb.shophelperjavasb.users.dto.UserResponseDto;
import com.shophelperjavasb.shophelperjavasb.users.dto.UsersPage;

public interface UsersService {
    UsersPage getAll();

    ProfileDto getProfile(AuthenticatedUser currentUser);

    UserResponseDto getUser(Long userId);
}
