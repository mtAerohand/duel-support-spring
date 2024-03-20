package com.mtaerohand.duelsupportspring.Controllers.GameController.FrontEntities;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class FrontGame {
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
