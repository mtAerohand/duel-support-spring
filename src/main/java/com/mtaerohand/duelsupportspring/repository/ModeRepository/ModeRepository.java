package com.mtaerohand.duelsupportspring.repository.ModeRepository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * モード情報リポジトリ
 */
@Repository
public interface ModeRepository extends JpaRepository<Mode, Integer>, JpaSpecificationExecutor<Mode> {
    @SuppressWarnings("null")
    List<Mode> findAll(Sort sort);

    @SuppressWarnings("null")
    List<Mode> findAll(Specification<Mode> spec, Sort sort);
}
