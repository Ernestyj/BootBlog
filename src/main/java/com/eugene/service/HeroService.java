package com.eugene.service;

import com.eugene.model.Hero;

import java.util.List;

/**
 * Created by DCLab on 2016/11/10.
 */
public interface HeroService {

    List<Hero> getHeroes();

    int modifyName(int id, String name);

    int addHero(Hero hero);

    int deleteHero(int id);
}
