package com.lilia.demo.service;

import com.lilia.demo.domen.Hero;
import org.junit.Test;

import java.util.Collection;
import java.util.List;

public class FileHeroServiceTest {

    @Test
    public void addTest() {
        FileHeroService service = new FileHeroService();
        Hero hero = new Hero();
        hero.setName("Tamara");
        service.addHeroes(hero);
    }

    @Test
    public void deleteTest() {
        FileHeroService service = new FileHeroService();
        service.deleteHero(1);
    }
    @Test
    public void updateTest() {
        FileHeroService service = new FileHeroService();
        Hero hero = new Hero(2, "Сергей");
        service.updateHero(hero);

    }
    @Test
    public void getTest() {
        FileHeroService service = new FileHeroService();
        Hero hero = service.getHero(2);
        if (hero.getName().equals("Timur")){
            System.out.println("Ok");
        }

    }

    @Test
    public void getHeroesTest() {
        FileHeroService service = new FileHeroService();
        Collection<Hero> heroes = service.getHeroes();
        for(Hero hero : heroes){
            System.out.println(hero.getId() + " " + hero.getName());
        }


    }






}
