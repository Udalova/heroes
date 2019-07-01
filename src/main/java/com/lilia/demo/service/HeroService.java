package com.lilia.demo.service;

import com.lilia.demo.domen.Hero;

import java.util.Collection;

public interface HeroService {
    Collection<Hero> getHeroes();

    Hero addHeroes(Hero hero);

    Hero updateHero(Hero hero);

    void deleteHero(Integer id);

    Hero getHero(Integer id);
}
