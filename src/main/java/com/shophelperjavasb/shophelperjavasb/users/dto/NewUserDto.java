package com.shophelperjavasb.shophelperjavasb.users.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class NewUserDto {
    private String username;
    private String email;
    private String password;
}
