package com.mtaerohand.duelsupportspring.repository.UserRepository;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * ユーザエンティティ
 */
@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    private String id;
}
