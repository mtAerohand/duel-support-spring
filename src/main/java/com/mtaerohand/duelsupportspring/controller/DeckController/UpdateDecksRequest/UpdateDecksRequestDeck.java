package com.mtaerohand.duelsupportspring.controller.DeckController.UpdateDecksRequest;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

@Value
public class UpdateDecksRequestDeck {
    @NotNull
    private Integer id;

    @NotEmpty
    @Size(max = 30)
    private String name;

    @NotEmpty
    @Size(max = 30)
    private String pronounce;

    @Size(max = 100)
    private String remarks;
}
