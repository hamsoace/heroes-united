import DAO.Sql2oHeroDAO;
import DAO.Sql2oSquadDAO;
import models.Hero;
import models.Squad;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;
public class App {
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }

    public static void main(String[] args) {
        port(getHerokuAssignedPort());
        staticFileLocation("/public");

//        String connectionString = "jdbc:postgresql://localhost:5432/moringa";
//        Connection con;
//        Sql2o sql2o = new Sql2o(connectionString, "moringa", "Access");
        String connectionString = "jdbc:postgresql://ec2-54-160-18-230.compute-1.amazonaws.com:5432/dfoonudsrjcubk"; //!
        Sql2o sql2o = new Sql2o(connectionString, "rirffgosexpsjs", "98f53c6deed31854ca59a5ce61bce0a749c32aa6fd36100ac453f9d9fd0fdc6c"); //!
        Sql2oSquadDAO squadDAO = new Sql2oSquadDAO(sql2o);
        Sql2oHeroDAO heroDAO = new Sql2oHeroDAO(sql2o);
        Map<String, Object> model = new HashMap<>();

        List<String> myStrings = new ArrayList<>();


        get("/", (req, res) -> {
            model.put("squads", squadDAO.getAllSquads());
            model.put("heroes", heroDAO.getAllHeroes());
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/addsquad", (req, res) -> {
            return new ModelAndView(model, "squad-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/addsquad", (req, res) -> {
            String name = req.queryParams("name");
            String cause = req.queryParams("cause");
            int size = Integer.parseInt(req.queryParams("size"));
            Squad newSquad = new Squad(name, cause, size);
            squadDAO.addSquad(newSquad);
            model.put("squads", squadDAO.getAllSquads());
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/addhero", (req, res) -> {
            model.put("squads", squadDAO.getAllSquads());
            return new ModelAndView(model, "hero-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/addhero", (req, res) -> {
            String name = req.queryParams("name");
            String power = req.queryParams("power");
            String weakness = req.queryParams("weakness");
            int age = Integer.parseInt(req.queryParams("age"));
            int squadId = Integer.parseInt(req.queryParams("squad"));
            Hero newHero = new Hero(name, power, weakness, age, squadId);
            heroDAO.addHero(newHero);
            model.put("squads", squadDAO.getAllSquads());
            model.put("heroes", heroDAO.getAllHeroes());
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/heroes/:id", (req, res) -> {
            int id = Integer.parseInt(req.params("id"));
            Hero hero = heroDAO.getHeroById(id);
            Squad squad = squadDAO.getSquadById(hero.getSquadId());
            model.put("hero", heroDAO.getHeroById(id));
            model.put("squad", squad);
            return new ModelAndView(model, "hero-details.hbs");
        }, new HandlebarsTemplateEngine());

        get("/squads/:id", (req, res) -> {
            int id = Integer.parseInt(req.params("id"));
            model.put("heroes", squadDAO.getSquadHeroesById(id));
            model.put("squad", squadDAO.getSquadById(id));
            return new ModelAndView(model, "squad-details.hbs");
        }, new HandlebarsTemplateEngine());

        get("/deletehero/:id", (req, res) -> {
            int id = Integer.parseInt(req.params("id"));
            heroDAO.deleteHeroById(id);
            model.put("heroes", heroDAO.getAllHeroes());
            model.put("squads", squadDAO.getAllSquads());
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/deletesquad/:id", (req, res) -> {
            int id = Integer.parseInt(req.params("id"));
            squadDAO.deleteSquadById(id);
            squadDAO.deleteHeroesInSquad(id);
            model.put("heroes", heroDAO.getAllHeroes());
            model.put("squads", squadDAO.getAllSquads());
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/edithero/:id", (req, res) -> {
            int id = Integer.parseInt(req.params("id"));
            model.put("editHero", true);
            model.put("hero", heroDAO.getHeroById(id));
            model.put("squads", squadDAO.getAllSquads());
            return new ModelAndView(model, "hero-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/edithero/:id", (req, res) -> {
            int id = Integer.parseInt(req.params("id"));
            String name = req.queryParams("name");
            String power = req.queryParams("power");
            String weakness = req.queryParams("weakness");
            int age = Integer.parseInt(req.queryParams("age"));
            int squadId = Integer.parseInt(req.queryParams("squad"));
            heroDAO.updateHero(id,name,power,weakness,age,squadId);
            model.put("hero", heroDAO.getHeroById(id));
            return new ModelAndView(model, "hero-details.hbs");
        }, new HandlebarsTemplateEngine());

    }
}
