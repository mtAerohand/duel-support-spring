package com.mtaerohand.duelsupportspring.Entities;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class ModeDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer modeId;

    private String name;

    private LocalDateTime startDatetime;

    private LocalDateTime endDatetime;

    private String remarks;

    private Timestamp updatedAt;
}
