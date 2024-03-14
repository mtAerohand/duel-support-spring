package com.mtaerohand.duelsupportspring.Controllers.GameController.ResponseEntities.GetModeDetailsOngoingResponse;

import java.util.List;

import com.mtaerohand.duelsupportspring.Controllers.GameController.ResponseEntities.GetModeDetailsOngoingResponse.InternalDTO.ModeDetailDTO;

import lombok.Data;

@Data
public class GetModeDetailsOngoingResponse {
    private Integer modeId;
    private List<ModeDetailDTO> modeDetails;
}
