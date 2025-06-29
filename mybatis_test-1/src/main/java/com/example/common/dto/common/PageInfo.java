package com.example.common.dto.common;

import lombok.Data;
import lombok.NoArgsConstructor;

/**ページング用のクラス
ここにはページングに関する設定が記述されている*/
@Data
@NoArgsConstructor
public class PageInfo {

	private int page;
	private int size;
	private long total;
	private int totalPages;
	private boolean hasNext;
	private boolean hasPrevious;

	//ページングの値を設定
	public PageInfo(int page, int size, long total) {
		this.page = page;
		this.size = size;
		this.total = total;
		this.totalPages = (int) Math.ceil((double) total / size);
		this.hasNext = page < totalPages - 1;
		this.hasPrevious = page > 0;
	}

	// 便利メソッド（値の初期化）
	public static PageInfo of(int page, int size, long total) {
		return new PageInfo(page, size, total);
	}

	public boolean isEmpty() {
		return total == 0;
	}

	public boolean isFirst() {
		return page == 0;
	}
	
	public boolean isLast() {
		return page >= totalPages -1;
	}

}
