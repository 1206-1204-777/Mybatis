package com.example.common.dto.request;

import java.time.LocalTime;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import com.example.common.enums.UserRole;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ユーザー登録・更新用のリクエストDTO
 * POST /api/v1/users
 * PUT /api/v1/users/{id}
 */
@Data
@NoArgsConstructor
public class UserRequest {
    
    @NotBlank(message = "ユーザー名は必須です")
    private String username;
    
    @NotBlank(message = "パスワードは必須です")
    private String password;
    
    @Email(message = "有効なメールアドレスを入力してください")
    @NotBlank(message = "メールアドレスは必須です")
    private String email;
    
    @NotNull(message = "ユーザーロールは必須です")
    private UserRole role;
    
    private Long locationId;
    
    private LocalTime defaultStartTime;
    
    private LocalTime defaultEndTime;
}