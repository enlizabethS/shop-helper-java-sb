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
public class UserDto {
    private Long  id;
    private String username;
    private String email;

    public static UserDto from(User user) {
        return UserDto.builder()
            .id(user.getId())
            .username(user.getUsername())
            .email(user.getEmail())
            .build();
    }

    public static List<UserDto> from(List<User> users) {
        return users.stream()
            .map(UserDto::from)
            .collect(Collectors.toList());
    }
}
