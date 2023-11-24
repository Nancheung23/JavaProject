package com.courseAssignment_standingTable;

import java.util.ArrayList;

public class Team {
    private String team;
    private int win;
    private int draw;
    private int lost;
    private int goals;
    private int goalsScored;
    private int scores;

    public Team(ArrayList<Game> games, String team) {
        this.team = team;
        boolean indicator = false;
        for (Game game : games) {
            if (game.getHomeTeam().equalsIgnoreCase(team)) {
                indicator = true;
                this.goals += game.getGoalsHome();
                this.goalsScored += game.getGoalsVisiting();
                switch (game.getResult()) {
                    case 'W':
                        this.win++;
                        this.scores += 3;
                        break;
                    case 'D':
                        this.scores += 1;
                        this.draw++;
                        break;
                    case 'L':
                        this.lost++;
                        break;
                    default:
                        System.out.println("Error");
                        return;
                }
            } else if (game.getVisitingTeam().equalsIgnoreCase(team)) {
                indicator = true;
                this.goals += game.getGoalsVisiting();
                this.goalsScored += game.getGoalsHome();
                switch (game.getResult()) {
                    case 'W':
                        this.lost++;
                        break;
                    case 'D':
                        this.scores += 1;
                        this.draw++;
                        break;
                    case 'L':
                        this.win++;
                        this.scores += 3;
                        break;
                    default:
                        System.out.println("Error");
                        return;
                }
            }
        }
        if (indicator) {
            System.out.printf("Generate team %s's data successfully\n", team);
        } else {
            throw new IllegalArgumentException("Can't collect team data...");
        }
    }

    public String getTeam() {
        return team;
    }

    public int getWin() {
        return win;
    }

    public int getDraw() {
        return draw;
    }

    public int getLost() {
        return lost;
    }

    public int getGoals() {
        return goals;
    }

    public int getGoalsScored() {
        return goalsScored;
    }

    public int getScores() {
        return scores;
    }
}
