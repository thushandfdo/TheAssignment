package org.example;

import java.io.*;
import java.net.URL;
import java.util.Arrays;
import java.util.Scanner;

public class DataRetrievalProgram {

    public static void main(String[] args) {
        DataRetrievalProgram program = new DataRetrievalProgram();

        // Read data from the CSV file and create an array of Team objects
        Team[] teams = program.readDataFromCSV();

        // Perform data analysis
        calculateNetGoals(teams);
        sortTeamsByNetGoals(teams);
        sortTeamsByGoalsAgainst(teams);
        sortTeamsByGoalsFor(teams);
        displayBestPerformingTeam(teams);
        displayTeamsByNetGoals(teams);

        // Allow the user to filter teams based on group
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a group (A, B, C, D) to filter teams: ");
        String selectedGroup = scanner.nextLine().toUpperCase();
        displayTeamsByGroup(teams, selectedGroup);
    }

    private Team[] readDataFromCSV() {
        Team[] teams = null;

        try {
            URL resourceUrl = DataRetrievalProgram.class.getClassLoader().getResource("teams.csv");
            assert resourceUrl != null;

            BufferedReader reader = new BufferedReader(new InputStreamReader(resourceUrl.openStream()));

            String line;
            int count = 0;

            // Count the number of teams
            reader.readLine(); // Skip headers

            line = reader.readLine();
            while (line != null) {
                count++;
                line = reader.readLine();
            }
            teams = new Team[count];
            reader.close();

            // Read and create team objects
            int i = 0;
            reader = new BufferedReader(new InputStreamReader(resourceUrl.openStream()));
            while ((line = reader.readLine()) != null) {
                if (!line.equals("Team Name,Team Code,Goals For,Goals Against,Group")) {
                    String[] data = line.split(",");
                    String teamName = data[0];
                    String teamCode = data[1];
                    int goalsFor = Integer.parseInt(data[2]);
                    int goalsAgainst = Integer.parseInt(data[3]);
                    String group = data[4];

                    teams[i] = new Team(teamName, teamCode, goalsFor, goalsAgainst, group);
                    i++;
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error reading from CSV file: " + e.getMessage());
        }
        return teams;
    }

    private static void calculateNetGoals(Team[] teams) {
        for (Team team : teams) {
            team.calculateNetGoals();
        }
    }

    private static void sortTeamsByNetGoals(Team[] teams) {
        Arrays.sort(teams, (team1, team2) -> Integer.compare(team2.getNetGoals(), team1.getNetGoals()));
        System.out.println("\nTeams sorted by net goals (descending order):");
        displayTeams(teams);
    }

    private static void sortTeamsByGoalsAgainst(Team[] teams) {
        Arrays.sort(teams, (team1, team2) -> Integer.compare(team2.getGoalsAgainst(), team1.getGoalsAgainst()));
        System.out.println("\nTeams sorted by goals against (descending order):");
        displayTeams(teams);
    }

    private static void sortTeamsByGoalsFor(Team[] teams) {
        Arrays.sort(teams, (team1, team2) -> Integer.compare(team2.getGoalsFor(), team1.getGoalsFor()));
        System.out.println("\nTeams sorted by goals for (descending order):");
        displayTeams(teams);
    }

    private static void displayBestPerformingTeam(Team[] teams) {
        Team bestTeam = teams[0];
        System.out.println("\nBest Performing Team (highest net goals):");
        System.out.println(bestTeam.toCSVString());
    }

    private static void displayTeamsByNetGoals(Team[] teams) {
        System.out.println("\nAll teams sorted by net goals:");
        displayTeams(teams);
    }

    private static void displayTeamsByGroup(Team[] teams, String group) {
        System.out.println("\nTeams in Group " + group + ":");
        for (Team team : teams) {
            if (team.getGroup().equals(group)) {
                System.out.println(team.toCSVString());
            }
        }
    }

    private static void displayTeams(Team[] teams) {
        for (Team team : teams) {
            System.out.println(team.toCSVString());
        }
    }
}
