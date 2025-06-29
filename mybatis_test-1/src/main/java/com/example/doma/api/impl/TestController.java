package com.example.doma.api.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.common.dto.common.ApiResponse; // ←新しいimport
import com.example.doma.entity.UserEntity;
import com.example.doma.mapper.UserMapper;
import com.example.doma.mapper.UserTestMapper;

@RestController
@RequestMapping("/api/test")
@CrossOrigin(origins = "*")
public class TestController {

	@Autowired
	private UserTestMapper userTestMapper;

	@Autowired
	private UserMapper userMapper;

	@GetMapping("/users/count")
	public ApiResponse<Map<String, Object>> testUserMapper() {
		try {

			long userCount = userMapper.countUsers();
			List<UserEntity> users = userMapper.selectAll();

			Map<String, Object> data = new HashMap<String, Object>();
			data.put("userCount", userCount);
			data.put("sampleUsers", users.stream().limit(3).collect(Collectors.toList()));
			return ApiResponse.success(data,"UserMapper動作確認成功！");
		} catch (Exception e) {
			return ApiResponse.error("MAPPER_ERROR", "UserMapper動作確認失敗", e.getMessage());
		}

	}

	@GetMapping("/connection")
	public ApiResponse<Map<String, Object>> testConnection() { // ←戻り値型変更
		try {
			Integer userCount = userTestMapper.countUsers();
			Map<String, Object> data = new HashMap<>();
			data.put("userCount", userCount);
			data.put("status", "connected");
			data.put("databaseName", "attendance_db");

			return ApiResponse.success(data, "勤怠アプリDBへの接続成功！");

		} catch (Exception e) {
			return ApiResponse.error("CONNECTION_ERROR",
					"勤怠アプリDBへの接続失敗", e.getMessage());
		}
	}

	@GetMapping("/tables")
	public ApiResponse<List<String>> getTables() { // ←戻り値型変更
		try {
			List<String> tables = userTestMapper.getTableNames();
			return ApiResponse.success(tables, "テーブル一覧取得成功");

		} catch (Exception e) {
			return ApiResponse.error("QUERY_ERROR",
					"テーブル一覧取得失敗", e.getMessage());
		}
	}

}