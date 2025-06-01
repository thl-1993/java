package edu.school21.numbers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.api.BeforeEach;

public class NumberWorkerTest {
    private NumberWorker numberWorker;

    @BeforeEach
    void setUp() {
        numberWorker = new NumberWorker();
    }

    // 1. Проверка метода isPrime для простых чисел
    @ParameterizedTest
    @ValueSource(ints = {2, 3, 5, 7, 11})
    void isPrimeForPrimes(int number) {
        Assertions.assertTrue(numberWorker.isPrime(number),
                "Число " + number + " должно быть простым.");
    }

    // 2. Проверка метода isPrime для составных чисел
    @ParameterizedTest
    @ValueSource(ints = {4, 6, 8, 9, 10})
    void isPrimeForNotPrimes(int number) {
        Assertions.assertFalse(numberWorker.isPrime(number),
                "Число " + number + " не должно быть простым.");
    }

    // 3. Проверка метода isPrime для некорректных чисел (меньше или равно 1)
    @ParameterizedTest
    @ValueSource(ints = {0, 1, -1, -10, -100})
    void isPrimeForIncorrectNumbers(int number) {
        Assertions.assertThrows(IllegalNumberException.class, () -> numberWorker.isPrime(number),
                "Для числа " + number + " должно выбрасываться исключение IllegalNumberException.");
    }

    // 4. Проверка метода digitsSum с использованием файла data.csv
    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv", numLinesToSkip = 1)
    void digitsSumTest(int number, int expectedSum) {
        Assertions.assertEquals(expectedSum, numberWorker.digitsSum(number),
                "Сумма цифр числа " + number + " должна быть " + expectedSum);
    }
}

