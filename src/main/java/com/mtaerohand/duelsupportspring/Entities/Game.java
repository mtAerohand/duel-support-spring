package com.mtaerohand.duelsupportspring.Entities;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

// TODO: updatedAtに監査で取得した値を設定する
@Entity
@Data
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer modeId;

    private Integer modeDetailId;

    private LocalDateTime datetime;

    private Integer myDeckId;

    private Boolean isFirstAttack;

    private Integer opDeckId;

    private Integer result;

    private String remarks;

    private Timestamp updatedAt;
}
