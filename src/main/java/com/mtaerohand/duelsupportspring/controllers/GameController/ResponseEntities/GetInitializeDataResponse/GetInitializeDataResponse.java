package com.mtaerohand.duelsupportspring.Controllers.GameController.ResponseEntities.GetInitializeDataResponse;

import java.util.List;

import com.mtaerohand.duelsupportspring.Controllers.GameController.FrontEntities.FrontDeck;
import com.mtaerohand.duelsupportspring.Controllers.GameController.FrontEntities.FrontMode;

import lombok.Data;

@Data
public class GetInitializeDataResponse {
    private List<FrontDeck> decks;
    private List<FrontMode> modes;
    private List<ModeOngoing> modesOngoing;
}
