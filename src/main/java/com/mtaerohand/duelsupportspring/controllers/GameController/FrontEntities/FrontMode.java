package com.mtaerohand.duelsupportspring.Controllers.GameController.FrontEntities;

import lombok.Data;

@Data
public class FrontMode {
    private Integer id;
    private String name;
    private String pronounce;
    private Boolean isPermanent;
    private String remarks;
}
