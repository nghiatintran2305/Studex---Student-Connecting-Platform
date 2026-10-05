package com.studex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Khởi chạy backend và tự quét các package bên dưới com.studex.
@SpringBootApplication
public class StudexApplication {

    // Khởi tạo Spring context, REST server, JPA và kết nối PostgreSQL.
    public static void main(String[] args) {
        SpringApplication.run(StudexApplication.class, args);
    }
}
