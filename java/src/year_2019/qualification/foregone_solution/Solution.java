package year_2019.qualification.foregone_solution;

import java.util.*;
import java.io.*;
public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt();  // Scanner has functions to read ints, longs, strings, chars, etc.
        for (int i = 1; i <= t; ++i) {
            String n = in.next();
            StringBuilder nString = new StringBuilder(n);
            String secondCheckString = "";
            for (int j = 0; j < nString.length(); j++) {
                if (nString.charAt(j) == '4') {
                    nString.setCharAt(j, '3');
                    secondCheckString += "1";
                    continue;
                }
                secondCheckString += "0";
            }
            System.out.println("Case #" + i + ": " + nString + " " + formatNumberString(secondCheckString));
        }
    }

    private static String formatNumberString(String input) {
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) != '0') {
                return input.substring(i);
            }
        }
        return "";
    }
}
