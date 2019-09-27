package year_2018.qualification.number_guessing;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {

    private static final String CORRECT = "CORRECT";
    private static final String TOO_SMALL = "TOO_SMALL";
    private static final String TOO_BIG  = "TOO_BIG";
    private static final String WRONG_ANSWER = "WRONG_ANSWER";

    private static int LOWER_BOUND;
    private static int UPPER_BOUND;

    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt();
        for (int i = 1; i <= t; ++i) {
            LOWER_BOUND = in.nextInt();
            UPPER_BOUND = in.nextInt();
            int maxGuessNumber = in.nextInt();
            long maxCaseNumber = log2Round(UPPER_BOUND - LOWER_BOUND);
            boolean upper_check = false;
            if (maxCaseNumber < maxGuessNumber) {
                System.out.print(UPPER_BOUND);
                String answer = in.next();
                upper_check = answer.equals(CORRECT);
                maxGuessNumber--;
            }
            //flush
            if (!upper_check) {
                in.nextLine();
                for (int j = 0; j<maxGuessNumber; j++) {
                    final int guess = guessNumber(LOWER_BOUND, UPPER_BOUND);
                    System.out.print(guess);
                    //flush
                    in.nextLine();
                    String answer = in.next();
                    if (answer.equals(CORRECT)) {
                        break;
                    }
                    changeBounds(answer, guess);
                }
            }

        }
    }

    private static int guessNumber(int min, int max) {
        return (max - min)/2+min;
    }

    private static void changeBounds(final String indication, final int guessedNumber) {
        switch (indication) {
            case TOO_SMALL:
                LOWER_BOUND = guessedNumber;
                break;
            case TOO_BIG:
                UPPER_BOUND = guessedNumber;
                break;
            default:
                break;
        }
    }

    private static long log2Round(int number) {
        double result = Math.log(number)/Math.log(2);
        return Math.round(result);
    }
}
