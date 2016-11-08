package com.eugene.service;

import com.eugene.mapper.InfoMapper;
import com.eugene.model.Info;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2016/9/8.
 */
@Service
public class InfoServiceImpl implements InfoService {
    private static final int MODIFYPASSSUC=0;//修改密码成功
    private static final int PASSERROE=1;//密码错误
    private static final int SySTEMERROE=2;//系统错误
    private static final Logger LOGGER = LoggerFactory.getLogger(InfoServiceImpl.class);
    @Autowired
    private InfoMapper infoMapper;

    @Override
    @Cacheable(value = "profileOfInfo",key = "1")
    public Info getInfo() {
        Info info=new Info();
        try {
            info=infoMapper.select();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return info;
    }

    @Override
    public boolean login(Info user) {
        int num=0;
        try {
            num=infoMapper.selectByPw(user.getUsername(),user.getPassword());
        } catch (Exception e) {
           LOGGER.error(e.getMessage());
        }
        if (num>0){
            return true;
        }else {
            return false;
        }
    }
    @Override
    public boolean checkPass(String oldPass){
        int num=0;
        try {
            num=infoMapper.selectByOldPass(oldPass);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return num>0;
    }
    @Override
    @CacheEvict(value = "profileOfInfo",key = "1")
    public boolean updateInfo(Info info) {
        boolean result=true;
        try {
            infoMapper.update(info);
        } catch (Exception e) {
           LOGGER.error(e.getMessage());
            result=false;
        }
        return result;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public int modifyPw(String oldPass,String newPass) {
        int result;
       if (checkPass(oldPass)){
           try {
               infoMapper.updataPass(newPass);
               result=MODIFYPASSSUC;
           } catch (Exception e) {
               LOGGER.error(e.getMessage());
               result=SySTEMERROE;
           }
       }else {
           result=PASSERROE;
       }
        return result;
    }
    @Override
    public void addSession(HttpServletRequest request,Info info){
        HttpSession session = request.getSession(true);
        session.setAttribute("cur_user",info);
        session.setMaxInactiveInterval(600);
    }
    @Override
    public void destroySession(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        session.removeAttribute("cur_user");
    }
    @Override
    public String getResumeMd(){
       String md="";
        try {
            md=infoMapper.selectMd();
        } catch (Exception e) {
           LOGGER.error(e.getMessage());
        }
        return md;
    }
    @Override
    @CacheEvict(value = "resumeView",key = "1")
    public boolean updateResume(Info info){
        boolean result=true;
        try {
            infoMapper.updateResume(info);
        } catch (Exception e) {
            result=false;
           LOGGER.error(e.getMessage());
        }
        return result;
    }

    @Override
    @Cacheable(value = "resumeView",key = "1")
    public String getResumeView() {
        String resume="";
        try {
            resume=infoMapper.selectResume();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return resume;
    }
}
