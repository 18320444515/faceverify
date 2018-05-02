package com.yigong.faceverify.entity;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * 课程总统计表
 * @author tianyi
 * @date 2018-03-02 12:06
 */
@Entity
public class CourseStatistics {
    @Id
    @Column(name = "teacher_course_id")
    @GeneratedValue    //自增
    private int teacherCourseId;  //课程课堂id

    @NotNull
    private int studentNum;

    @NotNull
    private int classNum;  //上课课堂次数

    @NotNull
    private int hasClassNum;  //已上课程次数

    @NotNull
    private int allAttendanceNum;  //全勤次数

    @NotNull
    private int notAttendanceNum;  //缺勤次数

    @NotNull
    private int absenceNum;  //缺勤人次

    @NotNull
    private int lateNum;  //迟到人次

    @NotNull
    private int leaveNum;  //请假人次

    private Timestamp createTime;

    public int getTeacherCourseId() {
        return teacherCourseId;
    }

    public void setTeacherCourseId(int teacherCourseId) {
        this.teacherCourseId = teacherCourseId;
    }

    public int getStudentNum() {
        return studentNum;
    }

    public void setStudentNum(int studentNum) {
        this.studentNum = studentNum;
    }

    public int getClassNum() {
        return classNum;
    }

    public void setClassNum(int classNum) {
        this.classNum = classNum;
    }

    public int getHasClassNum() {
        return hasClassNum;
    }

    public void setHasClassNum(int hasClassNum) {
        this.hasClassNum = hasClassNum;
    }

    public int getAllAttendanceNum() {
        return allAttendanceNum;
    }

    public void setAllAttendanceNum(int allAttendanceNum) {
        this.allAttendanceNum = allAttendanceNum;
    }

    public int getNotAttendanceNum() {
        return notAttendanceNum;
    }

    public void setNotAttendanceNum(int notAttendanceNum) {
        this.notAttendanceNum = notAttendanceNum;
    }

    public int getAbsenceNum() {
        return absenceNum;
    }

    public void setAbsenceNum(int absenceNum) {
        this.absenceNum = absenceNum;
    }

    public int getLateNum() {
        return lateNum;
    }

    public void setLateNum(int lateNum) {
        this.lateNum = lateNum;
    }

    public int getLeaveNum() {
        return leaveNum;
    }

    public void setLeaveNum(int leaveNum) {
        this.leaveNum = leaveNum;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
}
