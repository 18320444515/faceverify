package com.yigong.faceverify.repository;

import com.yigong.faceverify.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author tianyi
 * @date 2018-03-02 13:25
 */
public interface NoticeRepository extends JpaRepository<Notice,Integer> {

}
