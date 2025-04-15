package com.pluralsight;

import java.util.Scanner;

public class HighScoreWins {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter a score: ");
        String game_score_string = scanner.nextLine().trim();
        String[] halves = game_score_string.split("\\|");
        String[] teams = halves[0].split(":");
        String[] scores = halves[1].split(":");
        int scoreA = Integer.parseInt(scores[0]);
        int scoreB = Integer.parseInt(scores[1]);
        System.out.println(scoreA == scoreB ? new StringBuilder().append("Teams ").append(teams[0]).append(" & ").append(teams[1]).append(" Tied!").toString(): "Winner: " + (scoreA > scoreB ? teams[0] : teams[1])); //I wanted to test out the string builder
    }
}
