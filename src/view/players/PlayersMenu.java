package view.players;

import controllers.game.Game;
import controllers.leaderboard.Leaderboard;
import controllers.word.Words;
import models.GamePlaySummary;
import models.User;

import java.util.Scanner;

public class PlayersMenu {
    protected static final String PLAY = "p";
    protected static final String LEADERBOARD = "l";
    protected static final String STATS = "s";
    protected static final String QUIT = "q";
    protected final User user;

    public PlayersMenu(User user){
        this.user = user;
    }

    public void showMenu(){
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.flush();
            printMenu();
            System.out.print("Your choice: ");
            String usersChoice = sc.next();
            switch(usersChoice){
                case PLAY : {
                    play();
                    break;
                }
                case LEADERBOARD: {
                    leaderboard();
                    break;
                }
                case STATS : {
                    stats();
                    break;
                }
                case QUIT : {
                    System.out.flush();
                    return;
                }
                default:{
                    System.out.println("Please enter a valid value");
                }
            }
        }
    }

    public void printMenu(){
        System.out.println("-------------------------------");
        System.out.println("Welcome " + user.getUsername());
        System.out.println("""
               Press:
               'p': New game
               'l': leaderboard
               's': profile
               'q': back
           """);
        System.out.println("-------------------------------");

    }

    public void leaderboard(){
        Leaderboard.showLeaderboard();
    }

    public void stats(){
        this.user.printUserStatement();
    }

    public void play(){

        Game bs = new Game(Words.randomWord(), this.user, 1);
        GamePlaySummary summary = bs.startGame();
        this.user.setUserStats(summary);
        this.user.saveProfile();
    }
}
