package com.mtaerohand.duelsupportspring.Repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mtaerohand.duelsupportspring.Entities.Mode;

@Repository
public interface ModeRepository extends JpaRepository<Mode, Integer> {
    @SuppressWarnings("null")
    List<Mode> findAll(Sort sort);

    @Query("SELECT m FROM Mode m LEFT JOIN FETCH m.modeDetails md " +
            "WHERE CURRENT_TIMESTAMP BETWEEN md.startDatetime AND md.endDatetime")
    List<Mode> findAllOngoingModes(LocalDateTime currentDatetime);
}
