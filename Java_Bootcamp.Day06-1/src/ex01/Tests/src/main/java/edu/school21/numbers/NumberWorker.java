package edu.school21.numbers;

public class NumberWorker
{
    public boolean isPrime(int number) {
        boolean isPrime = false;
        if (number <= 1) {
            throw new IllegalNumberException("Error: number must be greater than 1");
        } else {
            int result = prime(number);
            if (result * result > number) {
                isPrime = true;
            }
        }
        return isPrime;
    }
    public static int prime(int num) {
        int del = 2;
        while ((del * del <= num) && (num % del) != 0) {
            del += 1;
        }
        return del;
    }

    public int digitsSum(int number){
        int res = 0;
        while (number > 0) {
            res += number % 10;
            number /= 10;
        }
        return res;
    }
}