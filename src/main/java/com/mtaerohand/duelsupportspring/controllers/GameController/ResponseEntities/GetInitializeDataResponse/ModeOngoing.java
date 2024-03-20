package com.mtaerohand.duelsupportspring.Controllers.GameController.ResponseEntities.GetInitializeDataResponse;

import com.mtaerohand.duelsupportspring.Controllers.GameController.FrontEntities.FrontMode;
import com.mtaerohand.duelsupportspring.Controllers.GameController.FrontEntities.FrontModeDetail;

import lombok.Data;

@Data
public class ModeOngoing {
    private FrontMode modeOngoing;
    private FrontModeDetail modeDetailOngoing;
}
