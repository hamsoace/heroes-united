package models;

public class Team {
    private int id;
    private String teamName;
    private String teamCause;
    private int teamSize;

    public Team(String teamName, String teamCause, int teamSize) {
        this.teamName = teamName;
        this.teamCause = teamCause;
        this.teamSize = teamSize;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamCause() {
        return teamCause;
    }

    public void setTeamCause(String teamCause) {
        this.teamCause = teamCause;
    }

    public int getTeamSize() {
        return teamSize;
    }

    public void setTeamSize(int teamSize) {
        this.teamSize = teamSize;
    }
}
