package com.yigong.faceverify.controller;

import com.yigong.faceverify.VO.ResultVO;
import com.yigong.faceverify.entity.*;
import com.yigong.faceverify.repository.*;
import com.yigong.faceverify.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 * @author tianyi
 * @date 2018-02-28 20:29
 */
@RestController
@RequestMapping(value = "/update")
@Slf4j
public class UpdateController {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseClassRepository courseClassRepository;

    @Autowired
    private CourseClassStatisticsRepository courseClassStatisticsRepository;

    @Autowired
    private CourseStatisticsRepository courseStatisticsRepository;

    @Autowired
    private NoticeRepository noticeRepository;

    @Autowired
    private StudentCourseRepository studentCourseRepository;

    @Autowired
    private StudentCourseClassRepository studentCourseClassRepository;

    @Autowired
    private TeacherCourseRepository teacherCourseRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping(value = "/user/password")
    @ResponseBody
    public ResultVO studentPassword(@RequestParam(value = "userName")String userName,
                                    @RequestParam(value = "passWord")String passWord){
        User user=userRepository.findByName(userName);
        user.setPassword(passWord);
        return ResultVOUtil.success(userRepository.save(user));
    }

    @RequestMapping(value = "/user/message",method = RequestMethod.POST)
    @ResponseBody
    public ResultVO student(@RequestParam(value = "UserId")int userId,
                            @RequestParam(value = "Name")String userName,
                            @RequestParam(value = "Type")int type,
                            @RequestParam(value = "Gender")int gender,
                            @RequestParam(value = "Phone")String phone,
                            @RequestParam(value = "College")int college,
                            @RequestParam(value = "Major")String major,
                            @RequestParam(value = "Campus")int campus,
                            @RequestParam(value = "Status")int status){
        log.info("【update/user/message】");

        User user=userRepository.findOne(userId);
        user.setType(type);
        user.setGender(gender);
        user.setPhone(phone);
        user.setCollege(college);
        user.setMajor(major);
        user.setCampus(campus);
        user.setStatus(status);

        return ResultVOUtil.success(userRepository.save(user));
    }

    @PostMapping(value = "/course")
    @ResponseBody
    public ResultVO course(@RequestParam(value = "CourseId")int courseId,
                           @RequestParam(value = "Code") int code,
                           @RequestParam(value = "Name")String name,
                           @RequestParam(value = "Type")int type,
                           @RequestParam(value = "Style")int style,
                           @RequestParam(value = "Hour")int hour,
                           @RequestParam(value = "Credit")BigDecimal credit){
        Course course=courseRepository.findOne(courseId);
        course.setCode(code);
        course.setName(name);
        course.setType(type);
        course.setStyle(style);
        course.setHour(hour);
        course.setCredit(credit);
        return ResultVOUtil.success(courseRepository.save(course));
    }

    @PostMapping(value = "/courseClass")
    @ResponseBody
    public ResultVO courseClass(@RequestParam(value = "CourseClassId")int courseClassId,
                                @RequestParam(value = "ClassOrder")int classOrder,
                                @RequestParam(value = "TeacherCourseId")int teacherCourseId,
                                @RequestParam(value = "ClassTime")String classTime,
                                @RequestParam(value = "ClassCode")String classCode,
                                @RequestParam(value = "WeekNum")int weekNum,
                                @RequestParam(value = "DayNum")int dayNum,
                                @RequestParam(value = "ClassPlace")String classPlace,
                                @RequestParam(value = "ClassDate")String classDate,
                                @RequestParam(value = "ClassType")int classType,
                                @RequestParam(value = "ClassStatus")int classStatus){
        CourseClass courseClass=courseClassRepository.findOne(courseClassId);
        courseClass.setClassOrder(classOrder);
        courseClass.setTeacherCourseId(teacherCourseId);
        courseClass.setClassTime(classTime);
        courseClass.setClassCode(classCode);
        courseClass.setWeekNum(weekNum);
        courseClass.setDayNum(dayNum);
        courseClass.setClassPlace(classPlace);
        courseClass.setClassDate(classDate);
        courseClass.setClassType(classType);
        courseClass.setClassStatus(classStatus);
        return ResultVOUtil.success(courseClassRepository.save(courseClass));
    }

    @PostMapping(value = "/courseClassStatistics")
    @ResponseBody
    public ResultVO courseClassStatistics(@RequestParam(value = "CourseClassId")int courseClassId,
                                          @RequestParam(value = "TeacherCourseId")int teacherCourseId,
                                          @RequestParam(value = "StudentNum")int studentNum,
                                          @RequestParam(value = "AttendanceNum")int attendanceNum,
                                          @RequestParam(value = "AbsenceNum")int absenceNum,
                                          @RequestParam(value = "LateNum")int lateNum,
                                          @RequestParam(value = "LeaveNum")int leaveNum,
                                          @RequestParam(value = "AttendanceStatus")int attendanceStatus,
                                          @RequestParam(value = "ClassOrder")int classOrder){
        CourseClassStatistics courseClassStatistics=courseClassStatisticsRepository.findOne(courseClassId);
        courseClassStatistics.setTeacherCourseId(teacherCourseId);
        courseClassStatistics.setStudentNum(studentNum);
        courseClassStatistics.setAttendanceNum(attendanceNum);
        courseClassStatistics.setAbsenceNum(absenceNum);
        courseClassStatistics.setLateNum(lateNum);
        courseClassStatistics.setLeaveNum(leaveNum);
        courseClassStatistics.setClassOrder(classOrder);
        courseClassStatistics.setAttendanceStatus(attendanceStatus);
        return ResultVOUtil.success(courseClassStatisticsRepository.save(courseClassStatistics));
    }

    @PostMapping(value = "/courseStatistics")
    @ResponseBody
    public ResultVO courseStatistics(@RequestParam(value = "TeacherCourseId")int teacherCourseId,
                                     @RequestParam(value = "StudentNum")int studentNum,
                                     @RequestParam(value = "ClassNum")int classNum,
                                     @RequestParam(value = "HasClassNum")int hasClassNum,
                                     @RequestParam(value = "AllAttendanceAum")int allAttendanceNum,
                                     @RequestParam(value = "NotAttendanceNum")int notAttendanceNum,
                                     @RequestParam(value = "AbsenceNum")int absenceNum,
                                     @RequestParam(value = "LateNum")int lateNum,
                                     @RequestParam(value = "LeaveNum")int leaveNum){
        CourseStatistics courseStatistics=courseStatisticsRepository.findOne(teacherCourseId);
        courseStatistics.setStudentNum(studentNum);
        courseStatistics.setClassNum(classNum);
        courseStatistics.setHasClassNum(hasClassNum);
        courseStatistics.setAllAttendanceNum(allAttendanceNum);
        courseStatistics.setNotAttendanceNum(notAttendanceNum);
        courseStatistics.setAbsenceNum(absenceNum);
        courseStatistics.setLateNum(lateNum);
        courseStatistics.setLeaveNum(leaveNum);
        return ResultVOUtil.success(courseStatisticsRepository.save(courseStatistics));
    }

    @PostMapping(value = "/notice")
    @ResponseBody
    public ResultVO notice(@RequestParam(value = "NoticeId")int noticeId,
                           @RequestParam(value = "Title")String title,
                           @RequestParam(value = "Context")String context,
                           @RequestParam(value = "IsSystem")int isSystem,
                           @RequestParam(value = "PublisherName")String publisherName){
        Notice notice=noticeRepository.findOne(noticeId);
        notice.setTitle(title);
        notice.setContext(context);
        notice.setIsSystem(isSystem);
        notice.setPublisherName(publisherName);
        return ResultVOUtil.success(noticeRepository.save(notice));
    }

    @PostMapping(value = "/studentCourse")
    @ResponseBody
    public ResultVO studentCourse(@RequestParam(value = "StudentCourseId")int studentCourseId,
                                  @RequestParam(value = "StudentNumber")int studentNumber,
                                  @RequestParam(value = "StudentName")String studentName,
                                  @RequestParam(value = "TeacherCourseId")int teacherCourseId,
                                  @RequestParam(value = "courseName")String courseName,
                                  @RequestParam(value = "AttendanceNum")int attendanceNum,
                                  @RequestParam(value = "AbsenceNum")int absenceNum,
                                  @RequestParam(value = "LateNum")int lateNum,
                                  @RequestParam(value = "LeaveNum")int leaveNum,
                                  @RequestParam(value = "ClassNum")int classNum){
        StudentCourse studentCourse=studentCourseRepository.findOne(studentCourseId);
        studentCourse.setStudentNumber(studentNumber);
        studentCourse.setStudentName(studentName);
        studentCourse.setTeacherCourseId(teacherCourseId);
        studentCourse.setCourseName(courseName);
        studentCourse.setAttendanceNum(attendanceNum);
        studentCourse.setAbsenceNum(absenceNum);
        studentCourse.setLateNum(lateNum);
        studentCourse.setLeaveNum(leaveNum);
        studentCourse.setClassNum(classNum);
        return ResultVOUtil.success(studentCourseRepository.save(studentCourse));
    }

    @PostMapping(value = "/studentCourseClass")
    @ResponseBody
    public ResultVO studentCourseClass(@RequestParam(value = "StudentCourseClassId")int studentCourseClassId,
                                       @RequestParam(value = "StudentNumber")int studentNumber,
                                       @RequestParam(value = "StudentName")String studentName,
                                       @RequestParam(value = "CourseClassId")int courseClassId,
                                       @RequestParam(value = "AttendanceStatus")int attendanceStatus){
        StudentCourseClass studentCourseClass=studentCourseClassRepository.findOne(studentCourseClassId);
        studentCourseClass.setStudentNumber(studentNumber);
        studentCourseClass.setStudentName(studentName);
        studentCourseClass.setCourseClassId(courseClassId);
        studentCourseClass.setAttendanceStatus(attendanceStatus);
        return ResultVOUtil.success(studentCourseClassRepository.save(studentCourseClass));
    }

    @PostMapping(value = "/teacherCourse")
    @ResponseBody
    public ResultVO teacherCourse(@RequestParam(value = "TeacherCourseId")int teacherCourseId,
                                  @RequestParam(value = "TeacherNumber")int teacherNumber,
                                  @RequestParam(value = "CourseId")int courseId,
                                  @RequestParam(value = "CourseName")String courseName,
                                  @RequestParam(value = "ClassName")String className,
                                  @RequestParam(value = "Term")String term,
                                  @RequestParam(value = "StudentNum")int studentNum,
                                  @RequestParam(value = "classNum")int classNum){
        TeacherCourse teacherCourse=teacherCourseRepository.findOne(teacherCourseId);
        teacherCourse.setTeacherNumber(teacherNumber);
        teacherCourse.setCourseId(courseId);
        teacherCourse.setCourseName(courseName);
        teacherCourse.setClassName(className);
        teacherCourse.setTerm(term);
        teacherCourse.setStudentNum(studentNum);
        teacherCourse.setClassNum(classNum);
        return ResultVOUtil.success(teacherCourseRepository.save(teacherCourse));
    }

}
