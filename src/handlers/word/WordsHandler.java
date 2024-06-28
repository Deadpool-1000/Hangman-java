package handlers.word;

import models.Word;
import utils.database.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class WordsHandler {
    private static final Connection conn = DatabaseConnectionManager.getInstance().conn;
    private static final String ADD_WORD_QUERY = "INSERT INTO words VALUES(?,?)";
    private static final String RANDOM_WORD_QUERY = "SELECT * FROM words ORDER BY RAND() LIMIT 1";
    private static final String READ_WORDS_QUERY = "SELECT * FROM words LIMIT 2 OFFSET ?";

    public static void addWord(String text, String hint){
        try{
            PreparedStatement addWordQuery = conn.prepareStatement(ADD_WORD_QUERY);
            addWordQuery.setString(1, text);
            addWordQuery.setString(2, hint);
            addWordQuery.execute();
        } catch(Exception e){
            System.out.println(e);
        }
    }

    public static Word getRandomWord(){
        try{
            PreparedStatement randomWord = conn.prepareStatement(RANDOM_WORD_QUERY);
            ResultSet rst = randomWord.executeQuery();
            rst.next();
            return new Word(rst.getString("word"), rst.getString("hint"));
        } catch (Exception e){
            System.out.println(e);
        }
        return null;
    }


    public static ArrayList<Word> getWords(int pageNumber){
        try{
            PreparedStatement getWordsQuery = conn.prepareStatement(READ_WORDS_QUERY);
            getWordsQuery.setInt(1, (pageNumber-1)*2);
            ResultSet rst = getWordsQuery.executeQuery();
            ArrayList<Word> words = new ArrayList<>();
            while(rst.next()){
                words.add(new Word(rst.getString("word"), rst.getString("hint")));
            }
            return words;
        } catch(Exception e){
            System.out.println(e);
        }
        return null;
    }
}
