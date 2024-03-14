package com.mtaerohand.duelsupportspring.Controllers.GameController.ResponseEntities.GetModeDetailsOngoingResponse.InternalDTO;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ModeDetailDTO {
    private Integer id;
    private String name;
    private LocalDateTime startDatetime;
    private LocalDateTime endDatetime;
}
