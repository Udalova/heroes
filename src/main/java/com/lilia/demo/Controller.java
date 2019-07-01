package com.lilia.demo;

import com.lilia.demo.domen.Hero;
import com.lilia.demo.service.FileHeroService;
import com.lilia.demo.service.HeroService;
import com.lilia.demo.service.MapHeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
public class Controller {

    private HeroService service;

    public Controller(@Autowired HeroService service) {
        this.service = service;
    }

    @RequestMapping(value = "/api/heroes", method = RequestMethod.GET)
    public ResponseEntity<Collection<Hero>> getHeroes() {
        Collection<Hero> heroes = service.getHeroes();
        return new ResponseEntity<>(heroes, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/heroes", method = RequestMethod.POST)
    public ResponseEntity<Hero> addHeroes(@RequestBody Hero hero) {
        return new ResponseEntity<>(service.addHeroes(hero), HttpStatus.OK);
    }

    @RequestMapping(value = "/api/heroes", method = RequestMethod.PUT)
    public ResponseEntity editHeroes(@RequestBody Hero hero) {
        return new ResponseEntity<>(service.updateHero(hero), HttpStatus.OK);
    }

    @RequestMapping(value = "/api/heroes/{id}", method = RequestMethod.GET)
    public ResponseEntity<Hero> getHero(@PathVariable int id) {
        Hero hero = service.getHero(id);
        if (hero == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(hero, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/heroes/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteHeroes(@PathVariable int id) {
        service.deleteHero(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
