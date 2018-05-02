package com.yigong.faceverify.repository;

import com.yigong.faceverify.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author tianyi
 * @date 2018-03-01 10:45
 */
public interface CourseRepository extends JpaRepository<Course,Integer> {

}
