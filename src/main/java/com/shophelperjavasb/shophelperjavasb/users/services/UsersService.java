package com.shophelperjavasb.shophelperjavasb.users.services;

import com.shophelperjavasb.shophelperjavasb.users.dto.NewUserDto;
import com.shophelperjavasb.shophelperjavasb.users.dto.ProfileDto;
import com.shophelperjavasb.shophelperjavasb.users.dto.UserDto;
import com.shophelperjavasb.shophelperjavasb.users.dto.UsersPage;

import java.util.List;

public interface UsersService {
    UserDto signUp(NewUserDto newUserDto);

    UsersPage getAll();

    ProfileDto getProfile(int currentUserId);

    UserDto getUser(int userId);
}
