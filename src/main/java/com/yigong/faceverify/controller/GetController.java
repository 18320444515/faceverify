package com.yigong.faceverify.controller;

import com.yigong.faceverify.VO.ResultVO;
import com.yigong.faceverify.entity.*;
import com.yigong.faceverify.repository.*;
import com.yigong.faceverify.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author tianyi
 * @date 2018-03-02 17:15
 */
@Controller
@RequestMapping("/get")
@Slf4j
public class GetController {

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

    @RequestMapping(value = "/user",method = RequestMethod.POST)
    @ResponseBody
    public ResultVO user(@RequestParam(value = "UserId")int userId){
        log.info("【get/user】");
        User user=userRepository.findOne(userId);
        if (user!=null){
            return ResultVOUtil.success(user);
        }else {
            return ResultVOUtil.error(123, "获取user失败");
        }
    }

    /**
     * 获取班级简要信息
     * @param userId 用户的id列
     * @param courseId course表的id列，course表的code列==teacher_course表的courseId
     * @return
     */
    @PostMapping(value = "/class/message")
    @ResponseBody
    public ResultVO classMessage(@RequestParam(value = "UserId") int userId,
                             @RequestParam(value = "CourseId")int courseId){
        User user=userRepository.findOne(userId);
        Course course=courseRepository.findOne(courseId);
        TeacherCourse teacherCourse=teacherCourseRepository.findByCourseId(course.getCode());
        ClassMsg classMsg=new ClassMsg();
        classMsg.setClassName(course.getName());
        classMsg.setClassNum(teacherCourse.getClassNum());
        classMsg.setTeacherName(user.getName());
        classMsg.setTerm(teacherCourse.getTerm());
        return ResultVOUtil.success(classMsg);
    }

    @PostMapping(value = "/UserId/all")
    @ResponseBody
    public ResultVO allUserId(){
        return ResultVOUtil.success(userRepository.findAll());
    }

    /**
     * TODO 注意！！这里要用teacher_course表的courseId
     * @return
     */
    @PostMapping(value = "/CourseId/all")
    @ResponseBody
    public ResultVO allCourseId(){
        return ResultVOUtil.success(teacherCourseRepository.findAll());
    }

    @PostMapping(value = "/course")
    @ResponseBody
    public ResultVO course(@RequestParam(value = "CourseId")int courseId){
        log.info("【get/course】");
        Course course=courseRepository.findOne(courseId);
        if (course!=null){
            return ResultVOUtil.success(course);
        }else {
            return ResultVOUtil.error(234, "获取course失败");
        }
    }

    @PostMapping(value = "/courseClass")
    @ResponseBody
    public ResultVO courseClass(@RequestParam(value = "CourseClassId")int courseClassId){
        CourseClass courseClass=courseClassRepository.findOne(courseClassId);
        if (courseClass!=null){
            return ResultVOUtil.success(courseClass);
        }else {
            return ResultVOUtil.error(255, "获取courseClass失败");
        }
    }

    @PostMapping(value = "/courseClassStatistics")
    @ResponseBody
    public ResultVO courseClassStatistics(@RequestParam(value = "CourseClassId")int courseClassId){
        CourseClassStatistics courseClassStatistics=courseClassStatisticsRepository.findOne(courseClassId);
        if (courseClassStatistics!=null){
            return ResultVOUtil.success(courseClassStatistics);
        }else {
            return ResultVOUtil.error(155, "获取失败");
        }
    }

    @PostMapping(value = "/courseStatistic")
    @ResponseBody
    public ResultVO courseStatistic(@RequestParam(value = "TeacherCourseId")int teacherCourseId){
        CourseStatistics courseStatistics=courseStatisticsRepository.findOne(teacherCourseId);
        if (courseStatistics!=null){
            return ResultVOUtil.success(courseStatistics);
        }else {
            return ResultVOUtil.error(63, "获取失败");
        }
    }

    @PostMapping(value = "/notice")
    @ResponseBody
    public ResultVO notice(@RequestParam(value = "NoticeId")int noticeId){
        Notice notice=noticeRepository.findOne(noticeId);
        if (notice!=null){
            return ResultVOUtil.success(notice);
        }else {
            return ResultVOUtil.error(2452, "获取失败");
        }
    }

    @PostMapping(value = "/studentCourse")
    @ResponseBody
    public ResultVO studentCourse(@RequestParam(value = "StudentCourseId")int studentCourseId){
        StudentCourse studentCourse=studentCourseRepository.findOne(studentCourseId);
        if (studentCourse!=null){
            return ResultVOUtil.success(studentCourse);
        }else {
            return ResultVOUtil.error(464, "获取失败");
        }
    }

    @PostMapping(value = "/studentCourseClass")
    @ResponseBody
    public ResultVO studentCourseClass(@RequestParam(value = "StudentCourseClassId")int studentCourseClassId){
        StudentCourseClass studentCourseClass=studentCourseClassRepository.findOne(studentCourseClassId);
        if (studentCourseClass!=null){
            return ResultVOUtil.success(studentCourseClass);
        }else {
            return ResultVOUtil.error(346, "获取失败");
        }
    }

    @PostMapping(value = "/teacherCourse")
    @ResponseBody
    public ResultVO teacherCourse(@RequestParam(value = "TeacherCourseId")int teacherCourseId){
        TeacherCourse teacherCourse=teacherCourseRepository.findOne(teacherCourseId);
        if (teacherCourse!=null){
            return ResultVOUtil.success(teacherCourse);
        }else {
            return ResultVOUtil.error(252, "获取失败");
        }
    }

}
