import models.Heroes;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class App {
    public static void main(String[] args){
        Heroes heroes;
        heroes = new Heroes("Batman", 40, "gadgets+intellect", "parents' death");

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("Heroes", Heroes.heroes);
            return new ModelAndView(model, "heroinfo.hbs");
        }, new HandlebarsTemplateEngine());
    }
}
