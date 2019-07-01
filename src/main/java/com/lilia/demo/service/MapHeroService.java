package com.lilia.demo.service;

import com.lilia.demo.domen.Hero;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MapHeroService implements HeroService {
    private static Map<Integer, Hero> heroes = new HashMap<>();
    private static int counter = 5;
    static {
        heroes.put(1, new Hero(1, "Lila"));
        heroes.put(2, new Hero(2, "Sergey"));
        heroes.put(3, new Hero(3, "Timur"));
        heroes.put(4, new Hero(4, "Tamara"));
    }

    @Override
    public Collection<Hero> getHeroes() {
        return heroes.values();
    }

    @Override
    public Hero addHeroes(Hero hero) {
        hero.setId(counter);
        counter = counter + 1;
        heroes.put(hero.getId(), hero);
        return hero;
    }

    @Override
    public Hero updateHero(Hero hero) {
        heroes.put(hero.getId(),hero);
        return hero;
    }

    @Override
    public void deleteHero(Integer id) {
        heroes.remove(id);
    }

    @Override
    public Hero getHero(Integer id) {
        return heroes.get(id);
    }

}
