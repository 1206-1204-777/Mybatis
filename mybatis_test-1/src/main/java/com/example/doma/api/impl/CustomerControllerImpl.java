package com.example.doma.api.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.doma.api.CustomerController;
import com.example.doma.entity.Customers;
import com.example.doma.service.CustomerService;

@RestController
@RequestMapping("/api/customers")
public class CustomerControllerImpl implements CustomerController {

	private final CustomerService customerService;

	@Autowired
	public CustomerControllerImpl(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	//顧客情報登録
	@Override
	@PostMapping
	public void registerCustomer(@RequestBody Customers customers) {
		customerService.registerCustomer(customers);
	}
	
	//顧客情報を名前で検索
	@Override
	@GetMapping("/search")
	public List<Customers> searchCustomer(@RequestParam String name){
		return customerService.searchCustomersByName(name);
	}
}
