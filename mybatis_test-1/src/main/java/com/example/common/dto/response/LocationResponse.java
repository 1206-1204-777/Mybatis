package com.example.common.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

/**勤務地情報のレスポンス用dto*/
@Data
@NoArgsConstructor
public class LocationResponse {
	private Long id;
	private String startTime;
	private String endTime;
	private Long createdBy;

}
