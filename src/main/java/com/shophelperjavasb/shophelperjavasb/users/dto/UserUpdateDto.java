package com.shophelperjavasb.shophelperjavasb.users.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserUpdateDto {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
}
