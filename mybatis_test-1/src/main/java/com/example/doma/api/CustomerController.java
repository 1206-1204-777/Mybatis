package com.example.doma.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.doma.entity.Customers;

public interface CustomerController {

	//顧客情報登録
	void registerCustomer(Customers customers);

	//顧客情報を名前で検索
	List<Customers> searchCustomer(String name);

}