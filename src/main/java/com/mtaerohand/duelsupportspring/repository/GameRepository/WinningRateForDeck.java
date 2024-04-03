package com.mtaerohand.duelsupportspring.repository.GameRepository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 勝率情報エンティティ
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WinningRateForDeck {
    private Float winningRate;
    private Integer winCount;
    private Integer loseCount;
}
