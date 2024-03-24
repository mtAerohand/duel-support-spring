package com.mtaerohand.duelsupportspring.repository.ModeDetailRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import com.mtaerohand.duelsupportspring.repository.ModeRepository.Mode;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * モード詳細エンティティ
 */
@Entity
@Table(name = "mode_details")
@Data
public class ModeDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "mode_id")
    private Mode mode;

    private String name;

    private LocalDateTime startDatetime;

    private LocalDateTime endDatetime;

    private String remarks;

    private Timestamp updatedAt;
}
