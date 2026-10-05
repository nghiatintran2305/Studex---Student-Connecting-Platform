package com.studex.dto.response;

import java.time.Instant;

// DTO bất biến chứa trạng thái database, phiên bản runtime và thời điểm kiểm tra.
public record SystemStatusResponse(
        String application,
        String database,
        String javaVersion,
        String hibernateVersion,
        Instant checkedAt) {
}
