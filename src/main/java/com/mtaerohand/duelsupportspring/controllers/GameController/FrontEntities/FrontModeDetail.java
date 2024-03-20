package com.mtaerohand.duelsupportspring.Controllers.GameController.FrontEntities;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class FrontModeDetail {
    private Integer id;
    private Integer modeId;
    private String name;
    private LocalDateTime startDatetime;
    private LocalDateTime endDatetime;
    private String remarks;
}
