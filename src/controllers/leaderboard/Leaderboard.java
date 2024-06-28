package controllers.leaderboard;

import handlers.leaderboard.LeaderboardHandler;
import models.LeaderboardUser;

import java.util.ArrayList;

public class Leaderboard {
    public static void showLeaderboard(){
        ArrayList<LeaderboardUser> users = LeaderboardHandler.getLeaderboard();
        System.out.printf("--------------------------------%n");
        System.out.printf(" Leaderboard         %n");

        System.out.printf("--------------------------------%n");
        System.out.printf("| %-30s | %-30s | %30s |%n", "username", "High Score", "Scored on");

        if(users != null) {
            for (LeaderboardUser user : users) {
                System.out.printf("| %-30s | %-30.2f | %30s |%n", user.getUsername(), user.getHigh_score(), String.format(" %tc", user.getHsScoredOn()));
            }
        } else {
            System.out.println("No users on the leaderboard");
        }
        System.out.printf("--------------------------------%n");
    }
}
