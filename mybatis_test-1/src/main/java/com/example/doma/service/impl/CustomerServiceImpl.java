package com.example.doma.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.doma.entity.Customers;
import com.example.doma.mapper.CustomerMapper;
import com.example.doma.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	private final CustomerMapper customerMapper;

	public CustomerServiceImpl(CustomerMapper customerMapper) {
		this.customerMapper = customerMapper;
	}

	@Override
	public void registerCustomer(Customers customers) {
		customerMapper.insertCustomer(customers);
	}

	@Override
	public List<Customers> searchCustomersByName(String name) {
		return customerMapper.findByName(name);
	}

}
