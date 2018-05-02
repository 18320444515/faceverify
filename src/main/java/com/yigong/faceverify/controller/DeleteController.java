package com.yigong.faceverify.controller;

import com.yigong.faceverify.VO.ResultVO;
import com.yigong.faceverify.entity.*;
import com.yigong.faceverify.repository.*;
import com.yigong.faceverify.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author tianyi
 * @date 2018-03-01 11:00
 */
@RestController
@RequestMapping(value = "/delete")
@Slf4j
public class DeleteController {

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
        log.info("【delete/user】");
        User user=userRepository.findOne(userId);
        try{
            userRepository.delete(user);
            return ResultVOUtil.success();
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResultVOUtil.error(123,"删除user失败");
    }

    @PostMapping(value = "/course")
    @ResponseBody
    public ResultVO course(@RequestParam(value = "CourseId")int courseId){
        log.info("【delete/user】");
        Course course=courseRepository.findOne(courseId);
        try{
            courseRepository.delete(course);
            return ResultVOUtil.success();
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResultVOUtil.error(234,"删除course失败");
    }

    @PostMapping(value = "/courseClass")
    @ResponseBody
    public ResultVO courseClass(@RequestParam(value = "CourseClassId")int courseClassId){
        CourseClass courseClass=courseClassRepository.findOne(courseClassId);
        try{
            courseClassRepository.delete(courseClass);
            return ResultVOUtil.success();
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResultVOUtil.error(255,"删除courseClass失败");
    }

    @PostMapping(value = "/courseClassStatistics")
    @ResponseBody
    public ResultVO courseClassStatistics(@RequestParam(value = "CourseClassId")int courseClassId){
        CourseClassStatistics courseClassStatistics=courseClassStatisticsRepository.findOne(courseClassId);
        try{
            courseClassStatisticsRepository.delete(courseClassStatistics);
            return ResultVOUtil.success();
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResultVOUtil.error(155,"删除失败");
    }

    @PostMapping(value = "/courseStatistic")
    @ResponseBody
    public ResultVO courseStatistic(@RequestParam(value = "TeacherCourseId")int teacherCourseId){
        CourseStatistics courseStatistics=courseStatisticsRepository.findOne(teacherCourseId);
        try{
            courseStatisticsRepository.delete(courseStatistics);
            return ResultVOUtil.success();
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResultVOUtil.error(63,"删除失败");
    }

    @PostMapping(value = "/notice")
    @ResponseBody
    public ResultVO notice(@RequestParam(value = "NoticeId")int noticeId){
        Notice notice=noticeRepository.findOne(noticeId);
        try{
            noticeRepository.delete(notice);
            return ResultVOUtil.success();
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResultVOUtil.error(2452,"删除失败");
    }

    @PostMapping(value = "/studentCourse")
    @ResponseBody
    public ResultVO studentCourse(@RequestParam(value = "StudentCourseId")int studentCourseId){
        StudentCourse studentCourse=studentCourseRepository.findOne(studentCourseId);
        try{
            studentCourseRepository.save(studentCourse);
            return ResultVOUtil.success();
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResultVOUtil.error(464,"删除失败");
    }

    @PostMapping(value = "/studentCourseClass")
    @ResponseBody
    public ResultVO studentCourseClass(@RequestParam(value = "StudentCourseClassId")int studentCourseClassId){
        StudentCourseClass studentCourseClass=studentCourseClassRepository.findOne(studentCourseClassId);
        try{
            studentCourseClassRepository.save(studentCourseClass);
            return ResultVOUtil.success();
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResultVOUtil.error(346,"删除失败");
    }

    @PostMapping(value = "/teacherCourse")
    @ResponseBody
    public ResultVO teacherCourse(@RequestParam(value = "TeacherCourseId")int teacherCourseId){
        TeacherCourse teacherCourse=teacherCourseRepository.findOne(teacherCourseId);
        try{
            teacherCourseRepository.save(teacherCourse);
            return ResultVOUtil.success();
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResultVOUtil.error(252,"删除失败");
    }
}
