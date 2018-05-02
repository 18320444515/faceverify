package com.yigong.faceverify.entity;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * 课程课堂总统计表
 * @author tianyi
 * @date 2018-03-02 12:14
 */
@Entity
public class Notice {
    @Id
    @Column(name = "notice_id")
    @GeneratedValue    //自增
    private int noticeId;  //公告通知id

    @NotBlank
    private String title;  //标题

    @NotBlank
    private String context; //内容

    private int publisherId;  //发布人id

    @NotNull
    private int isSystem;  //是否为系统消息

    @NotBlank
    private String publisherName; //发布人名字

    private Timestamp createTime;

    public int getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(int noticeId) {
        this.noticeId = noticeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public int getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(int publisherId) {
        this.publisherId = publisherId;
    }

    public int getIsSystem() {
        return isSystem;
    }

    public void setIsSystem(int isSystem) {
        this.isSystem = isSystem;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisher_name) {
        this.publisherName = publisher_name;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
}
