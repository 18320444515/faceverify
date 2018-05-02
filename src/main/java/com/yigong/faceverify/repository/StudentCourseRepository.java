package com.yigong.faceverify.repository;

import com.yigong.faceverify.entity.StudentCourse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author tianyi
 * @date 2018-03-02 13:25
 */
public interface StudentCourseRepository extends JpaRepository<StudentCourse,Integer> {
    public List<StudentCourse> findAllByTeacherCourseId(int teacherCourseId);
    public StudentCourse findByStudentNumberAndTeacherCourseId(int studentNum,int teacherCourseId);
}
