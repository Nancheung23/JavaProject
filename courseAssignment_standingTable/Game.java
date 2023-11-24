package com.courseAssignment_standingTable;

import java.util.Map;

public class Game {
    private String homeTeam;
    private String visitingTeam;
    private int goalsHome;
    private int goalsVisiting;
    private Map<String, Integer> playerHomeStatus;
    private Map<String, Integer> playerVisitingStatus;
    // inner variable
    private char result;

    public Game() {
    }

    public Game(String homeTeam, String visitingTeam, int goalsHome, int goalsVisiting, char result, Map<String, Integer> playerHomeStatus, Map<String, Integer> playerVisitingStatus) {
        this.homeTeam = homeTeam;
        this.visitingTeam = visitingTeam;
        this.goalsHome = goalsHome;
        this.goalsVisiting = goalsVisiting;
        this.playerHomeStatus = playerHomeStatus;
        this.playerVisitingStatus = playerVisitingStatus;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public String getVisitingTeam() {
        return visitingTeam;
    }

    public void setVisitingTeam(String visitingTeam) {
        this.visitingTeam = visitingTeam;
    }

    // >=0
    public int getGoalsHome() {
        return goalsHome;
    }

    public void setGoalsHome(int goalsHome) {
        this.goalsHome = goalsHome;
    }

    // >=0
    public int getGoalsVisiting() {
        return goalsVisiting;
    }

    public void setGoalsVisiting(int goalsVisiting) {
        this.goalsVisiting = goalsVisiting;
    }

    // W / L / D ==> 3 / 0 / 1
    public Map<String, Integer> getPlayerHomeStatus() {
        return playerHomeStatus;
    }

    public void setPlayerHomeStatus(Map<String, Integer> playerHomeStatus) {
        this.playerHomeStatus = playerHomeStatus;
    }

    public Map<String, Integer> getPlayerVisitingStatus() {
        return playerVisitingStatus;
    }

    public void setPlayerVisitingStatus(Map<String, Integer> playerVisitingStatus) {
        this.playerVisitingStatus = playerVisitingStatus;
    }

    public char getResult() {
        return result;
    }

    public void setResult(char result) {
        this.result = result;
    }
}
