package com.mtaerohand.duelsupportspring.repository.ModeDetailRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * モード詳細情報リポジトリ
 */
@Repository
public interface ModeDetailRepository extends JpaRepository<ModeDetail, Integer> {

}
