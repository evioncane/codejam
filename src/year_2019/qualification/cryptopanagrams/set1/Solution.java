package year_2019.qualification.cryptopanagrams.set1;

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
            final Integer[] message = new Integer[l + 1];
            int[] ciphertext = new int[l];
            for (int j = 0; j < l; j++) {
                ciphertext[j] = in.nextInt();
            }
            int[] factors = getPrimeFactorsForFirstValue(ciphertext[0], n);
            rearangeFactors(ciphertext, factors);
            message[0] = factors[0];
            int previousCharPrime = factors[1];
            for (int j = 1; j < l; j++) {
                message[j] = previousCharPrime;
                previousCharPrime = ciphertext[j] / previousCharPrime;
            }
            message[l] = previousCharPrime;

            List<Integer> sortedPrimes = Arrays.asList(message)
                    .stream()
                    .sorted()
                    .distinct()
                    .collect(Collectors.toList());

            String decryptedMessage = encryptArray(message, sortedPrimes);
            System.out.print("Case #" + i + ": " + decryptedMessage + "\n");
        }
    }

    private static void rearangeFactors(int[] ciphertext, int[] factors) {
        int first = ciphertext[0];
        for (int i = 1; i < ciphertext.length; i++) {
            if (first != ciphertext[i]) {
                if ((ciphertext[i] % factors[0] == 0 && i % 2 != 0)
                        || (ciphertext[i] % factors[1] == 0 && i % 2 == 0)) {
                    int temp = factors[0];
                    factors[0] = factors[1];
                    factors[1] = temp;
                }
                return;
            }
        }
    }

    private static int[] getPrimeFactorsForFirstValue(int first, int maxPrime) {
        final int[] factors = new int[2];
        for (int i = 2; i <= first / 2 && i <= maxPrime; i++) {
            if (first % i == 0) {
                factors[0] = i;
                factors[1] = first / i;
                break;
            }
        }
        return factors;
    }

    private static String encryptArray(Integer[] message, List<Integer> sorted) {

        StringBuilder builder = new StringBuilder();

        Map<Integer, Character> letterMap = populateLetterMap(sorted);

        for (int i = 0; i < message.length; i++) {
            builder.append(letterMap.get(message[i]));
        }

        return builder.toString();

    }

    private static Map<Integer, Character> populateLetterMap(List<Integer> sorted) {
        Map<Integer, Character> letterMap = new HashMap<>();
        for (int i = 0; i < sorted.size(); i++) {
            letterMap.put(sorted.get(i), (char) (i + 'A'));
        }
        return letterMap;
    }
}
