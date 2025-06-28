package com.example.doma.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.doma.entity.Customers;

@Mapper
public interface CustomerMapper {

	//顧客情報を登録
	void insertCustomer(Customers customers);

	//顧客情報を名前で検索してリストに格納
	List<Customers> findByName(@Param("name") String name);
}
