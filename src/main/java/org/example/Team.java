package org.example;

public class Team {
    private String teamName;
    private String teamCode;
    private int goalsFor;
    private int goalsAgainst;
    private String group;
    private int netGoals;

    public Team(String teamName, String teamCode, int goalsFor, int goalsAgainst, String group) {
        this.teamName = teamName;
        this.teamCode = teamCode;
        this.goalsFor = goalsFor;
        this.goalsAgainst = goalsAgainst;
        this.group = group;
        calculateNetGoals();
    }

    public void calculateNetGoals() {
        this.netGoals = goalsFor - goalsAgainst;
    }

    public int getNetGoals() {
        return netGoals;
    }

    public int getGoalsAgainst() {
        return goalsAgainst;
    }

    public int getGoalsFor() {
        return goalsFor;
    }

    public String getGroup() {
        return group;
    }

    public String toCSVString() {
        return teamName + "," + teamCode + "," + goalsFor + "," + goalsAgainst + "," + group;
    }
}
