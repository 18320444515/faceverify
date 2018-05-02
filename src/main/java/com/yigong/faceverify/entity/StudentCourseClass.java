package com.yigong.faceverify.entity;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.lang.ref.PhantomReference;
import java.sql.Timestamp;

/**
 * 学生课程课堂表
 * @author tianyi
 * @date 2018-03-02 12:44
 */
@Entity
public class StudentCourseClass {
    @Id
    @Column(name = "student_course_class_id")
    @GeneratedValue    //自增
    private int studentCourseClassId;  //学生课程课堂id

    @NotNull
    private int studentNumber; //学号

    @NotBlank
    private String studentName;  //学生姓名

    @NotNull
    private int courseClassId;  //课程课堂id

    @NotNull
    private int attendanceStatus;  //出勤状态

    private String leave_reason;  //请假原因

    private Timestamp createTime;

    public int getStudentCourseClassId() {
        return studentCourseClassId;
    }

    public void setStudentCourseClassId(int studentCourseClassId) {
        this.studentCourseClassId = studentCourseClassId;
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

    public int getCourseClassId() {
        return courseClassId;
    }

    public void setCourseClassId(int courseClassId) {
        this.courseClassId = courseClassId;
    }

    public int getAttendanceStatus() {
        return attendanceStatus;
    }

    public void setAttendanceStatus(int attendanceStatus) {
        this.attendanceStatus = attendanceStatus;
    }

    public String getLeave_reason() {
        return leave_reason;
    }

    public void setLeave_reason(String leave_reason) {
        this.leave_reason = leave_reason;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
}
