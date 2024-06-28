package models;

import handlers.users.UsersHandler;
import utils.statics.Statics;

import java.sql.Timestamp;


public class User {
    private final String userId;
    private final String username;
    private double highScore;
    private int totalGames;
    private int gamesWon;
    private Timestamp hsScoredOn;
    private final String role;

    public User(String userId, String username, float highScore, int totalGames, int gamesWon, Timestamp hsScoredOn, String role){
        this.username = username;
        this.highScore = highScore;
        this.totalGames = totalGames;
        this.gamesWon = gamesWon;
        this.hsScoredOn = hsScoredOn;
        this.role = role;
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public double getHighScore() {
        return highScore;
    }

    public int getTotalGames() {
        return totalGames;
    }

    public int getGamesWon() {
        return gamesWon;
    }

    public Timestamp getHsScoredOn() {
        return hsScoredOn;
    }

    public String getRole() {
        return role;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserStats(GamePlaySummary summary){
        if(this.highScore < summary.getScore()){
            this.highScore = summary.getScore();
            this.hsScoredOn = new Timestamp(System.currentTimeMillis());
        }

        this.totalGames += summary.getTotalGames();
        this.gamesWon += summary.getWonGames();
    }

    public void printUserStatement(){
        System.out.println("---------------------------------------");
        System.out.println(Statics.ANSI_YELLOW + "Your stats" + Statics.ANSI_RESET);
        System.out.println("Player name: " + this.username);
        System.out.println("HighScore: " + this.highScore);
        System.out.println("Total Games: "+ this.totalGames);
        System.out.println("Games Won: " + this.gamesWon);
        System.out.println("---------------------------------------");
    }


    public void saveProfile(){
        UsersHandler.saveProfile(this);
    }
}
