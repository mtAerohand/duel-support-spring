package com.mtaerohand.duelsupportspring.Controllers.GameController.RequestEntities;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class CreateGameRequest {
    private Integer modeId;
    private Integer modeDetailId;
    private LocalDateTime datetime;
    private Integer myDeckId;
    private Boolean isFirstAttack;
    private Integer opDeckId;
    private Integer result;
    private String remarks;
}
