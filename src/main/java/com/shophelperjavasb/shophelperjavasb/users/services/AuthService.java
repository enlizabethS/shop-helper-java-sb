package com.shophelperjavasb.shophelperjavasb.users.services;

import com.shophelperjavasb.shophelperjavasb.users.dto.NewUserDto;
import com.shophelperjavasb.shophelperjavasb.users.dto.UserDto;

public interface AuthService {
    UserDto signUp(NewUserDto newUserDto);
}
