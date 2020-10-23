package DAO;

import models.Team;

import java.util.List;

public interface TeamDAO {

    List<Team> getAllTeams();

    void addTeam(Team team);
}
