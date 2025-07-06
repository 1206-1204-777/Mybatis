package com.example.doma.api;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.common.dto.common.ApiResponse;
import com.example.common.dto.request.UserRequest;
import com.example.common.dto.response.UserResponse;
import com.example.common.enums.UserRole;

/**
 * ユーザー関連APIのコントローラーインターフェース
 */
@Validated
public interface UserController {
    
    // 基本CRUD
    ApiResponse<UserResponse> getUser(@PathVariable Long id);
    
    ApiResponse<UserResponse> createUser(@Valid @RequestBody UserRequest request);
    
    ApiResponse<UserResponse> updateUser(@PathVariable Long id, @Valid @RequestBody UserRequest request);
    
    ApiResponse<Void> deleteUser(@PathVariable Long id);
    
    // ページネーション付き一覧
    ApiResponse<List<UserResponse>> getUsers(
        @RequestParam(defaultValue = "0") @Min(0) int page,
        @RequestParam(defaultValue = "20") @Min(1) int size
    );
    
    // 検索機能
    ApiResponse<UserResponse> getUserByUsername(@PathVariable String username);
    
    ApiResponse<UserResponse> getUserByEmail(@RequestParam String email);
    
    ApiResponse<List<UserResponse>> getUsersByRole(@PathVariable UserRole role);
    
    ApiResponse<List<UserResponse>> getUsersByLocation(@PathVariable Long locationId);
    
    // 存在確認
    ApiResponse<Boolean> checkUsernameExists(@PathVariable String username);
    
    // 統計情報
    ApiResponse<Long> getUserCount();
}