package com.mtaerohand.duelsupportspring.Controllers.GameController.ResponseEntities;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class GetModeDetailsOngoingResponse {
    private Integer id;
    private Integer modeId;
    private String name;
    private LocalDateTime startDatetime;
    private LocalDateTime endDatetime;
}
