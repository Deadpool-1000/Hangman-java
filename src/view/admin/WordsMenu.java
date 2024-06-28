package view.admin;

import controllers.word.Words;
import utils.statics.Statics;

import java.util.Scanner;

public class WordsMenu {
    private static final String ADD = "a";
    private static final String READ = "r";
    private static final String UPDATE = "u";
    private static final String DELETE = "d";
    private static final String QUIT = "q";


    public static void showMenu(){
        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.flush();
            printMenu();
            System.out.print("Your choice: ");
            String usersChoice = sc.next();
            switch (usersChoice) {
                case ADD : {
                    Words.addWord();
                    break;
                }
                case READ : {
                    Words.readWords();
                    break;
                }
                case UPDATE : {
                    Words.updateWord();
                    break;
                }
                case DELETE : {
                    Words.deleteWord();
                }
                case QUIT: {
                    return;
                }
            }
        }
    }

    private static void printMenu(){
        System.out.println("-------------------------------");
        System.out.println(Statics.ANSI_YELLOW + "Words section: " + Statics.ANSI_RESET);
        System.out.println("What do you want to do?");
        System.out.println("""
                'c': add new word
                'r': print available words
                'u': update word
                'd': delete word
                'q': quit
                """);
        System.out.println("-------------------------------");
    }
}
