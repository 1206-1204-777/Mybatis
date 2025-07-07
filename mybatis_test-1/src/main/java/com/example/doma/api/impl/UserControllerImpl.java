package com.example.doma.api.impl;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
		"http://localhost:8080", // 勤怠アプリ
		"http://localhost:3000" // フロントエンド開発用
})

@AllArgsConstructor
public class UserControllerImpl implements UserController {

	private final UserService userService;

	@Override
	@GetMapping("/{id}")
	public ApiResponse<UserResponse> getUser(@PathVariable Long id) {
		try {
			UserResponse users = userService.findById(id);
			return ApiResponse.success(users);
		} catch (UserNotFoundException e) {
			e.getStackTrace();
			return ApiResponse.error("USER_NOT_FOUND", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return ApiResponse.error("INTERNAL_ERROR", "システムエラーが発生しました: " + e.getMessage());
		}

	}

	@Override
	@PostMapping
	public ApiResponse<UserResponse> createUser(@Valid @RequestBody UserRequest request) {
		try {
			UserResponse users = userService.create(request);
			return ApiResponse.success(users);
		} catch (UserNotFoundException e) {
			e.getStackTrace();
			return ApiResponse.error("USER_NOT_FOUND", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return ApiResponse.error("INTERNAL_ERROR", "システムエラーが発生しました: " + e.getMessage());
		}

	}

	@Override
	@PutMapping("/{id}")
	public ApiResponse<UserResponse> updateUser(@PathVariable Long id, @Valid @RequestBody UserRequest request) {
		try {
			UserResponse users = userService.update(id, request);
			return ApiResponse.success(users);
		} catch (UserNotFoundException e) {
			e.getStackTrace();
			return ApiResponse.error("USER_NOT_FOUND", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return ApiResponse.error("INTERNAL_ERROR", "システムエラーが発生しました: " + e.getMessage());
		}

	}

	@Override
	@DeleteMapping("/{id}")
	public ApiResponse<Void> deleteUser(@PathVariable Long id) {
		try {
			userService.deleteById(id);
			return ApiResponse.success(null, "ユーザーが正常に削除されました");
		} catch (UserNotFoundException e) {
			e.getStackTrace();
			return ApiResponse.error("USER_NOT_FOUND", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return ApiResponse.error("INTERNAL_ERROR", "システムエラーが発生しました: " + e.getMessage());
		}

	}

	@Override
	@GetMapping
	public ApiResponse<List<UserResponse>> getUsers(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "20") int size) {
		try {

			List<UserResponse> users = userService.findUsers(page, size);
			long total = userService.countUsers();

			PageInfo pageInfo = PageInfo.of(page, size, total);
			return ApiResponse.success(users, pageInfo);

		} catch (Exception e) {
			e.printStackTrace();
			return ApiResponse.error("INTERNAL_ERROR", "システムエラーが発生しました: " + e.getMessage());
		}

	}

	@Override
	@GetMapping("/username/{username}")
	public ApiResponse<UserResponse> getUserByUsername(@PathVariable String username) {
		try {
			UserResponse users = userService.findByUsername(username);
			return ApiResponse.success(users);
		} catch (UserNotFoundException e) {
			e.getStackTrace();
			return ApiResponse.error("USER_NOT_FOUND", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return ApiResponse.error("INTERNAL_ERROR", "システムエラーが発生しました: " + e.getMessage());
		}

	}

	@Override
	@GetMapping("/email")
	public ApiResponse<UserResponse> getUserByEmail(@RequestParam String email) {
		try {
			UserResponse users = userService.findByEmail(email);
			return ApiResponse.success(users);
		} catch (UserNotFoundException e) {
			e.getStackTrace();
			return ApiResponse.error("USER_NOT_FOUND", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return ApiResponse.error("INTERNAL_ERROR", "システムエラーが発生しました: " + e.getMessage());
		}

	}

	@Override
	@GetMapping("/role/{role}")
	public ApiResponse<List<UserResponse>> getUsersByRole(@PathVariable UserRole role) {
		try {
			List<UserResponse> users = userService.findByRole(role);
			return ApiResponse.success(users);
		} catch (UserNotFoundException e) {
			e.getStackTrace();
			return ApiResponse.error("USER_NOT_FOUND", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return ApiResponse.error("INTERNAL_ERROR", "システムエラーが発生しました: " + e.getMessage());
		}

	}

	@Override
	@GetMapping("/location/{locationId}")
	public ApiResponse<List<UserResponse>> getUsersByLocation(@PathVariable Long locationId) {
		try {
			List<UserResponse> user = userService.findByLocationId(locationId);
			return ApiResponse.success(user);
		} catch (UserNotFoundException e) {
			e.getStackTrace();
			return ApiResponse.error("USER_NOT_FOUND", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return ApiResponse.error("INTERNAL_ERROR", "システムエラーが発生しました: " + e.getMessage());
		}

	}

	@Override
	@GetMapping("/username/{username}/exists")
	public ApiResponse<Boolean> checkUsernameExists(@PathVariable String username) {
		try {
			boolean exists = userService.existsByUsername(username);
			return ApiResponse.success(exists, exists ? "ユーザー名は既に使用されています" : "ユーザー名は使用可能です");
		} catch (UserNotFoundException e) {
			e.getStackTrace();
			return ApiResponse.error("USER_NOT_FOUND", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return ApiResponse.error("INTERNAL_ERROR", "システムエラーが発生しました: " + e.getMessage());
		}

	}

	@Override
	@GetMapping("/count")
	public ApiResponse<Long> getUserCount() {
		try {
			long count = userService.countUsers();
			return ApiResponse.success(count);
		} catch (Exception e) {
			e.printStackTrace();
			return ApiResponse.error("INTERNAL_ERROR", "システムエラーが発生しました: " + e.getMessage());
		}
	}

}
