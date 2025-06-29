package com.example.doma.entity;

import java.time.LocalTime;

import com.example.common.enums.UserRole;

import lombok.Data;
import lombok.NoArgsConstructor;

/**MyBatis用のUserEntity*/
@Data
@NoArgsConstructor
public class UserEntity {
	private Long id;
	private String username;
	private String email;
	private UserRole role;
	private Long LocationId;//勤務地ID
	private LocalTime defaultStartTime;
	private LocalTime defaultendTime;
}
