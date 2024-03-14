package com.mtaerohand.duelsupportspring.Repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mtaerohand.duelsupportspring.Entities.ModeDetail;

@Repository
public interface ModeDetailRepository extends JpaRepository<ModeDetail, Integer> {
    @Query("SELECT m FROM ModeDetail m WHERE :modeId = m.modeId AND m.startDatetime <= :currentDatetime AND m.endDatetime >= :currentDatetime")
    List<ModeDetail> findAllOngoingByModeId(Integer modeId, LocalDateTime currentDatetime);
}
