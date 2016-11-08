package com.eugene.service;

import com.eugene.mapper.BlogMapper;
import com.eugene.model.Archive;
import com.eugene.model.Blog;
import com.eugene.utils.TimeTools;
import com.eugene.utils.Tools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/9/3.
 */
@Service
public class BlogServiceImpl implements BlogService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BlogServiceImpl.class);
    @Autowired
    private BlogMapper blogMapper;
    @Override
    public Blog adminGetBlog(int vid){
        Blog blog =null;
        try {
            blog =blogMapper.selectAdmin(vid);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return blog;
    }
    @Override
    @Caching(evict = {
            @CacheEvict(value = "archives",key = "1"),
            @CacheEvict(value = "tagList",key = "1"),
            @CacheEvict(value = "archivePageNum",key = "1")
    })
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public boolean addBlog(Blog blog){
        blog.setDate(new Date(System.currentTimeMillis()));
        boolean result=true;
        try {
            blogMapper.insertBlog(blog);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            result=false;
        }
        if (result){
            try {
                addViewTag(blog.getTags(), blog.getVid());
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
                result=false;
            }
        }
        return result;
    }
    @Override
    public List<Blog> getBlogPage(int id){
        List<Blog> blogList=new ArrayList<>();
        try {
            int start=(id-1)*10;
            blogList=blogMapper.selectTenBlogs(start);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return blogList;
    }
    @Override
    public int adminGetPageNum(){
        int num=0;
        try {
            num=blogMapper.selectBlogNum();
        } catch (Exception e) {
           LOGGER.error(e.getMessage());
    }
    return num%10==0?num/10:num/10+1;
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "archives",key = "1"),
            @CacheEvict(value = "tagList",key = "1")
    })
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public boolean updateBlog(Blog blog){
        boolean result=true;
        try {
            blogMapper.updateBlogView(blog);
        } catch (Exception e) {
            result=false;
            LOGGER.error(e.getMessage());
        }
        if (result){
            try {
                updateViewTag(blog.getTags(), blog.getVid());
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
                result=false;
            }
        }
        return result;
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "archives",key = "1"),
            @CacheEvict(value = "tagList",key = "1"),
            @CacheEvict(value = "archivePageNum",key = "1")
    })
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public boolean deleteBlogById(int vid) {
        boolean result=true;
        try {
            blogMapper.deleteBlogView(vid);
        } catch (Exception e) {
           LOGGER.error(e.getMessage());
            result=false;
        }
        return result;
    }

    @Override
    @Cacheable(value = "tagList",key = "1")
    public List<String> getTagList() {
        List<String> tags=new ArrayList<>();
        try {
            tags=blogMapper.selectTags();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return tags;
    }

    @Override
    @Cacheable(value = "archives",condition = "#page==1",key = "1")
    public List<Archive> getArchive(int page) {
        int start=(page-1)*12;
        List<Archive> archives=new ArrayList<>();
        try {
            List<Blog> blogs =blogMapper.selectArc(start);
            archives=bv2Ar(blogs);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return archives;
    }

    @Override
    @Cacheable(value = "archivePageNum",key = "1")
    public int getArchiveNum() {
        int blogNum=0;
        try {
            blogNum=blogMapper.selectBlogNum();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return blogNum%12==0?blogNum/12:blogNum/12+1;
    }

    @Override
    public Blog getBlog(int vid) {
        Blog blog =new Blog();
        try {
            blog =blogMapper.selectView(vid);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return blog;
    }

    @Override
    public Blog getPrevBlog(int vid) {
        Blog blog =null;
        try {
            blog =blogMapper.selectPreView(vid);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return blog;
    }

    @Override
    public Blog getNextBlog(int vid) {
        Blog blog =null;
        try {
            blog =blogMapper.selectNextView(vid);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return blog;
    }

    @Override
    public List<Blog> getBlogByTag(String tagName) {
        List<Blog> views=new ArrayList<>();
        try {
            List<Integer> vids=blogMapper.selectVidBytag(tagName);
            for (int vid:vids){
                Blog view=blogMapper.selectTagView(vid);
                if (view!=null){
                    view.setVid(vid);
                    String monthDay=TimeTools.getEdate(view.getDate());
                    view.setMonthDay(monthDay);
                    views.add(view);
                }
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return views;
    }

    private List<Archive> bv2Ar(List<Blog> views) throws Exception{
        List<Archive> archives=new ArrayList<>();
        Map<Integer,Archive> years2Ar=new HashMap<>();
        for (Blog view:views){
            Date date=view.getDate();
            view.setMonthDay(TimeTools.getEdate(date));
            int year=TimeTools.getYear(date);
            if (years2Ar.containsKey(year)){
                years2Ar.get(year).getList().add(view);
            }else {
                Archive archive=new Archive(year,new ArrayList<Blog>());
                years2Ar.put(year,archive);
                archive.getList().add(view);
                archives.add(archive);
            }
        }
        return archives;
    }
    private void addViewTag(String tagStr,int vid) throws Exception{
        List<String> tagList= Tools.getTagList(tagStr);
        for (String tag:tagList){
            blogMapper.insertViewTag(tag,vid);
        }

    }
    private void updateViewTag(String tagStr,int vid) throws Exception{
        blogMapper.deleteViewTag(vid);
        List<String> tagList=Tools.getTagList(tagStr);
        for (String tag:tagList){
            blogMapper.insertViewTag(tag,vid);
        }

    }

}
