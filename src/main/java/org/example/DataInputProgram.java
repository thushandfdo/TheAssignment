package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class DataInputProgram {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of teams: ");
        int numTeams = scanner.nextInt();
        scanner.nextLine();  // Consume the newline character

        // Create an array to store team details
        Team[] teams = new Team[numTeams];

        for (int i = 0; i < numTeams; i++) {
            System.out.println("Enter details for Team " + (i + 1) + ":");
            System.out.print("Team Name: ");
            String teamName = scanner.nextLine();
            System.out.print("Team Code: ");
            String teamCode = scanner.nextLine();

            System.out.print("Goals Scored by the Team: ");
            int goalsFor = scanner.nextInt();

            System.out.print("Goals Scored against the Team: ");
            int goalsAgainst = scanner.nextInt();

            scanner.nextLine();  // Consume the newline character

            System.out.print("Group (A, B, C, D): ");
            String group = scanner.nextLine().toUpperCase();

            // Validate input
            if (teamName.isEmpty() || teamCode.isEmpty() || goalsFor < 0 || goalsAgainst < 0 ||
                    !(group.equals("A") || group.equals("B") || group.equals("C") || group.equals("D"))) {
                System.out.println("Invalid input. Please try again.");
                i--;  // Repeat input for this team
                continue;
            }

            // Create a team object and store it in the array
            teams[i] = new Team(teamName, teamCode, goalsFor, goalsAgainst, group);
        }

        // Store the data in a CSV file
        storeDataToCSV(teams);
        System.out.println("Data stored successfully.");
    }

    private static void storeDataToCSV(Team[] teams) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("src/main/resources/teams.csv"))) {
            writer.println("Team Name,Team Code,Goals For,Goals Against,Group");
            for (Team team : teams) {
                writer.println(team.toCSVString());
            }
        } catch (IOException e) {
            System.err.println("Error writing to CSV file: " + e.getMessage());
        }
    }
}


