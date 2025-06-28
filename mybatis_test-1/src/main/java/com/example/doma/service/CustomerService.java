package com.example.doma.service;

import java.util.List;

import com.example.doma.entity.Customers;

public interface CustomerService {
	//顧客情報登録
	public void registerCustomer(Customers customers);

	//顧客情報を名前で検索
	public List<Customers> searchCustomersByName(String name);
}
