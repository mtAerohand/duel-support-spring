package com.mtaerohand.duelsupportspring.Entities;

import java.sql.Timestamp;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Mode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String pronounce;

    private Boolean isPermanent;

    private String remarks;

    private Timestamp updatedAt;

    @OneToMany(mappedBy = "mode", cascade = CascadeType.ALL)
    private List<ModeDetail> modeDetails;
}
