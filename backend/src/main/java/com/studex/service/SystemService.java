package com.studex.service;

import com.studex.dto.response.SystemStatusResponse;
import java.time.Instant;
import org.hibernate.Version;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

// Xử lý kiểm tra hạ tầng độc lập với controller.
@Service
public class SystemService {

    private final JdbcTemplate jdbcTemplate;

    // Nhận JDBC dùng chung datasource với JPA.
    public SystemService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Kiểm tra PostgreSQL bằng truy vấn chỉ đọc và trả phiên bản runtime.
    public SystemStatusResponse getStatus() {
        Integer result = jdbcTemplate.queryForObject("SELECT 1", Integer.class);
        return new SystemStatusResponse(
                "studex-backend",
                Integer.valueOf(1).equals(result) ? "UP" : "DOWN",
                System.getProperty("java.version"),
                Version.getVersionString(),
                Instant.now());
    }
}
