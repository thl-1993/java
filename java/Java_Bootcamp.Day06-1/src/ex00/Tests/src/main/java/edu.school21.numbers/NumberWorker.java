package edu.school21.numbers;

public class NumberWorker {
    public boolean isPrime(int number) {
        boolean isPrime = false;
        if (number <= 1) {
            throw new IllegalNumberException("ERROR: Number must be greater than 1");
        } else {
            int result = prime(number);
            if (result * result > number) {
                isPrime = true;
            }
        }
        return isPrime;
    }

    public static int prime(int number) {
        int value = 2;
        while ((value * value <= number) && (number % value) != 0) {
            value += 1;
        }
        return value;
    }

    public int digitsSum(int number) {
        int result = 0;
        while (number > 0) {
            result += number % 10;
            number /= 10;
        }
        return result;
    }
}