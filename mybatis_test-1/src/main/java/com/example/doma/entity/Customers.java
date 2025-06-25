package com.example.doma.entity;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Customers {

	private Integer id;
	private String name;
	private String email;
	private String phone;
	private String address;
	private LocalDateTime createdAt;
}
