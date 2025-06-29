package com.example.doma.entity;

import java.time.LocalTime;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LocationEntity {

	private Long id;
	private String name;
	private LocalTime startTime;
	private LocalTime endTime;
	private Long createdBy;
}
