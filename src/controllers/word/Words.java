package controllers.word;

import handlers.word.WordsHandler;
import models.Word;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Words {

    public static void addWord(){
        try{
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("How many words do you want to enter: ");
            int numOfWords = Integer.parseInt(input.readLine());
            while(numOfWords != 0){
                System.out.print("New Word: ");
                String word = input.readLine();
                System.out.print("Hint for the new Word: ");
                String text = input.readLine();
                WordsHandler.addWord(word, text);
                numOfWords--;
            }
        } catch(Exception e){
            System.out.println(e);
        }
    }

    public static Word randomWord(){
        return WordsHandler.getRandomWord();
    }
    public static void updateWord(){

    }
    public static void deleteWord(){

    }
    public static void readWords(){
        Scanner sc = new Scanner(System.in);
        int pageNumber = 1;
        ArrayList<Word> words;
        String response = "y";
        while(!response.equals("n")){
            words = WordsHandler.getWords(pageNumber++);
            printWords(words);
            System.out.print("Do you want more pages:(y/n) ");
            response = sc.next().toLowerCase();
            while(!(response.equals("y") || response.equals("n"))){
                System.out.print("Enter valid input: ");
                response = sc.next();
            }
        }
    }

    private static void printWords(ArrayList<Word> words){
        if(!words.isEmpty()){
            for(Word word: words){
                System.out.println("---------------------------------");
                System.out.println("Word: " + word.getText());
                System.out.println("Hint: " + word.getHint());
                System.out.println("---------------------------------");
            }
        } else {
            System.out.println("No words to show");
        }
    }
}
