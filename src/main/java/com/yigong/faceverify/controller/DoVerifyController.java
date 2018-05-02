package com.yigong.faceverify.controller;

import com.yigong.faceverify.VO.ResultVO;
import com.yigong.faceverify.entity.*;
import com.yigong.faceverify.repository.*;
import com.yigong.faceverify.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author tianyi
 * @date 2018-03-05 21:08
 */
@RestController
@RequestMapping(value = "/face")
@Slf4j
public class DoVerifyController {

    @Autowired
    private CourseClassRepository courseClassRepository;

    @Autowired
    private CourseClassStatisticsRepository courseClassStatisticsRepository;

    @Autowired
    private CourseStatisticsRepository courseStatisticsRepository;

    @Autowired
    private StudentCourseRepository studentCourseRepository;

    @Autowired
    private StudentCourseClassRepository studentCourseClassRepository;

    @Autowired
    private TeacherCourseRepository teacherCourseRepository;

    /**
     * 对应开始考勤按钮的接口
     * @param teacherCourseId 教师课程id
     * @param classOrder 课堂次序(授课安排里的第几节课)
     * @return
     */
    @PostMapping(value = "/start")
    public ResultVO addCourseClassStatisticsOneData(@RequestParam(value = "TeacherCourseId")int teacherCourseId,
                                                    @RequestParam(value = "ClassOrder")int classOrder){
        TeacherCourse teacherCourse=teacherCourseRepository.findOne(teacherCourseId);
        CourseClassStatistics courseClassStatistics=new CourseClassStatistics();
        //设置关键的初始数据
        courseClassStatistics.setTeacherCourseId(teacherCourseId);
        courseClassStatistics.setStudentNum(teacherCourse.getStudentNum());
        courseClassStatistics.setClassOrder(classOrder);
        //设置其他初始数据
        courseClassStatistics.setAttendanceNum(0);
        courseClassStatistics.setAbsenceNum(0);
        courseClassStatistics.setLateNum(0);
        courseClassStatistics.setLeaveNum(0);
        //存储并返回
        return ResultVOUtil.success(courseClassStatisticsRepository.save(courseClassStatistics));
    }

    /**
     * 实现记录刷脸结果的接口
     * @param studentNumber 学号
     * @param teacherCourseId 教师课程id
     * @param courseClassId 课程课堂id
     * @return
     */
    @PostMapping(value = "/deal")
    public ResultVO dealCheckResultFromFront(@RequestParam(value = "StudentNumber")int studentNumber,
                                             @RequestParam(value = "TeacherCourseId")int teacherCourseId,
                                             @RequestParam(value = "courseClassId")int courseClassId){
        if (studentCourseClassRepository.findByCourseClassId(courseClassId)!=null){
            return ResultVOUtil.error(123,"用户已刷脸，赶紧进行下一位");
        }else {
            return doverify(studentNumber,teacherCourseId,courseClassId);
        }
    }

    /**
     * 参数同上，上先做检验，此处实现业务
     * @param studentNumber
     * @param teacherCourseId
     * @param courseClassId
     */
    private ResultVO doverify(int studentNumber, int teacherCourseId, int courseClassId){
        //TODO 获取上课时间
        CourseClass courseClass=courseClassRepository.getOne(courseClassId);
        String classDate=courseClass.getClassDate();
        String classTime=courseClass.getClassTime();
        String classCode=courseClass.getClassCode();
        SimpleDateFormat dataFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date startTime=null;
        try{
            startTime=dataFormat.parse(classDate+" "+classTime);
        }catch (ParseException e) {
            log.warn("考勤时日期转换异常");
            throw new RuntimeException("考勤时日期转换异常");
        }
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(startTime);
        int classNum=classCode.length()/2;
        calendar.add(Calendar.MINUTE,classNum*40+(classNum-1)*10);
        Date endTime=calendar.getTime();

        //获取当前时间，并开始判断 TODO Date的数据是以未来为增长方向吗
        Date nowTime=new Date();
        CourseClassStatistics courseClassStatistics=courseClassStatisticsRepository.findOne(courseClassId);
        StudentCourseClass studentCourseClass=new StudentCourseClass();
        if (nowTime.compareTo(startTime)>=0){
            //出勤，先在课堂表记录；再添加学生信息条数
            courseClassStatistics.setAttendanceNum(courseClassStatistics.getAttendanceNum()+1);
            courseClassStatisticsRepository.save(courseClassStatistics);
            studentCourseClass.setAttendanceStatus(1);
            studentCourseClass.setCourseClassId(courseClassId);
            studentCourseClass.setStudentNumber(studentNumber);
            studentCourseClass.setStudentName(studentCourseRepository.findByStudentNumberAndTeacherCourseId(studentNumber,teacherCourseId).getStudentName());
            //TODO 欠姓名（姓名从哪里来？User表？实际上是必须每个学生都注册吗？若否，）
            return ResultVOUtil.success(studentCourseClassRepository.save(studentCourseClass));
        }else if (nowTime.compareTo(endTime)>=0){
            //迟到，先在课堂表记录；再添加学生信息条数
            courseClassStatistics.setLateNum(courseClassStatistics.getLateNum()+1);
            courseClassStatisticsRepository.save(courseClassStatistics);
            studentCourseClass.setAttendanceStatus(2);
            studentCourseClass.setCourseClassId(courseClassId);
            studentCourseClass.setStudentNumber(studentNumber);
            studentCourseClass.setStudentName(studentCourseRepository.findByStudentNumberAndTeacherCourseId(studentNumber,teacherCourseId).getStudentName());
            //TODO 欠姓名（姓名从哪里来？User表？实际上是必须每个学生都注册吗？若否，）
            return ResultVOUtil.success(studentCourseClassRepository.save(studentCourseClass));
        }else {
            //缺勤，先在课堂表记录；再添加学生信息条数
            courseClassStatistics.setAbsenceNum(courseClassStatistics.getAbsenceNum()+1);
            courseClassStatisticsRepository.save(courseClassStatistics);
            studentCourseClass.setAttendanceStatus(0);
            studentCourseClass.setCourseClassId(courseClassId);
            studentCourseClass.setStudentNumber(studentNumber);
            studentCourseClass.setStudentName(studentCourseRepository.findByStudentNumberAndTeacherCourseId(studentNumber,teacherCourseId).getStudentName());
            //TODO 欠姓名（姓名从哪里来？User表？实际上是必须每个学生都注册吗？若否，）
            return ResultVOUtil.success(studentCourseClassRepository.save(studentCourseClass));
        }
    }

    @PostMapping(value = "/end")
    public void endVerify(@RequestParam(value = "courseClassId")int courseClassId,
                          @RequestParam(value = "TeacherCourseId")int teacherCourseId){
        //1.获取将会用到的相关对象
        CourseClassStatistics courseClassStatistics=courseClassStatisticsRepository.findOne(courseClassId);
        CourseStatistics courseStatistics=courseStatisticsRepository.findOne(teacherCourseId);
        //2.学生课程课堂表记录未刷脸的学生（记录为缺勤）
        List<StudentCourse> studentCourses=studentCourseRepository.findAllByTeacherCourseId(teacherCourseId);
        for (StudentCourse i:studentCourses) {
            StudentCourseClass j=studentCourseClassRepository.findByStudentNumber(i.getStudentNumber());
            if (j!=null){
                //若非空，则说明已考勤
                continue;
            }else {
                //若为空，则说明缺勤,【注意这里的J为null】
                //缺勤，先在课堂表记录；再添加学生信息条数
                courseClassStatistics.setAbsenceNum(courseClassStatistics.getAbsenceNum()+1);
                courseClassStatisticsRepository.save(courseClassStatistics);
                j=new StudentCourseClass();
                j.setAttendanceStatus(0);
                j.setCourseClassId(courseClassId);
                j.setStudentNumber(i.getStudentNumber());
                //TODO 欠姓名（姓名从哪里来？User表？实际上是必须每个学生都注册吗？若否，）
                studentCourseClassRepository.save(j);
                log.info("学号为"+i.getStudentNumber()+"的学生已被记录为缺勤");
                continue;
            }
        }
        //3.课程课堂表总结本次考勤的结果
        int absenceNum=courseClassStatistics.getAbsenceNum();
        int lateNum=courseClassStatistics.getLateNum();
        if (absenceNum!=0){
            courseClassStatistics.setAttendanceStatus(0);
            courseClassStatisticsRepository.save(courseClassStatistics);
            log.info("本堂课的出勤状态被判断为缺勤");
            //4.课程考勤记录总表更新本次的数据
            courseStatistics.setNotAttendanceNum(courseStatistics.getNotAttendanceNum()+1);
            courseStatistics.setHasClassNum(courseStatistics.getHasClassNum()+1);
            courseStatistics.setAbsenceNum(absenceNum);
            courseStatistics.setLateNum(lateNum);
            //TODO 迟到信息另写一个借口来补充实现
            log.info("已经更新课程记录总表的数据");
        }else {
            courseClassStatistics.setAttendanceStatus(1);
            courseClassStatisticsRepository.save(courseClassStatistics);
            log.info("本堂课的出勤状态被判断为全勤");
            //4.课程考勤记录总表更新本次的数据
            courseStatistics.setAllAttendanceNum(courseStatistics.getAllAttendanceNum()+1);
            courseStatistics.setHasClassNum(courseStatistics.getHasClassNum()+1);
            courseStatistics.setAbsenceNum(absenceNum);
            courseStatistics.setLateNum(lateNum);
            //TODO 迟到信息另写一个借口来补充实现
            log.info("已经更新课程记录总表的数据");
        }

    }
}
