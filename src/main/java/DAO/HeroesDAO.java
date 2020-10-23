package DAO;

import models.Heroes;

import java.util.List;

public interface HeroesDAO {

    List<Heroes> getAllHeroes();

    void addHeroes(Heroes heroes);
}
