package com.example.doma.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserTestMapper {
	 // 勤怠アプリのusersテーブル件数確認
    @Select("SELECT COUNT(*) FROM users")
    Integer countUsers();

    // 勤怠アプリのテーブル一覧確認
    @Select("SELECT table_name FROM information_schema.tables WHERE table_schema = 'public'")
    List<String> getTableNames();

    // usersテーブルの構造確認
    @Select("SELECT column_name, data_type FROM information_schema.columns WHERE table_name = 'users' ORDER BY ordinal_position")
    List<Map<String, Object>> getUserColumns();

    // サンプルユーザーデータ取得（最大5件）
    @Select("SELECT id, username, email, role FROM users LIMIT 5")
    List<Map<String, Object>> getSampleUsers();

}
