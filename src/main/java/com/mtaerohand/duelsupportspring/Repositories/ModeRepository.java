package com.mtaerohand.duelsupportspring.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mtaerohand.duelsupportspring.Entities.Mode;

@Repository
public interface ModeRepository extends JpaRepository<Mode, Integer> {

}
