package com.shophelperjavasb.shophelperjavasb.users.services.impl;

import com.shophelperjavasb.shophelperjavasb.users.dto.NewUserDto;
import com.shophelperjavasb.shophelperjavasb.users.dto.ProfileDto;
import com.shophelperjavasb.shophelperjavasb.users.dto.UserDto;
import com.shophelperjavasb.shophelperjavasb.users.dto.UsersPage;
import com.shophelperjavasb.shophelperjavasb.users.model.User;
import com.shophelperjavasb.shophelperjavasb.users.repositories.UsersRepository;
import com.shophelperjavasb.shophelperjavasb.users.services.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDto signUp(NewUserDto newUserDto) {
        User user = User.builder()
            .username(newUserDto.getUsername())
            .email(newUserDto.getEmail())
            .hashPassword(passwordEncoder.encode(newUserDto.getPassword()))
            .build();
        usersRepository.save(user);

        return UserDto.from(user);
    }

    @Override
    public UsersPage getAll() {
        List<User> users = usersRepository.findAll();

        return UsersPage.builder()
            .users(UserDto.from(users))
            .build();
    }

    @Override
    public ProfileDto getProfile(int currentUserId) {
        User user = usersRepository.findById(currentUserId)
            .orElseThrow(IllegalArgumentException::new);

        return ProfileDto.from(user);
    }
}
