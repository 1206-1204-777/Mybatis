package com.example.doma.api.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.doma.mapper.UserTestMapper;

@RestController
@RequestMapping("/api/test")
@CrossOrigin(origins =  "*")
public class TestController {

    @Autowired
    private UserTestMapper userTestMapper;

    // 基本的な接続確認
    @GetMapping("/connection")
    public Map<String, Object> testConnection() {
        Map<String, Object> result = new HashMap<>();
        
        try {
            // ユーザー数取得
            Integer userCount = userTestMapper.countUsers();
            result.put("success", true);
            result.put("userCount", userCount);
            result.put("message", "勤怠アプリDBへの接続成功！");
            
        } catch (Exception e) {
            result.put("success", false);
            result.put("error", e.getMessage());
            result.put("message", "勤怠アプリDBへの接続失敗");
        }
        
        return result;
    }

    // テーブル一覧確認
    @GetMapping("/tables")
    public Map<String, Object> getTables() {
        Map<String, Object> result = new HashMap<>();
        
        try {
            List<String> tables = userTestMapper.getTableNames();
            result.put("success", true);
            result.put("tables", tables);
            result.put("message", "テーブル一覧取得成功");
            
        } catch (Exception e) {
            result.put("success", false);
            result.put("error", e.getMessage());
        }
        
        return result;
    }

    // usersテーブル構造確認
    @GetMapping("/users/structure")
    public Map<String, Object> getUserStructure() {
        Map<String, Object> result = new HashMap<>();
        
        try {
            List<Map<String, Object>> columns = userTestMapper.getUserColumns();
            result.put("success", true);
            result.put("columns", columns);
            result.put("message", "usersテーブル構造取得成功");
            
        } catch (Exception e) {
            result.put("success", false);
            result.put("error", e.getMessage());
        }
        
        return result;
    }

    // サンプルユーザーデータ確認
    @GetMapping("/users/sample")
    public Map<String, Object> getSampleUsers() {
        Map<String, Object> result = new HashMap<>();
        
        try {
            List<Map<String, Object>> users = userTestMapper.getSampleUsers();
            result.put("success", true);
            result.put("users", users);
            result.put("count", users.size());
            result.put("message", "サンプルユーザーデータ取得成功");
            
        } catch (Exception e) {
            result.put("success", false);
            result.put("error", e.getMessage());
        }
        
        return result;
    }
}