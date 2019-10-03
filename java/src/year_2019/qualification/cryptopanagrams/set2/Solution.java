package year_2019.qualification.cryptopanagrams.set2;

import java.math.BigInteger;
import java.util.*;
import java.io.*;

public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt();
        for (int i = 1; i <= t; ++i) {
            in.nextLine();
            BigInteger n = in.nextBigInteger();
            int l = in.nextInt();
            final BigInteger[] message = new BigInteger[l + 1];
            BigInteger[] ciphertext = new BigInteger[l];
            for (int j = 0; j < l; j++) {
                ciphertext[j] = in.nextBigInteger();
            }
            String solution = solve(n, ciphertext);
            /*BigInteger[] factors = getPrimeFactorsForFirstValue(ciphertext[0], n);
            rearangeFactors(ciphertext, factors);
            message[0] = factors[0];
            BigInteger previousCharPrime = factors[1];
            for (int j = 1; j < l; j++) {
                message[j] = previousCharPrime;
                previousCharPrime = ciphertext[j].divide(previousCharPrime);
            }
            message[l] = previousCharPrime;

            List<BigInteger> sortedPrimes = Arrays.asList(message)
                    .stream()
                    .sorted()
                    .distinct()
                    .collect(Collectors.toList());

            String decryptedMessage = encryptArray(message, sortedPrimes);*/
            //System.out.print("Case #" + i + ": " + decryptedMessage + "\n");
            System.out.print("Case #" + i + ": "+solution+"\n");
        }
    }

    private static void rearangeFactors(BigInteger[] ciphertext, BigInteger[] factors) {
        BigInteger first = ciphertext[0];
        for (int i = 1; i < ciphertext.length; i++) {
            if (!first.equals(ciphertext[i])) {
                if ((ciphertext[i].remainder(factors[0]).equals(new BigInteger("0")) && i % 2 != 0)
                        || (ciphertext[i].remainder(factors[1]).equals(new BigInteger("0")) && i % 2 == 0)) {
                    BigInteger temp = factors[0];
                    factors[0] = factors[1];
                    factors[1] = temp;
                }
                return;
            }
        }
    }

    private static BigInteger[] getPrimeFactorsForFirstValue(BigInteger first, BigInteger maxPrime) {
        final BigInteger[] factors = new BigInteger[2];
        final BigInteger maxPossiblePrime = first.divide(new BigInteger("2"));
        for (BigInteger i = new BigInteger("2");
             (maxPossiblePrime.compareTo(i) == 1 || maxPossiblePrime.compareTo(i) == 1)
                     && (maxPrime.compareTo(i) == 1 || maxPrime.compareTo(i) == 0);
             i = i.add(BigInteger.ONE)) {
            if (first.remainder(i).equals(new BigInteger("0"))) {
                factors[0] = i;
                factors[1] = first.divide(i);
                break;
            }
        }
        return factors;
    }

    private static String encryptArray(BigInteger[] message, List<BigInteger> sorted) {

        StringBuilder builder = new StringBuilder();

        Map<BigInteger, Character> letterMap = populateLetterMap(sorted);

        for (int i = 0; i < message.length; i++) {
            builder.append(letterMap.get(message[i]));
        }

        return builder.toString();

    }

    private static Map<BigInteger, Character> populateLetterMap(List<BigInteger> sorted) {
        Map<BigInteger, Character> letterMap = new HashMap<>();
        for (int i = 0; i < sorted.size(); i++) {
            letterMap.put(sorted.get(i), (char) (i + 'A'));
        }
        return letterMap;
    }

    private static String solve(BigInteger maxPrime, BigInteger[] seq) {
        // Find prime factors
        BigInteger[] primeSeq = new BigInteger[seq.length + 1];
        int firstDiff = -1;
        for (int i = 1; i < seq.length; i++) {
            if (!seq[i].equals(seq[i - 1])) {
                firstDiff = i;
                primeSeq[i] = seq[i].gcd(seq[i - 1]);
                break;
            }
        }
        assert firstDiff > 0;
        for (int i = firstDiff; i < seq.length; i++) {
            primeSeq[i + 1] = seq[i].divide(primeSeq[i]);
        }
        for (int i = firstDiff - 1; i >= 0; i--) {
            primeSeq[i] = seq[i].divide(primeSeq[i + 1]);
        }
        // Build dictionary
        Set<BigInteger> primes = new HashSet<>();
        for (BigInteger prime : primeSeq) {
            primes.add(prime);
        }
        //assert primes.size() == 26; // assertion removed to test dictionaries smaller than 26 letters
        BigInteger[] sortedPrimes = new BigInteger[primes.size()];
        primes.toArray(sortedPrimes);
        Arrays.sort(sortedPrimes);
        Map<BigInteger, Character> dictionary = new HashMap<>();
        for (int i = 0; i < sortedPrimes.length; i++) {
            dictionary.put(sortedPrimes[i], (char) ('A' + i));
        }
        // Decypher sequence
        StringBuilder result = new StringBuilder(primeSeq.length);
        for (BigInteger prime : primeSeq) {
            result.append(dictionary.get(prime));
        }
        return result.toString();
    }
}
