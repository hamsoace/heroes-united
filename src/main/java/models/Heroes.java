package models;

import java.util.ArrayList;
import java.util.List;

public class Heroes {
    private int id;
   private String superName;
   private int age;
   private String superPower;
   private String weakness;
   private int teamId;


    public Heroes(String superName, int age, String superPower, String weakness, int teamId) {
        this.superName = superName;
        this.age = age;
        this.superPower = superPower;
        this.weakness = weakness;
        this.teamId = teamId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return superName;
    }

    public void setName(String superName) {
        this.superName = superName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSuperPower() {
        return superPower;
    }

    public void setSuperPower(String superPower) {
        this.superPower = superPower;
    }

    public String getWeakness() {
        return weakness;
    }

    public void setWeakness(String weakness) {
        this.weakness = weakness;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }
}
