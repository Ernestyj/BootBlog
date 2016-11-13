package com.eugene.service;

import com.eugene.mapper.HeroMapper;
import com.eugene.model.Hero;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by DCLab on 2016/11/10.
 */
@Service
public class HeroServiceImpl implements HeroService {

    private static final Logger LOGGER = LoggerFactory.getLogger(HeroServiceImpl.class);

    @Autowired
    private HeroMapper heroMapper;

    @Override
    public List<Hero> getHeroes() {
        List<Hero> heroes = null;
        try {
            heroes = heroMapper.selectAll();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return heroes;
    }

    @Override
    public Hero getHeroByName(String name) {
        Hero hero = null;
        try {
            hero = heroMapper.selectByName(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hero;
    }

    @Override
    public int modifyName(int id, String name) {
        int res = 0;
        try {
            res = heroMapper.updateName(id, name);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return res;
    }

    @Override
    public int addHero(Hero hero) {
        int res = 0;
        try {
            res = heroMapper.insertHero(hero);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return res;
    }

    @Override
    public int deleteHero(int id) {
        int res = 0;
        try {
            res = heroMapper.deleteHero(id);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return res;
    }
}
