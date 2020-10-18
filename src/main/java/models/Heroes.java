package models;

import java.util.ArrayList;
import java.util.List;

public class Heroes {
    String name;
    int age;
    String superPower;
    String weakness;

    public static List<Heroes> heroes = new ArrayList<>();

    public Heroes(String name, int age, String superPower, String weakness){
        this.name = name;
        this.age = age;
        this.superPower = superPower;
        this.weakness = weakness;
        heroes.add(this);
    }
    public String heroName(){
        return name;
    }
    public String heroPower(){
        return superPower;
    }
}
