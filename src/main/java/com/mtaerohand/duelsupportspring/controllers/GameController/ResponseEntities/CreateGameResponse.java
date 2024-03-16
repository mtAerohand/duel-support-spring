package com.mtaerohand.duelsupportspring.Controllers.GameController.ResponseEntities;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class CreateGameResponse {
    private Integer id;
    private Integer modeId;
    private Integer modeDetailId;
    private LocalDateTime datetime;
    private Integer myDeckId;
    private Boolean isFirstAttack;
    private Integer opDeckId;
    private Integer result;
    private String remarks;
}
