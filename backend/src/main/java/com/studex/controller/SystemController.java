package com.studex.controller;

import com.studex.dto.response.SystemStatusResponse;
import com.studex.service.SystemService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// API kiểm tra kết nối giữa frontend, backend và database.
@RestController
@RequestMapping("/api/v1/system")
public class SystemController {

    private final SystemService systemService;

    // Nhận service qua constructor để dependency rõ ràng.
    public SystemController(SystemService systemService) {
        this.systemService = systemService;
    }

    // Trả trạng thái hệ thống mà không thay đổi dữ liệu.
    @GetMapping("/status")
    public SystemStatusResponse getStatus() {
        return systemService.getStatus();
    }
}
