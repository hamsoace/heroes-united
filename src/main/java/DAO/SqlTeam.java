package DAO;

import models.Team;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class SqlTeam implements TeamDAO{
    public SqlTeam(Sql2o sql2o){
        this.sql2o = sql2o;
    }

    private final Sql2o sql2o;

    @Override
    public List<Team> getAllTeams() {
        String sql = "SELECT * FROM heroes";
        try(Connection con = sql2o.open()) {
            return con.createQuery(sql).throwOnMappingFailure(false).executeAndFetch(Team.class);
        }catch (Sql2oException ex){
            System.out.println(ex);
            return null;
        }
    }

    @Override
    public void addTeam(Team team) {
        String sql = "INSERT INTO teams (teamName, teamCause, teamSize) VALUES (:teamName, :teamCause, :teamSize)";
        try(Connection con = sql2o.open()){
//            int id = (int) con.createQuery(sql, true).bind(team).executeUpdate().getKey();
//            team.getId();
            con.createQuery(sql).bind(team).executeUpdate();
        }catch(Sql2oException ex){
            System.out.println(ex);
        }
    }
}
