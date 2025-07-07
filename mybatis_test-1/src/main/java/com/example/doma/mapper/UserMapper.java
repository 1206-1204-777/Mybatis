package com.example.doma.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.common.enums.UserRole;
import com.example.doma.entity.UserEntity;

/**
 * User用のMyBatis Mapper
 *勤怠アプリの UserRepository.javaの機能をMyBatis化
 */
@Mapper
public interface UserMapper {

	//基本操作
	UserEntity selectById(@Param("id") Long id);//id検索

	List<UserEntity> selectAll();//全件取得

	void insert(UserEntity user);//カラムの追加

	void update(UserEntity user);//ユーザー情報の更新

	void deleteById(@Param("id") Long id);//削除

	//勤怠アプリのUserRepositoryにあるカスタムメソッドに対応
	UserEntity selectByUsername(@Param("username") String username);//名前検索

	UserEntity selectByEmail(@Param("email") String email);//メールアドレスを検索

	boolean existsByUsername(@Param("username") String username);//ユーザー名が存在するか検証
	
	List<UserEntity> selectByRole(@Param("role") UserRole role);//ユーザーのロール情報を取得
	
	List<UserEntity> selectByLocationId(@Param("locationId") Long locationId);//ユーザーの勤務地情報を取得
	
	//ページネーション対応
	List<UserEntity> selectWithPaging(@Param("offset")int offset,@Param("limit") int limit);// ページ数取得
	
	long countUsers();//ユーザーの数を取得
}
