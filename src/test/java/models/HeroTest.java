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
}