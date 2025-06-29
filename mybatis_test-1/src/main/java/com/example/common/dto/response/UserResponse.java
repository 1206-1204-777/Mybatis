package com.example.common.dto.response;

import java.time.LocalTime;

import com.example.common.enums.UserRole;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ユーザー情報返却用dto
 * GET /api/v1/users/{id}
 * GET /api/v1/users*/
@Data
@NoArgsConstructor
public class UserResponse {

	private Long id;
	private UserRole role;
	private String roleDisplayName; //一般ユーザーor管理者どちらかを画面に表示させるため
	private LocationResponse location;
	private LocalTime defaultStartTime;
	private LocalTime defaultEndTime;
	
	// roleの設定時に使う
	public void setRole(UserRole role) {
		this.role = role;
		this.roleDisplayName = role != null ? role.displayName() : null;
	}
}
