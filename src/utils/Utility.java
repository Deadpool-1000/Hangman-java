package utils;

import java.util.HashSet;
import java.util.ArrayList;

public class Utility {
    public static HashSet<Character> stringToSet(String text){
        char[] textAsChar = text.toCharArray();
        HashSet<Character> result = new HashSet<Character>();
        for(int i=0;i<text.length();i++){
            result.add(textAsChar[i]);
        }
        return result;
    }

    public static double calculateScore(ArrayList<Integer> scoreCard, long totalTime){
        long sum = sumOfArray(scoreCard);
        return (double) sum /scoreCard.size();
    }

    public static long sumOfArray(ArrayList<Integer> arr){
        long sum = 0;
        for(int n: arr){
            sum +=n;
        }
        return sum;
    }
}
