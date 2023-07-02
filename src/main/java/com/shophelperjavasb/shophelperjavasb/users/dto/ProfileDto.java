package com.shophelperjavasb.shophelperjavasb.users.dto;

import com.shophelperjavasb.shophelperjavasb.users.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProfileDto {
    private Long  id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String createdDateTime;
    private String role;

    public static ProfileDto from(User user) {
        return ProfileDto.builder()
            .id(user.getId())
            .username(user.getUsername())
            .firstName(user.getFirstName())
            .lastName(user.getLastName())
            .email(user.getEmail())
            .role(user.getRole().name())
            .createdDateTime(user.getCreatedDate().toString())
            .build();
    }
}
