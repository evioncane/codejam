package year_2019.qualification.cryptopanagrams;

import java.util.*;
import java.io.*;
import java.util.stream.Collectors;

public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt();
        for (int i = 1; i <= t; ++i) {
            in.nextLine();
            int n = in.nextInt();
            int l = in.nextInt();
            in.nextLine();
            final Double[] message = new Double[l+1];
            double first = in.nextDouble();
            double[] factors = getPrimeFactorsForFirstValue(first, n);
            double second = in.nextDouble();
            double previousCharPrime;
            if (second % factors[0] == 0) {
                previousCharPrime = second/factors[0];
                message[0] = factors[1];
                message[1] = factors[0];
            }
            else {
                previousCharPrime = second/factors[1];
                message[0] = factors[0];
                message[1] = factors[1];
            }
            for (int j = 2; j < l; j++) {
                message[j] = previousCharPrime;
                boolean check = in.hasNext();
                double nexDouble = in.nextDouble();
                previousCharPrime = nexDouble/previousCharPrime;
            }
            message[l] = previousCharPrime;

            List<Double> sortedPrimes = Arrays.asList(message)
                    .stream()
                    .sorted()
                    .distinct()
                    .collect(Collectors.toList());

            String decryptedMessage = encryptArray(message, sortedPrimes);
            System.out.print("\nCase #" + i + ": " + decryptedMessage);
        }
    }

    private static double[] getPrimeFactorsForFirstValue(double first, double maxPrime){
        final double[] factors = new double[2];
        for (int i = 2; i <= first/2 || i <= maxPrime; i++) {
            if (first % i == 0) {
                factors[0] = i;
                factors[1] = first/i;
                break;
            }
        }
        return factors;
    }

    private static String encryptArray(Double[] message, List<Double> sorted) {

        StringBuilder builder = new StringBuilder();

        for(int i = 0; i < message.length; i++){
            builder.append(getCharFromPrime(message[i], sorted));
        }

        return builder.toString();

    }

    private static String getCharFromPrime(double prime, List<Double> sorted) {
        for (int i = 0; i < sorted.size(); i++) {
            if (prime == sorted.get(i)){
                char letter = (char) (i+65);
                return ""+letter;
            }
        }
        return "";
    }
}
