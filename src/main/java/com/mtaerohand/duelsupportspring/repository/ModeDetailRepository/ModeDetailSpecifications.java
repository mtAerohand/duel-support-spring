package com.mtaerohand.duelsupportspring.repository.ModeDetailRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import jakarta.persistence.criteria.Predicate;

/**
 * モード詳細Specifications
 */
@Component
public class ModeDetailSpecifications {
    /**
     * 現在有効なモード詳細か
     */
    public Specification<ModeDetail> isOngoing() {
        return (root, query, builder) -> {
            LocalDateTime currentDateTime = LocalDateTime.now();

            List<Predicate> predicates = new ArrayList<>();
            // startDatetime <= 現在日時 <= endDatetime の条件を追加
            predicates.add(builder.lessThanOrEqualTo(root.get("startDatetime"), currentDateTime));
            predicates.add(builder.greaterThanOrEqualTo(root.get("endDatetime"), currentDateTime));

            // 条件を AND で結合
            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
