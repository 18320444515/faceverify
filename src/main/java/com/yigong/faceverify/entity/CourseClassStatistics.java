package com.yigong.faceverify.entity;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * 课程课堂统计表
 * @author tianyi
 * @date 2018-03-02 12:00
 */
@Entity
public class CourseClassStatistics {
    @Id
    @Column(name = "course_class_id")
    @GeneratedValue    //自增
    private int courseClassId;  //课程课堂id

    @NotNull
    private int teacherCourseId;  //课程课堂id

    @NotNull
    private int studentNum;

    @NotNull
    private int attendanceNum;  //出勤

    @NotNull
    private int absenceNum;  //缺勤

    @NotNull
    private int lateNum;  //迟到

    @NotNull
    private int leaveNum;  //请假

    @NotNull
    private int attendanceStatus;  //出勤状态：全勤/缺勤

    @NotNull
    private int classOrder;  //课堂次序

    private Timestamp createTime;

    public int getCourseClassId() {
        return courseClassId;
    }

    public void setCourseClassId(int courseClassId) {
        this.courseClassId = courseClassId;
    }

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

    public int getAttendanceNum() {
        return attendanceNum;
    }

    public void setAttendanceNum(int attendanceNum) {
        this.attendanceNum = attendanceNum;
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

    public int getAttendanceStatus() {
        return attendanceStatus;
    }

    public void setAttendanceStatus(int attendanceStatus) {
        this.attendanceStatus = attendanceStatus;
    }

    public int getClassOrder() {
        return classOrder;
    }

    public void setClassOrder(int classOrder) {
        this.classOrder = classOrder;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
}
