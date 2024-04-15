package com.mtaerohand.duelsupportspring.repository.GameRepository;

import java.time.LocalDateTime;

import com.mtaerohand.duelsupportspring.repository.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 試合情報エンティティ
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "games")
@Data
public class Game extends BaseEntity {
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
}
