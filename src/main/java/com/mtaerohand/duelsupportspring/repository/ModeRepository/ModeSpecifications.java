package com.mtaerohand.duelsupportspring.repository.ModeRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.mtaerohand.duelsupportspring.repository.ModeDetailRepository.ModeDetail;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;

@Component
public class ModeSpecifications {
    public Specification<Mode> isOngoing() {
        return (root, query, builder) -> {
            LocalDateTime currentDateTime = LocalDateTime.now();

            // ModeDetail エンティティとの結合
            Join<Mode, ModeDetail> join = root.join("modeDetails");

            List<Predicate> predicates = new ArrayList<>();
            // startDatetime <= 現在日時 <= endDatetime の条件を追加
            predicates.add(builder.lessThanOrEqualTo(join.get("startDatetime"), currentDateTime));
            predicates.add(builder.greaterThanOrEqualTo(join.get("endDatetime"), currentDateTime));

            // 条件を AND で結合
            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
