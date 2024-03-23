package com.mtaerohand.duelsupportspring.Controllers.SpreadsheetController;

import java.util.List;

import lombok.Data;

@Data
public class CreateGamesRequest {
    private List<CreateGameRequest> games;
}
