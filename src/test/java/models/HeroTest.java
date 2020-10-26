package models;

import org.junit.Test;

import static org.junit.Assert.*;

public class HeroTest {
    Hero first = new Hero("Batman","Gadgets","Parent's Death", 45,1);
    @Test
    public void getHeroName(){
        assertEquals("Batman", first.getHeroName());
    }

    @Test
    public void setAge(){
        assertEquals(45, first.getAge());
    }

    @Test
    public void getHeroPower(){
        assertEquals("Gadgets", first.getHeroPower());
    }

    @Test
    public void getSquadId(){
        assertEquals(1, first.getSquadId());
    }

    @Test
    public void getHeroWeakness(){
        assertEquals("Parent's Death", first.getHeroWeakness());
    }
}