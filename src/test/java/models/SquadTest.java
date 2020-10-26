package models;

import org.junit.Test;

import static org.junit.Assert.*;

public class SquadTest {
    Squad squad = new Squad("Avengers", "avenging the universe", 1);

    @Test
    public void getSquadName(){
        assertEquals("Avengers", squad.getSquadName());
    }

    @Test
    public void getCause(){
        assertEquals("avenging the universe", squad.getSquadCause());
    }

    @Test
    public void getSquadSize(){
        assertEquals(1, squad.getSquadSize());
    }

}