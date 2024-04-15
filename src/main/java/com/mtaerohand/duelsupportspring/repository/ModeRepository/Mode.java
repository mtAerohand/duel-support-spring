package com.mtaerohand.duelsupportspring.repository.ModeRepository;

import java.util.List;

import com.mtaerohand.duelsupportspring.repository.BaseEntity;
import com.mtaerohand.duelsupportspring.repository.ModeDetailRepository.ModeDetail;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * モードエンティティ
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "modes")
@Data
public class Mode extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String pronounce;

    private Boolean isPermanent;

    private String remarks;

    @OneToMany(mappedBy = "mode", cascade = CascadeType.ALL)
    private List<ModeDetail> modeDetails;
}
