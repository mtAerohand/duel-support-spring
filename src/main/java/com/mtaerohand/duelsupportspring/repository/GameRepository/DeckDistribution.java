package com.mtaerohand.duelsupportspring.repository.GameRepository;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * デッキ分布情報エンティティ
 */
@Data
@AllArgsConstructor
public class DeckDistribution implements Serializable {
    private Integer deckId;
    private Float ratio;
}
