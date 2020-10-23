package DAO;

import models.Heroes;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class SqlHero implements HeroesDAO{
    private final Sql2o sql2o;
    public SqlHero(Sql2o sql2o){
        this.sql2o = sql2o;
    }

    @Override
    public List<Heroes> getAllHeroes() {
       String sql = "SELECT * FROM heroes";
       try(Connection con = sql2o.open()){
           return con.createQuery(sql).executeAndFetch(Heroes.class);
       }catch (Sql2oException ex){
           System.out.println(ex);
           return null;
       }
    }

    @Override
    public void addHeroes(Heroes heroes) {
        String sql = "INSERT INTO heroes (superName, age, superPower, weakness, teamId) VALUE (:superName, :age, :superPower, :weakness, :teamId)";
        try(Connection con = sql2o.open()){
            con.createQuery(sql).bind(heroes).executeUpdate();
        }catch(Sql2oException ex){
            System.out.println(ex);
        }
    }
}
