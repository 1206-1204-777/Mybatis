package com.example.doma.api.impl;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.common.dto.common.ApiResponse;
import com.example.common.dto.common.PageInfo;
import com.example.common.dto.request.UserRequest;
import com.example.common.dto.response.UserResponse;
import com.example.common.enums.UserRole;
import com.example.doma.api.UserController;
import com.example.doma.exception.UserNotFoundException;
import com.example.doma.service.UserService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin(origins = {
"http://localhost:8080",    // 勤怠アプリ
"http://localhost:3000"     // フロントエンド開発用
})

@AllArgsConstructor
public class UserControllerImpl implements UserController {

	private final UserService userService;

	@Override
	@GetMapping("/{id}")
	public ApiResponse<UserResponse> getUser(@PathVariable Long id) {
		try {
			UserResponse user = userService.findById(id);
			return ApiResponse.success(user);
		} catch (UserNotFoundException e) {
			e.getStackTrace();
			return ApiResponse.error("USER_NOT_FOUND", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return ApiResponse.error("INTERNAL_ERROR\", \"システムエラーが発生しました: ", e.getMessage());
		}

	}

	@Override
	public ApiResponse<UserResponse> createUser(@Valid UserRequest request) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public ApiResponse<UserResponse> updateUser(Long id, @Valid UserRequest request) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public ApiResponse<Void> deleteUser(Long id) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public ApiResponse<List<UserResponse>> getUsers(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "20") int size) {
		try {
			
			List<UserResponse> users= userService.findUsers(page, size);
			long total = userService.countUsers();
			
			PageInfo pageInfo = PageInfo.of(page, size, total);
			return ApiResponse.success(users,pageInfo);

		} catch (Exception e) {
			e.printStackTrace();
			return ApiResponse.error("QUERY_ERROR", "ユーザー一覧取得に失敗しました: " , e.getMessage());
		}

	}

	@Override
	public ApiResponse<UserResponse> getUserByUsername(String username) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public ApiResponse<UserResponse> getUserByEmail(String email) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public ApiResponse<List<UserResponse>> getUsersByRole(UserRole role) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public ApiResponse<List<UserResponse>> getUsersByLocation(Long locationId) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public ApiResponse<Boolean> checkUsernameExists(String username) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	@GetMapping("/count")
	public ApiResponse<Long> getUserCount() {
		try {
			long count = userService.countUsers();
			return ApiResponse.success(count);
		} catch (Exception e) {
			e.printStackTrace();
			return ApiResponse.error("COUNT_ERROR", "ユーザー総数取得に失敗しました: ", e.getMessage());
		}
	}

}
