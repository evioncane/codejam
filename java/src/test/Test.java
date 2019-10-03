package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int k = in.nextInt();
        in.nextLine();
        int n = in.nextInt();
        System.out.println("K: "+k);
        System.out.println("N: "+n);
    }
}
