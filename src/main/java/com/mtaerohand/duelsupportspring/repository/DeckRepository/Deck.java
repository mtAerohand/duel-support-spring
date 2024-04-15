package com.mtaerohand.duelsupportspring.repository.DeckRepository;

import com.mtaerohand.duelsupportspring.repository.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * デッキ情報エンティティ
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "decks")
@Entity
// TODO: 監査情報を自動で挿入したい
public class Deck extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String pronounce;

    private String remarks;
}
