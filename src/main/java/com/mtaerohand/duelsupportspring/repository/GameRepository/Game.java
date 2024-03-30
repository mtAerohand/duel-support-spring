package com.mtaerohand.duelsupportspring.repository.GameRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * 試合情報エンティティ
 */
@Entity
@Table(name = "games")
@Data
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String userId;

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
