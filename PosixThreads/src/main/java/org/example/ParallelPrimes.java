package org.example;

import java.util.concurrent.atomic.AtomicInteger;

public class ParallelPrimes {
    public static void main(String[] args) {
        int limit = 10_000_000;
        int numThreads = 4; // número de threads para usar

        long startTime = System.currentTimeMillis();
        AtomicInteger primeCount = new AtomicInteger(0);

        Thread[] threads = new Thread[numThreads];
        int chunkSize = limit / numThreads; // tamanho do bloco

        for (int i = 0; i < numThreads; i++) {
            final int start = i * chunkSize + 1;
            final int end = (i == numThreads - 1) ? limit : start + chunkSize;
            threads[i] = new Thread(() -> {
                int localCount = 0;
                for (int j = start; j <= end; j++) {
                    if (isPrime(j)) {
                        localCount++;
                    }
                }
                primeCount.addAndGet(localCount);
            });
            threads[i].start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Número Primo: " + primeCount.get());
        System.out.println("Tempo de Execução - (Paralelo):  " + (endTime - startTime) + " ms");
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