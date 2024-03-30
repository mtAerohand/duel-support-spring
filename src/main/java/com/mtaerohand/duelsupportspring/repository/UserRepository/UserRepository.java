package com.mtaerohand.duelsupportspring.repository.UserRepository;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * ユーザ情報リポジトリ
 */
public interface UserRepository extends JpaRepository<User, String> {

}
