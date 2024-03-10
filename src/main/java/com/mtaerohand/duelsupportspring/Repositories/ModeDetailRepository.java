package com.mtaerohand.duelsupportspring.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mtaerohand.duelsupportspring.Entities.ModeDetail;

@Repository
public interface ModeDetailRepository extends JpaRepository<ModeDetail, Integer> {

}
