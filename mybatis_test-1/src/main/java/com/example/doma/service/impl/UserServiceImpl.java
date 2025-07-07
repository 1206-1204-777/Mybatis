package com.example.doma.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.example.common.dto.request.UserRequest;
import com.example.common.dto.response.UserResponse;
import com.example.common.enums.UserRole;
import com.example.doma.entity.UserEntity;
import com.example.doma.exception.UserNotFoundException;
import com.example.doma.mapper.UserMapper;
import com.example.doma.service.UserService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserMapper userMapper;
	private final ModelMapper mapper;

	@Override
	@Transactional(readOnly = true)
	public UserResponse findById(Long id) {
		UserEntity user = userMapper.selectById(id);

		if (user == null) {
			throw new UserNotFoundException("ユーザーが見つかりません" + id);
		}
		UserResponse response = mapper.map(user, UserResponse.class);
		if (user.getRole() != null) {
			response.setRole(user.getRole());
		}
		return response;
	}

	@Override
	public UserResponse create(UserRequest request) {
		validateUserRequest(request);
		if (existsByUsername(request.getUsername())) {
			throw new IllegalArgumentException("ユーザー名 '" + request.getUsername() + "' は既に使用されています");
		}

		UserEntity entity = mapper.map(request, UserEntity.class);

		userMapper.insert(entity);

		UserResponse response = mapper.map(entity, UserResponse.class);

		if (entity.getRole() != null) {
			response.setRole(entity.getRole());
		}
		return response;
	}

	@Override
	public UserResponse update(Long id, UserRequest request) {
		validateUserRequest(request);
		UserEntity existingUser = userMapper.selectById(id);
		if (!existingUser.getUsername().equals(request.getUsername())
				&& existsByUsername(request.getUsername())) {
			throw new IllegalArgumentException("ユーザー名 '" + request.getUsername() + "' は既に使用されています");
		}

		UserEntity entity = mapper.map(request, UserEntity.class);
		entity.setId(id);
		userMapper.update(entity);

		UserResponse response = mapper.map(entity, UserResponse.class);

		if (entity.getRole() != null) {
			response.setRole(entity.getRole());
		}
		return response;
	}

	@Override
	public void deleteById(Long id) {
		validateUserExists(id);
		userMapper.deleteById(id);
	}

	@Override
	public List<UserResponse> findUsers(int page, int size) {
		int offset = page * size;

		List<UserEntity> userEntities = userMapper.selectWithPaging(offset, size);

		List<UserResponse> userResponses = userEntities.stream()
				.map(entity -> {
					UserResponse response = mapper.map(entity, UserResponse.class);

					if (entity.getRole() != null) {
						response.setRole(entity.getRole());
					}
					return response;
				})
				.collect(Collectors.toList());

		return userResponses;
	}

	@Override
	@Transactional(readOnly = true)
	public long countUsers() {
		return userMapper.countUsers();
	}

	@Override
	@Transactional(readOnly = true)
	public UserResponse findByUsername(String username) {
		UserEntity user = userMapper.selectByUsername(username);

		if (user == null) {
			throw new UserNotFoundException("ユーザー名が見つかりません" + username);
		}
		UserResponse response = mapper.map(user, UserResponse.class);
		if (user.getRole() != null) {
			response.setRole(user.getRole());
		}
		return response;
	}

	@Override
	@Transactional(readOnly = true)
	public UserResponse findByEmail(String email) {
		UserEntity user = userMapper.selectByEmail(email);

		if (user == null) {
			throw new UserNotFoundException("メールアドレスが見つかりません" + email);
		}
		UserResponse response = mapper.map(user, UserResponse.class);
		if (user.getRole() != null) {
			response.setRole(user.getRole());
		}
		return response;
	}

	@Override
	public boolean existsByUsername(String username) {
		if (username == null) {
			throw new UserNotFoundException(username + "のユーザー名が見つかりません");
		}
		return userMapper.existsByUsername(username);
	}

	@Override
	@Transactional(readOnly = true)
	public List<UserResponse> findByRole(UserRole role) {
		List<UserEntity> users = userMapper.selectByRole(role);

		if (users == null || users.isEmpty()) {
			throw new UserNotFoundException("ロール " + role + " のユーザーが見つかりません");
		}
		return users.stream()
				.map(entity -> {
					UserResponse response = mapper.map(entity, UserResponse.class);
					if (entity != null) {
						response.setRole(entity.getRole());
					}
					return response;
				})
				.collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public List<UserResponse> findByLocationId(Long locationId) {
		List<UserEntity> users = userMapper.selectByLocationId(locationId);

		if (users == null || users.isEmpty()) {
			throw new UserNotFoundException("ロール " + locationId + " のユーザーが見つかりません");
		}
		return users.stream()
				.map(entity -> {
					UserResponse response = mapper.map(entity, UserResponse.class);
					if (entity != null) {
						response.setRole(entity.getRole());
					}
					return response;
				})
				.collect(Collectors.toList());
	}

	@Override
	public void validateUserRequest(UserRequest request) {
		// null チェック
		if (request == null) {
			throw new IllegalArgumentException("ユーザーリクエストがnullです");
		}

		// 必須項目チェック
		if (!StringUtils.hasText(request.getUsername())) {
			throw new IllegalArgumentException("ユーザー名は必須です");
		}

		if (!StringUtils.hasText(request.getPassword())) {
			throw new IllegalArgumentException("パスワードは必須です");
		}

		if (!StringUtils.hasText(request.getEmail())) {
			throw new IllegalArgumentException("メールアドレスは必須です");
		}

		if (request.getRole() == null) {
			throw new IllegalArgumentException("ユーザーロールは必須です");
		}

		// 形式チェック
		if (!isValidEmail(request.getEmail())) {
			throw new IllegalArgumentException("有効なメールアドレスを入力してください");
		}

		// 文字数チェック
		if (request.getUsername().length() < 3 || request.getUsername().length() > 50) {
			throw new IllegalArgumentException("ユーザー名は3文字以上50文字以下で入力してください");
		}

		if (request.getPassword().length() < 8) {
			throw new IllegalArgumentException("パスワードは8文字以上で入力してください");
		}
	}

	@Override
	public void validateUserExists(Long id) {
		// null チェック
		if (id == null) {
			throw new IllegalArgumentException("ユーザーIDがnullです");
		}

		// 負の値チェック
		if (id <= 0) {
			throw new IllegalArgumentException("ユーザーIDは正の値である必要があります");
		}

		// 存在チェック
		UserEntity user = userMapper.selectById(id);
		if (user == null) {
			throw new UserNotFoundException("ユーザーが見つかりません。ID: " + id);
		}
	}

	// プライベートヘルパーメソッド
	private boolean isValidEmail(String email) {
		// 簡単なメール形式チェック（より厳密にする場合は正規表現を使用）
		return email != null &&
				email.contains("@") &&
				email.contains(".") &&
				email.indexOf("@") < email.lastIndexOf(".");
	}

}
