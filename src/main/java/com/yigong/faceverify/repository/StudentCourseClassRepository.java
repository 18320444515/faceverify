package com.yigong.faceverify.repository;

import com.yigong.faceverify.entity.StudentCourseClass;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author tianyi
 * @date 2018-03-02 13:26
 */
public interface StudentCourseClassRepository extends JpaRepository<StudentCourseClass,Integer> {
    public StudentCourseClass findByCourseClassId(int courseId);
    public StudentCourseClass findByStudentNumber(int studentNum);
}
