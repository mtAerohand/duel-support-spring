package com.mtaerohand.duelsupportspring.Controllers.GameController.ResponseEntities.GetInitializeDataResponse;

import java.util.List;

import com.mtaerohand.duelsupportspring.Controllers.GameController.ResponseEntities.GetInitializeDataResponse.InternalDTO.DeckDTO;
import com.mtaerohand.duelsupportspring.Controllers.GameController.ResponseEntities.GetInitializeDataResponse.InternalDTO.ModeDTO;

import lombok.Data;

@Data
public class GetInitializeDataResponse {
    private List<DeckDTO> decks;
    private List<ModeDTO> modes;
}
