package com.eugene.model;

import java.util.List;

/**
 * 前端页面archives显示的博客列表对应的实体
 */
public class Archive {
    private int year;//年份
    private List<Blog> list;//此年份的博客列表

    public Archive() {
    }

    public Archive(int year, List<Blog> list) {

        this.year = year;
        this.list = list;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<Blog> getList() {
        return list;
    }

    public void setList(List<Blog> list) {
        this.list = list;
    }
}
