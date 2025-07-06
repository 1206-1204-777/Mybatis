package com.example.doma.service;

import java.util.List;

import com.example.common.dto.request.UserRequest;
import com.example.common.dto.response.UserResponse;
import com.example.common.enums.UserRole;

/**
 * ユーザー関連のビジネスロジックを定義
 */
public interface UserService {
    
    // 基本CRUD
    UserResponse findById(Long id);
    
    UserResponse create(UserRequest request);
    
    UserResponse update(Long id, UserRequest request);
    
    void deleteById(Long id);
    
    // ページネーション付き一覧
    List<UserResponse> findUsers(int page, int size);
    
    long countUsers();
    
    // UserRepositoryの機能移行
    UserResponse findByUsername(String username);
    
    UserResponse findByEmail(String email);
    
    boolean existsByUsername(String username);
    
    List<UserResponse> findByRole(UserRole role);
    
    List<UserResponse> findByLocationId(Long locationId);
    
    // バリデーション関連
    void validateUserRequest(UserRequest request);
    
    void validateUserExists(Long id);
}