package year_2019.qualification.you_can_go_your_own_way;

import java.util.*;
import java.io.*;
public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt();
        for (int i = 1; i <= t; ++i) {
            StringBuilder outputBuilder = new StringBuilder();
            String size = in.next();
            String pattern = in.next();
            for (int j = 0; j < pattern.length(); j++) {
                if (pattern.charAt(j) == 'S') {
                    outputBuilder.append('E');
                }
                else {
                    outputBuilder.append('S');
                }
            }
            System.out.println("Case #" + i + ": " + outputBuilder.toString());
        }
    }
}
