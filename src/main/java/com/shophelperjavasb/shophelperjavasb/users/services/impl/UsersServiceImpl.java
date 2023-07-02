package com.shophelperjavasb.shophelperjavasb.users.services.impl;

import com.shophelperjavasb.shophelperjavasb.exceptions.NotFoundException;
import com.shophelperjavasb.shophelperjavasb.users.dto.ProfileDto;
import com.shophelperjavasb.shophelperjavasb.users.dto.UserDto;
import com.shophelperjavasb.shophelperjavasb.users.dto.UsersPage;
import com.shophelperjavasb.shophelperjavasb.users.model.User;
import com.shophelperjavasb.shophelperjavasb.users.repositories.UsersRepository;
import com.shophelperjavasb.shophelperjavasb.users.services.UsersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

 @Slf4j
@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {
     private final UsersRepository usersRepository;

     @Override
     public UsersPage getAll() {
         List<User> users = usersRepository.findAll();

         return UsersPage.builder()
                 .users(UserDto.from(users))
                 .build();
     }

     @Override
     public ProfileDto getProfile(Long currentUserId) {
         User user = usersRepository.findById(currentUserId)
                 .orElseThrow(IllegalArgumentException::new);

         return ProfileDto.from(user);
     }

     @Override
     public UserDto getUser(Long userId) {
         User user = usersRepository.findById(userId)
                 .orElseThrow(() -> new NotFoundException("User with id <" + userId + "> not found"));

         return UserDto.from(user);
     }

     @Override
     public User getUserById(Long userId) {
         return usersRepository.getUserById(userId);
     }

     @Override
     public void saveUser(User existingUser) {
         usersRepository.saveUser(existingUser);
     }


     public void banUser(Long id) {
         User user = usersRepository.findById(id).orElse(null);
         if (user != null) {
             if (user.isActive()) {
                 user.setActive(false);
                 log.info("Ban user with id = {}; email: {}", user.getId(), user.getEmail());
             } else {
                 user.setActive(true);
                 log.info("Unban user with id = {}; email: {}", user.getId(), user.getEmail());
             }
         }
         usersRepository.save(user);
     }
 }


