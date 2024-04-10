package com.mtaerohand.duelsupportspring.repository.DeckRepository;

import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * デッキ情報エンティティ
 */
@Data
@Table(name = "decks")
@Entity
// TODO: 監査情報を自動で挿入したい
public class Deck {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String pronounce;

    private String remarks;

    private Timestamp updatedAt;
}
