package com.yigong.faceverify.repository;

import com.yigong.faceverify.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author tianyi
 * @date 2018-03-02 13:26
 */
public interface UserRepository extends JpaRepository<User,Integer> {
    User findByName(String name);
}
