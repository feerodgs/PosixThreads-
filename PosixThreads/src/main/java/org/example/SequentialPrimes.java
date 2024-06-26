package org.example;

public class SequentialPrimes {
    public static void main(String[] args) {
        int limit = 10_000_000;
        long startTime = System.currentTimeMillis();
        int primeCount = countPrimes(limit);
        long endTime = System.currentTimeMillis();
        System.out.println("Número Primo: " + primeCount);
        System.out.println("Tempo de Execução - (Sequencial):  " + (endTime - startTime) + " ms");
    }

    private static int countPrimes(int limit) {
        int count = 0;
        for (int i = 2; i <= limit; i++) {
            if (isPrime(i)) {
                count++;
            }
        }
        return count;
    }

    private static boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}

