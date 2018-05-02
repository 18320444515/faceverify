package com.yigong.faceverify.entity;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * 学生课程表
 * @author tianyi
 * @date 2018-03-02 12:19
 */
@Entity
public class StudentCourse {
    @Id
    @Column(name = "student_course_id")
    @GeneratedValue    //自增
    private int studentCourseId;  //学生课程id

    @NotNull
    private int studentNumber;  //学号

    @NotBlank
    private String studentName;  //学生姓名

    @NotNull
    private int teacherCourseId; //教师课程id

    @NotBlank
    private String courseName; //课程名

    @NotNull
    private int attendanceNum;  //出勤

    @NotNull
    private int absenceNum;  //缺勤

    @NotNull
    private int lateNum;  //迟到

    @NotNull
    private int leaveNum;  //请假

    @NotNull
    private int classNum;  //上课课堂次数

    private Timestamp createTime;

    public int getStudentCourseId() {
        return studentCourseId;
    }

    public void setStudentCourseId(int studentCourseId) {
        this.studentCourseId = studentCourseId;
    }

    public int getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(int studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getTeacherCourseId() {
        return teacherCourseId;
    }

    public void setTeacherCourseId(int teacherCourseId) {
        this.teacherCourseId = teacherCourseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
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

    public int getClassNum() {
        return classNum;
    }

    public void setClassNum(int classNum) {
        this.classNum = classNum;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
}
