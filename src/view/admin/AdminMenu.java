package view.admin;

import models.User;
import utils.statics.Statics;
import view.players.PlayersMenu;

import java.util.Scanner;

public class AdminMenu extends PlayersMenu {
    public static final String WORD = "w";

    public AdminMenu(User user) {
        super(user);
    }

    @Override
    public void showMenu(){
        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.flush();
            printMenu();
            System.out.print("Your choice: ");
            String usersChoice = sc.next();
            switch (usersChoice){
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
                case WORD: {
                    WordsMenu.showMenu();
                }
                default:{
                    System.out.println("Please enter a valid value");
                }
            }
        }
    }

    @Override
    public void printMenu(){
        System.out.println("-------------------------------");
        System.out.println(Statics.ANSI_BLUE + "Welcome " + user.getUsername() + Statics.ANSI_RESET);
        System.out.println("""
               Press:
               'p': New game
               'w': Words section
               'l': leaderboard
               's': profile
               'q': back
           """);
        System.out.println("-------------------------------");
    }
}
