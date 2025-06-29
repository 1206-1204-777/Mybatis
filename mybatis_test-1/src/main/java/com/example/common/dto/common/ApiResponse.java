package com.example.common.dto.common;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**このクラスは全APIで使用するレスポンス形式を定義している
 * フロントとの通信規格とエラーレスポンスの設定が記述されている*/

@Data
@JsonInclude(JsonInclude.Include.NON_NULL) //オブジェクトをnullのないjson形式に変換
@NoArgsConstructor //デフォルトコンストラクタの自動生成
@AllArgsConstructor //フィールドを全て引数にとして受け取るコンストラクタを自動生成
public class ApiResponse<T> {
	private boolean success;
	private T data;
	private String message;
	private ErrorDetail error;
	private MetaData meta;
	private PageInfo pageInfo;

	//初期コンストラク
	public ApiResponse(boolean success, T data, String message) {
		this.success = true;
		this.data = data;
		this.message = "success";
		this.meta = new MetaData();

	}

	// 成功レスポンス(成功判定,メッセージ無し)
	public static <T> ApiResponse<T> success(T data) {
		ApiResponse<T> response = new ApiResponse<T>();
		response.success = true;
		response.data = data;
		response.message = "success";
		response.meta = new MetaData();
		return response;
	}

	// 成功レスポンス(成功判定,メッセージあり)
	public static <T> ApiResponse<T> success(T data, String message) {
		ApiResponse<T> response = new ApiResponse<>();
		response.success = true;
		response.data = data;
		response.message = message;
		response.meta = new MetaData();
		return response;
	}

	// ページネーション付きレスポンス(成功判定,メッセージ無し)
	public static <T> ApiResponse<T> success(T data, PageInfo pageInfo) {
		ApiResponse<T> response = new ApiResponse<>();
		response.pageInfo = pageInfo;
		response.success = true;
		response.data = data;
		response.message = "success";
		response.meta = new MetaData();
		return response;
	}

	// ページネーション付きレスポンス(成功判定,メッセージあり)
	public static <T> ApiResponse<T> success(T data, String message, PageInfo pageInfo) {
		ApiResponse<T> response = new ApiResponse<>();
		response.success = true;
		response.pageInfo = pageInfo;
		response.data = data;
		response.message = message;
		response.meta = new MetaData();
		return response;
	}

	// 失敗レスポンス(データ内容なし)
	public static <T> ApiResponse<T> error(String code, String message) {
		ApiResponse<T> response = new ApiResponse<>();
		response.success = false;
		response.error = new ErrorDetail(code, message);
		response.meta = new MetaData();
		return response;
	}

	// 失敗レスポンス(データ内容あり)
	public static <T> ApiResponse<T> error(String code, String message, Object details) {
		ApiResponse<T> response = new ApiResponse<>();
		response.success = false;
		response.message = message;
		response.error = new ErrorDetail(code, message, details);
		response.meta = new MetaData();
		return response;
	}

	//バリデーション用
	public static <T> ApiResponse<T> validationError(Object details) {
		return error("VALIDATION_ERROR", "入力値に誤りがあります", details);
	}

	@Data
	@NoArgsConstructor //デフォルトコンストラクタの自動生成
	@AllArgsConstructor //フィールドを全て引数にとして受け取るコンストラクタを自動生成
	public static class ErrorDetail {
		private String code;
		private String message;
		private Object datails;

		public ErrorDetail(String code, String message) {
			this.code = code;
			this.message = message;
		}
	}

	@Data
	public static class MetaData {
		private LocalDateTime timestamp;
		private String requestId; //レスポンスの使用者ID（ユーザーID）

		//ユーザーIDのハッシュ化あり
		public MetaData() {
			this.timestamp = LocalDateTime.now();
			this.requestId = java.util.UUID.randomUUID().toString();
		}

		//ユーザーIDのハッシュ化なし
		public MetaData(String requestId) {
			this.timestamp = LocalDateTime.now();
			this.requestId = requestId;
		}
	}

}
