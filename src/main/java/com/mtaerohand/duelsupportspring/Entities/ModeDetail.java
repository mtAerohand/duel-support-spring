package com.mtaerohand.duelsupportspring.Entities;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
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
