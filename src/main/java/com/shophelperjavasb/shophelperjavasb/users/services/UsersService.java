package com.shophelperjavasb.shophelperjavasb.users.services;

import com.shophelperjavasb.shophelperjavasb.users.dto.ProfileDto;
import com.shophelperjavasb.shophelperjavasb.users.dto.UserDto;
import com.shophelperjavasb.shophelperjavasb.users.dto.UsersPage;

public interface UsersService {
    UsersPage getAll();

    ProfileDto getProfile(Long currentUserId);

    UserDto getUser(Long userId);
}
