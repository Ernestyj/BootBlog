package com.eugene.model;

import java.sql.Date;

/**
 * 博客页面的存储，包括id,日期，标题和博客页面
 */
public class Blog {

    private Integer vid;//id
    private Date date;//博客创建日期
    private String title;//博客标题，不可为空
    private String article;//博客内容的html文本
    private String tags;//标签，不同标签以,隔开
    private String md;//博客内容的markdown文本
    private String monthDay;//形如"Oct 04",为了方便archives页面显示，并不对应数据库的任何一列
    public Blog(String title, String tags, String md){
        this.title=title;
        this.tags=tags;
        this.md=md;
    }
    public Blog(int vid, String title, String tags){
        this.vid=vid;
        this.title=title;
        this.tags=tags;
    }

    public Blog() {
    }

    public Integer getVid() {

        return vid;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "vid=" + vid +
                ", date=" + date +
                ", title='" + title + '\'' +
                ", article='" + article + '\'' +
                ", tags='" + tags + '\'' +
                ", md='" + md + '\'' +
                ", monthDay='" + monthDay + '\'' +
                '}';
    }

    public void setVid(Integer vid) {
        this.vid = vid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getMd() {
        return md;
    }

    public void setMd(String md) {
        this.md = md;
    }

    public String getMonthDay() {
        return monthDay;
    }

    public void setMonthDay(String monthDay) {
        this.monthDay = monthDay;
    }

}
