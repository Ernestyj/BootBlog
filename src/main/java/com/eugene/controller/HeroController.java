package com.eugene.controller;

import com.eugene.model.Hero;
import com.eugene.service.HeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * Created by DCLab on 2016/11/10.
 */
@RestController
public class HeroController {
    @Autowired
    private HeroService heroService;

    @GetMapping("/app/heroes")
    public List<Hero> getHeroes(){
        return heroService.getHeroes();
    }

    @PutMapping("/app/heroes/{id}")
    public int modifyName(@RequestBody Hero hero){
        return heroService.modifyName(hero.getId(), hero.getName());
    }

    @PostMapping("/app/heroes")
    public @ResponseBody Hero addHero(@RequestBody Hero hero){
        heroService.addHero(hero);
        return hero;
    }

    @DeleteMapping("/app/heroes/{id}")
    public int deleteHero(@PathVariable int id){
        return heroService.deleteHero(id);
    }

}


