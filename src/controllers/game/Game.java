package controllers.game;

import models.*;
import utils.Utility;
import utils.statics.HangmanGraphic;

import java.util.*;

public class Game {
    private final User player;
    private final Word word;
    private final ArrayList<Character> alreadyGuessed;
    private int chances;
    private final int rounds;
    private int gamesWon;

    public Game(Word word, User player, int rounds){
        this.word = word;
        this.player = player;
        this.alreadyGuessed = new ArrayList<>();
        this.chances = 7;
        this.rounds = rounds;
        this.gamesWon = 0;
    }

    public GamePlaySummary startGame(){
        Scanner sc = new Scanner(System.in);
        printWelcome();
        ArrayList<Integer> scoreCard = new ArrayList<>();
        long startTime = System.currentTimeMillis();
        int rounds = this.rounds;
        while(rounds-- !=0){
            while(!allLetterGuessed() && chances != 0){
                printWord();
                System.out.print("Your choice: ");
                String userResponse = sc.next().toLowerCase();

                if(isLongerThanOne(userResponse)){
                    System.out.println("Enter a single character");
                    continue;
                }
                char charUserResponse = userResponse.charAt(0);
                if(isAlreadyGuessed(charUserResponse)){
                    System.out.println("Already guessed");
                    continue;
                }

                if(isChoiceCorrect(charUserResponse)){
                    System.out.println("Correct!");
                    alreadyGuessed.add(charUserResponse);
                } else {
                    System.out.println("Incorrect");
                    chances--;
                    System.out.println(HangmanGraphic.hanged[6-chances]);
                }
            }
            scoreCard.add(chances);
            if(allLetterGuessed()){
                System.out.println("You won 👏👏👏");
                this.gamesWon++;
                System.out.println("Your guess was correct the word is " + this.word.getText());
            } else {
                System.out.println("You lost 🥲🥲🥲");
                System.out.println("The word was " + this.word.getText());
            }
        }
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        double score = Utility.calculateScore(scoreCard, totalTime);
        System.out.format("You answered in  %.5f minutes\n", (double) totalTime/(1000*60*60));
        System.out.println("Score: " + score);

       return new GamePlaySummary(score, this.rounds, this.gamesWon);
    }

    private void printWelcome(){
        System.out.println("Lets start the game, " + player.getUsername());
        System.out.println("-------------Round 1-------------");
    }

    private void printWord(){
        for(int i = 0; i < word.getText().length(); i++){
            if(alreadyGuessed.contains(word.getText().charAt(i))){
                System.out.print(" " + word.getText().charAt(i) + " ");
            } else System.out.print(" _ ");

        }
        System.out.print("\t Hint: "+ word.getHint());
        System.out.println("\t"+ (7- chances) + "/7 chances");
    }

    private boolean allLetterGuessed(){
        //Verify if alreadyGuessed array has all characters of String word
       Set<Character> alGuessed = new HashSet<>(alreadyGuessed);
       Set<Character> wordAsSet = Utility.stringToSet(word.getText());
       return alGuessed.equals(wordAsSet);
    }

    private boolean isChoiceCorrect(char userChoice){
        return this.word.getText().indexOf(userChoice) != -1;
    }

    private boolean isAlreadyGuessed(char userResponse){
        return alreadyGuessed.contains(userResponse);
    }

    private boolean isLongerThanOne(String userResponse){
        return userResponse.length() > 1;
    }

}
