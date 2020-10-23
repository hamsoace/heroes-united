import DAO.SqlHero;
import DAO.SqlTeam;
import models.Heroes;
import models.Team;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class App {
    public static void main(String[] args){

        String connectionString = "jdbc:h2:~/HeroesUnited.db;INIT=RUNSCRIPT from 'classpath:db/query.sql'";
        Connection con;
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        SqlHero heroesDAO = new SqlHero(sql2o);
        SqlTeam teamDAO = new SqlTeam(sql2o);


        Map<String, Object> model = new HashMap<>();
        List<String> myString = new ArrayList<>();


        get("/", (request, response) -> {
            //System.out.println(teamDAO.getAllTeams());
//            List<Team> team = teamDAO.getAllTeams();
            model.put("teams" , teamDAO.getAllTeams());
            model.put("heroes", heroesDAO.getAllHeroes());
           return new ModelAndView(model,"index.hbs");
        }, new HandlebarsTemplateEngine());

//        get("/addteam", (request, response) -> {
//            return new ModelAndView(model,"team-form.hbs");
//        }, new HandlebarsTemplateEngine());

        get("/addteam", (request, response) -> {
            model.put("teams", teamDAO.getAllTeams());
            return  new ModelAndView(model, "team-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/addteam", (request, response) ->{
            String name = request.queryParams("team-name");
            String cause = request.queryParams("cause");
            int size = Integer.parseInt(request.queryParams("size"));
            Team newTeam = new Team(name,cause,size);
            teamDAO.addTeam(newTeam);
            model.put("teams", teamDAO.getAllTeams());
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/addhero", (request, response) -> {
            model.put("teams", teamDAO.getAllTeams());
            return new ModelAndView(model, "hero-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/addhero", (request, response) ->{
            String name = request.queryParams("supername");
            String superPower = request.queryParams("power");
            String weakness = request.queryParams("weakness");
            int age = Integer.parseInt(request.queryParams("age"));
            int teamId = Integer.parseInt(request.queryParams("team"));
            Heroes newHero = new Heroes(name,age,weakness,superPower,teamId);
            heroesDAO.addHeroes(newHero);
            model.put("heroes", heroesDAO.getAllHeroes());
            model.put("teams", teamDAO.getAllTeams());
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());
    }
}
