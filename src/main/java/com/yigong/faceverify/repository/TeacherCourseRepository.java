package com.yigong.faceverify.repository;

import com.yigong.faceverify.entity.TeacherCourse;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author tianyi
 * @date 2018-03-02 13:26
 */
public interface TeacherCourseRepository extends JpaRepository<TeacherCourse,Integer> {
    //!!注意!!!!这里的courseId 不是course表的id，而是course表的code!!!!!!!!TODO
    public TeacherCourse findByCourseId(int courseId);
}
