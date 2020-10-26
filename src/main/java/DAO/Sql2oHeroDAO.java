package DAO;

import models.Hero;
import models.Squad;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oHeroDAO implements HeroDAO {

    private final Sql2o sql2o;

    public Sql2oHeroDAO(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public List<Hero> getAllHeroes() {
        String sql = "SELECT * FROM heroes";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .executeAndFetch(Hero.class);
        } catch (Sql2oException ex) {
            System.out.println(ex);
            return null;
        }
    }

    @Override
    public void addHero(Hero hero) {
        String sql = "INSERT INTO heroes (heroName, heroPower, heroWeakness, age, squadId) VALUES (:heroName, :heroPower, :heroWeakness, :age, :squadId)";
        try (Connection con = sql2o.open()) {
            int id = (int)  con.createQuery(sql, true)
                    .bind(hero)
                    .executeUpdate()
                    .getKey();
            hero.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public Hero getHeroById(int id) {
        String sql = "SELECT * FROM heroes WHERE id=:id";
        try(Connection con = sql2o.open()){
           return con.createQuery(sql)
                    .addParameter("id",id)
                    .executeAndFetchFirst(Hero.class);
        }catch (Sql2oException ex) {
            System.out.println(ex);
            return null;
        }

    }

    @Override
    public void deleteHeroById(int id) {
        String sql = "DELETE FROM heroes WHERE id=:id";
        try (Connection con = sql2o.open()) {
           con.createQuery(sql)
                    .addParameter("id",id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }

    }

    @Override
    public void updateHero(int id, String name, String power, String weakness, int age, int squadId) {
        String sql = "UPDATE heroes SET heroName = :name, age = :age, heroPower = :power, heroWeakness = :weakness, squadId = :squadId WHERE id=:id";        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id",id)
                    .addParameter("name",name)
                    .addParameter("power",power)
                    .addParameter("weakness",weakness)
                    .addParameter("age",age)
                    .addParameter("squadId",squadId)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }
}
