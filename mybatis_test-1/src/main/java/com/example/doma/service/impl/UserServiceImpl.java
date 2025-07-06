package com.example.doma.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public UserResponse update(Long id, UserRequest request) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public void deleteById(Long id) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public List<UserResponse> findUsers(int page, int size) {
		int offset = page * size;
		
		List<UserEntity> userEntities = userMapper.selectWithPaging(offset, size);
		
		List<UserResponse> userResponses = userEntities.stream()
				.map(entity -> {
					UserResponse response = mapper.map(entity, UserResponse.class);
					
					if(entity.getRole() != null) {
						response.setRole(entity.getRole());
					}
					return response;
				})
				.collect(Collectors.toList());
		
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public long countUsers() {
		return userMapper.countUsers();
	}

	@Override
	public UserResponse findByUsername(String username) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public UserResponse findByEmail(String email) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public boolean existsByUsername(String username) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	@Override
	public List<UserResponse> findByRole(UserRole role) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public List<UserResponse> findByLocationId(Long locationId) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public void validateUserRequest(UserRequest request) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void validateUserExists(Long id) {
		// TODO 自動生成されたメソッド・スタブ

	}

}
