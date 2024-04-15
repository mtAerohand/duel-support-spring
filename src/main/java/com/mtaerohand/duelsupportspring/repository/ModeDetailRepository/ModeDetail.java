package com.mtaerohand.duelsupportspring.repository.ModeDetailRepository;

import java.time.LocalDateTime;

import com.mtaerohand.duelsupportspring.repository.BaseEntity;
import com.mtaerohand.duelsupportspring.repository.ModeRepository.Mode;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * モード詳細エンティティ
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "mode_details")
@Data
public class ModeDetail extends BaseEntity {
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
}
