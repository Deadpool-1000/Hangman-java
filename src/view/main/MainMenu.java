package view.main;

import controllers.authentication.Login;
import controllers.authentication.Signup;
import models.User;
import view.players.PlayersMenu;
import view.admin.AdminMenu;

import java.util.Scanner;

public class MainMenu {
    private static final char LOGIN = 'l';
    private static final char SIGNUP = 's';
    private static final char QUIT = 'q';
    private static final String ROLE_PLAYER = "player";
    private static final String ROLE_ADMIN = "admin";

    public static void showMainMenu(){
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.flush();
            printMenu();
            char usersChoice = sc.next().charAt(0);
            switch(usersChoice){
                case LOGIN:{
                    System.out.println("\rLogin");
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    String[] userCredentials = loginMenu(sc);
                    Login log = new Login(userCredentials[0], userCredentials[1]);
                    User user = log.login();
                    if(user != null){
                        if(user.getRole().equals(ROLE_PLAYER)){
                            PlayersMenu pMenu = new PlayersMenu(user);
                            pMenu.showMenu();
                        } else if(user.getRole().equals(ROLE_ADMIN)){
                            AdminMenu adMenu = new AdminMenu(user);
                            adMenu.showMenu();
                        } else {
                            System.out.println("Invalid user exiting...");
                            System.exit(1);
                        }
                    } else {
                        System.out.println("User not found");
                    }
                    break;
                }
                case SIGNUP: {
                    System.out.flush();
                    signupMenu(sc);
                    System.out.println("Signing you up...");
                    break;
                }
                case QUIT: {
                    System.exit(1);
                }
                default:
                    System.err.println("Invalid input");
            }
        }
    }

    static void printMenu(){
        System.out.println("Welcome to Hangman");
        System.out.println("""
            'l': login,
            's': signup,
            'q': quit
        """);
        System.out.print("Your choice: ");
    }

    static String[] loginMenu(Scanner sc){
        System.out.println("Please enter the following details");
        System.out.print("Username: ");
        String username = sc.next();
        System.out.print("Password: ");
        String password = sc.next();
        return new String[]{username, password};
    }

    static void signupMenu(Scanner sc){
        System.out.println("Please enter the following details");
        System.out.print("Username: ");
        String username = sc.next();
        System.out.print("Password: ");
        String password = sc.next();
        Signup sp = new Signup(username, password);
        boolean success = sp.signup();
        if(success){
            System.out.flush();
            System.out.println("Signup successful.");
            System.out.println("Please login to continue");
            loginMenu(sc);
        }
    }

}
