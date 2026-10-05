package com.studex.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import java.time.Instant;
import java.util.UUID;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

// Thuộc tính JPA dùng chung cho entity tương lai; class này không tạo bảng riêng.
@MappedSuperclass
public abstract class BaseEntity {

    // Hibernate sinh UUID khi lưu entity lần đầu.
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    // Lưu thời gian tạo cố định của entity.
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    // Cập nhật thời gian khi entity được thay đổi.
    @UpdateTimestamp
    @Column(nullable = false)
    private Instant updatedAt;

    // Constructor không tham số phục vụ JPA.
    protected BaseEntity() {
    }

    // Trả định danh do Hibernate quản lý.
    public UUID getId() {
        return id;
    }

    // Trả thời điểm tạo entity.
    public Instant getCreatedAt() {
        return createdAt;
    }

    // Trả thời điểm cập nhật gần nhất.
    public Instant getUpdatedAt() {
        return updatedAt;
    }
}
