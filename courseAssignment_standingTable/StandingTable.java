package com.courseAssignment_standingTable;

import java.util.*;

public class StandingTable {
    ArrayList<Game> games = new ArrayList<>();
    ArrayList<Team> teams = new ArrayList<>();
    // scanner.nextline() need to add into func *
    Scanner scanner = new Scanner(System.in);
    // inner class
    private static class Pair {
        int goals;
        String team;
        Pair(int goals, String team) {
            this.goals = goals;
            this.team = team;
        }
    }

    // frequent use func
    private boolean confirm() {
        System.out.print("(Y / N) ? : ");
        // double check
        boolean confirmation = scanner.next().equalsIgnoreCase("Y");
        scanner.nextLine();
        return confirmation;
    }

    private int checkInt() {
        try {
            // get input (Integer)
            int input = scanner.nextInt();
            scanner.nextLine();
            return input;
        } catch (InputMismatchException e) {
            // clear cache
            scanner.nextLine();
            // --> default case
            return -1;
        }
    }

    // main loop
    public void startSystem() {
        while (true) {
            System.out.println("====== Welcome to Standing Table system ======");
            System.out.println("1. Add scores.");
            System.out.println("2. Standing Table");
            System.out.println("3. Player List");
            System.out.println("4. Search Player");
            System.out.println("5. Quit");
            System.out.println("==============================================");
            System.out.print("Please select one service to continue: ");
            int select = checkInt();
            switch (select) {
                case 1:
                    addScore();
                    break;
                case 2:
                    standingTable();
                    break;
                case 3:
                    playerList();
                    break;
                case 4:
                    searchPlayer();
                    break;
                case 5:
                    System.out.println("Ready to quit, please confirm.");
                    if (!confirm()) {
                        System.out.println("You cancelled.");
                        break;
                    } else {
                        return;
                    }
            }
        }
    }
    private void addScore() {
        while (true) {
            System.out.println("====== Welcome to Score Operation Panel ======");
            System.out.println("Now you are going to create a game info, are you sure?");
            if (confirm()) {
                Game game = new Game();
                game.setHomeTeam(addHomeTeam());
                game.setVisitingTeam(addVisitingTeam());
                int home = addHomeGoals();
                game.setGoalsHome(home);
                game.setPlayerHomeStatus(addPlayer(home));
                int visiting = addVisitingGoals();
                game.setGoalsVisiting(visiting);
                game.setPlayerVisitingStatus(addPlayer(visiting));
                System.out.println(printResult(game.getHomeTeam(), game.getVisitingTeam(), game.getGoalsHome(), game.getGoalsVisiting(), game));
                System.out.println("Add to Standing table?");
                if (confirm()) {
                    games.add(game);
                    System.out.println("Data saved");
                    return;
                } else {
                    System.out.println("Back to start ...");
                }
            } else {
                return;
            }
        }
    }

    private void standingTable() {
        if (games.isEmpty()) {
            System.out.println("No team saved yet, add first!");
            return;
        }
        for (Game game : games) {
            if (checkTeam(game.getHomeTeam())) {
                teams.add(new Team(games, game.getHomeTeam()));
            }
            if (checkTeam(game.getVisitingTeam())) {
                teams.add(new Team(games, game.getVisitingTeam()));
            }
        }
        // sort arrayList<team>
        sortList(teams);
        System.out.println("====== Welcome to Standing Table ======");
        System.out.printf("%-15s %-5s %-5s %-5s %-10s %-6s%n", "Team", "Win", "Draw", "Lost", "Goals", "Points");
        // clear index over 12
        if (teams.size() > 12) {
            teams.subList(11, teams.size()).clear();
        }
        for (Team team : teams) {
            if (team.getTeam().length() >= 16) {
                String sub = team.getTeam().substring(0, 16);
                System.out.printf("%s %-5d %-5d %-5d %-10s %-6d%n", sub, team.getWin(), team.getDraw(), team.getLost(), team.getGoals() + "-" + team.getGoalsScored(), team.getScores());
            }
            if (team.getTeam().length() < 16) {
                String sub = team.getTeam() + " ".repeat(16 - team.getTeam().length());
                System.out.printf("%s %-5d %-5d %-5d %-10s %-6d%n", sub, team.getWin(), team.getDraw(), team.getLost(), team.getGoals() + "-" + team.getGoalsScored(), team.getScores());
            }
        }
    }
    private void searchPlayer() {
        while (true) {
            if (games.isEmpty()) {
                System.out.println("No team saved yet, add first!");
                return;
            }
            System.out.println("====== Welcome to Search page ======");
            System.out.println("Are you going to search player stat?");
            if (confirm()) {
                System.out.println("Please input player name you want to search: ");
                String playerName = scanner.nextLine();
                Map<String, Pair> playerStats = player(games);
                for (Map.Entry<String, Pair> entry : playerStats.entrySet()) {
                    if (entry.getKey().equals(playerName)) {
                        System.out.println("Player found!");
                        System.out.printf("%-15s %-5s %6s%n", "Player", "Points", "Team Name");
                        System.out.printf("%-15s %-5d %6s%n", playerName, entry.getValue().goals, entry.getValue().team);
                        return;
                    }
                }
                System.out.println("Can't find player, please check your input!");
            } else {
                System.out.println("Now quiting...");
                return;
            }
        }
    }
    // from bigger to smaller, sort
    private void sortList(ArrayList<Team> teams) {
        Team temp;
        for (int i = 0; i < teams.size(); i++) {
            for (int j = 0; j < teams.size() - i - 1; j++) {
                if (teams.get(j).getScores() < teams.get(j + 1).getScores()) {
                    temp = teams.get(j);
                    teams.set(j, teams.get(j + 1));
                    teams.set(j + 1, temp);
                }
            }
        }
    }

    // clear duplicated teams
    private boolean checkTeam(String name) {
        for (Team team : teams) {
            if (team.getTeam().equalsIgnoreCase(name)) {
                return false;
            }
        }
        return true;
    }
    // get player list and show a table
    private void playerList() {
        if (games.isEmpty()) {
            System.out.println("No team saved yet, add first!");
            return;
        }
        System.out.println("====== Welcome to Player List ======");
        System.out.printf("%-15s %-5s %6s%n", "Player", "Points", "Team Name");
        Map<String, Pair> playerStats = player(games);
        printPlayerStats(playerStats);
    }
    // save all players' info in one hashmap --> playerStats
    private Map<String, Pair> player(ArrayList<Game> games) {
        Map<String, Pair> playerStats = new HashMap<>();
        for (Game game : games) {
            updatePlayerStats(game.getPlayerHomeStatus(),playerStats,game.getHomeTeam());
            updatePlayerStats(game.getPlayerVisitingStatus(),playerStats,game.getVisitingTeam());
        }
        return playerStats;
    }
    // map --> (playerName: (goals : teamName))
    private void updatePlayerStats(Map<String, Integer> playerMap, Map<String, Pair> playerStats, String teamName) {
        if (playerMap == null) {
            return;
        }
        for (Map.Entry<String, Integer> entry : playerMap.entrySet()) {
            String playerName = entry.getKey();
            int goals = entry.getValue();
            Pair stats = playerStats.getOrDefault(playerName, new Pair(0, teamName));
            stats.goals += goals;
            playerStats.put(playerName, stats);
        }
    }
    //sort and print stats
    private void printPlayerStats(Map<String, Pair> playerStats) {
        playerStats.entrySet().stream()
                // compare goals and sort
                .sorted((e1, e2) -> Integer.compare(e2.getValue().goals, e1.getValue().goals))
                .forEach(e -> {
                    // for every entry, print info
                    String playerName = e.getKey();
                    Pair stats = e.getValue();
                    System.out.printf("%-15s %-5d %6s%n", playerName, stats.goals, stats.team);
                });
    }
    // add basic info
    private String addHomeTeam() {
        System.out.print("Who is home team?: ");
        return scanner.nextLine();
    }

    private String addVisitingTeam() {
        System.out.print("Who is visiting team?: ");
        return scanner.nextLine();
    }

    private int addHomeGoals() {
        System.out.print("Goals for home team?: ");
        int input = checkInt();
        if (input >= 0) {
            return input;
        } else {
            System.out.println("Wrong input. Input again!");
            addHomeGoals();
        }
        // no use
        return -1;
    }

    private int addVisitingGoals() {
        System.out.print("Goals for Visiting team?: ");
        int input = checkInt();
        if (input >= 0) {
            return input;
        } else {
            System.out.println("Wrong input. Input again!");
            addVisitingGoals();
        }
        // no use
        return -1;
    }

    // add players(hashmap) and result
    private Map<String, Integer> addPlayer(int scores) {
        Map<String, Integer> map = new HashMap<>();
        if (scores == 0) {
            return null;
        } else if (scores < 0) {
            // impossible
            return null;
        } else {
            do {
                System.out.print("Please input player name: ");
                String name = scanner.nextLine();
                System.out.print("Please input his score: ");
                int score = checkInt();
                if (score > 0) {
                    map.put(name, score);
                    scores -= score;
                } else {
                    System.out.println("Score should be greater than 0, please input again!");
                }
            } while (scores > 0);
            return map;
        }
    }

    // judge who win and return result
    private String printResult(String home, String visiting, int homeGoal, int visitingGoal, Game game) {
        if (homeGoal > visitingGoal) {
            game.setResult('W');
            return String.format("%s won %s %d-%d !", home, visiting, homeGoal, visitingGoal);
        } else if (homeGoal == visitingGoal) {
            game.setResult('D');
            return String.format("%s draw %s %d-%d !", home, visiting, homeGoal, visitingGoal);
        } else {
            game.setResult('L');
            return String.format("%s lost %s %d-%d !", home, visiting, homeGoal, visitingGoal);
        }
    }
}