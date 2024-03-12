package com.mtaerohand.duelsupportspring.Repositories;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mtaerohand.duelsupportspring.Entities.Mode;

@Repository
public interface ModeRepository extends JpaRepository<Mode, Integer> {
    @SuppressWarnings("null")
    List<Mode> findAll(Sort sort);
}
