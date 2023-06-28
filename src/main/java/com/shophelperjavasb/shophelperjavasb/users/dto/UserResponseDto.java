package com.shophelperjavasb.shophelperjavasb.users.dto;

import com.shophelperjavasb.shophelperjavasb.users.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserResponseDto {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private Long addressId;

    public static UserResponseDto from(User user) {
        return UserResponseDto.builder()
            .id(user.getId())
            .username(user.getUsername())
            .firstName(user.getFirstName())
            .lastName(user.getLastName())
            .email(user.getEmail())
            .addressId(user.getAddress().getId())
            .build();
    }

    public static List<UserResponseDto> from(List<User> users) {
        return users.stream()
            .map(UserResponseDto::from)
            .collect(Collectors.toList());
    }
}
