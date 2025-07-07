package com.example.doma.config;

import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

/**
 * Swagger UI / OpenAPI 設定クラス
 * 
 * アクセスURL: http://localhost:8081/swagger-ui.html
 */
//@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("勤怠管理API (MyBatis版)")
                .description("JPAからMyBatisに移行した勤怠管理システムのREST API仕様書")
                .version("v1.0")
                .contact(new Contact()
                    .name("開発チーム")
                    .email("dev@example.com"))
                .license(new License()
                    .name("MIT License")
                    .url("https://opensource.org/licenses/MIT")))
            .addServersItem(new Server()
                .url("http://localhost:8081")
                .description("開発環境"))
            .addServersItem(new Server()
                .url("http://localhost:8080")
                .description("勤怠アプリ連携用"));
    }
}