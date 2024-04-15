package com.mtaerohand.duelsupportspring.repository;

import java.sql.Timestamp;

import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

/**
 * エンティティのBaseクラス
 */
@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {
    @LastModifiedDate
    private Timestamp updatedAt;
}
