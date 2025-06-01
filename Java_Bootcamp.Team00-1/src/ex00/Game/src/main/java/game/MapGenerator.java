package game;

import chaselogic.Enemy;
import chaselogic.Entity;
import chaselogic.Map;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class MapGenerator {
    private static Data data;
    private static int weight;
    private static int height;

    public MapGenerator(Data data) {
        MapGenerator.data = data;
        weight = data.getWeight();
        height = data.getHeight();
    }

    public Map generateMap() { // Теперь возвращает объект Map
        int i = 0;
        AtomicInteger randomX = new AtomicInteger(0);
        AtomicInteger randomY = new AtomicInteger(0);
        Map map = new Map(height, weight); // Используем класс Map вместо двумерного массива
        ArrayList<Integer> coordinateX = new ArrayList<>(Collections.nCopies(height, 0));
        ArrayList<Integer> coordinateY = new ArrayList<>(Collections.nCopies(weight, 0));

        // Проверка на корректность входных данных
        if ((data.getEnemiesCount() + data.getWallsCount()) > (height * weight)) {
            throw new IllegalParametersException("Input data is incorrect");
        }

        // Генерация врагов
        for (i = 0; i < data.getEnemiesCount(); i++) {
            generateCoordinates(randomX, randomY);
            checkCoordinates(randomX, randomY, coordinateX, coordinateY, i);
            addEnemy(data, randomX, randomY);
            map.setObjectAt(randomY.get(), randomX.get(), Entity.ENEMY); // Устанавливаем врага на карте
        }

        // Генерация стен
        int wallsCount = data.getWallsCount() + i;
        for (; i < wallsCount; i++) {
            generateCoordinates(randomX, randomY);
            checkCoordinates(randomX, randomY, coordinateX, coordinateY, i);
            map.setObjectAt(randomY.get(), randomX.get(), Entity.WALLS); // Устанавливаем стены на карте
        }

        // Установка игрока
        generateCoordinates(randomX, randomY);
        checkCoordinates(randomX, randomY, coordinateX, coordinateY, i);
        map.setObjectAt(randomY.get(), randomX.get(), Entity.PLAYER); // Устанавливаем игрока на карте
        data.setPlayerCoordinates(randomY.get(), randomX.get());

        // Установка точки завершения (цели)
        generateCoordinates(randomX, randomY);
        checkCoordinates(randomX, randomY, coordinateX, coordinateY, i);
        map.setObjectAt(randomY.get(), randomX.get(), Entity.POINT); // Устанавливаем цель на карте

        return map;
    }

    // Добавление врага в список врагов
    private static void addEnemy(Data data, AtomicInteger randomX, AtomicInteger randomY) {
     //   Enemy enemy = new Enemy(data.getPlayerCoordinates()); //////________________
        Enemy enemy = new Enemy(); //////________________
        enemy.setCoordinates(randomY.get(), randomX.get());
        data.setEnemies(enemy);
    }

    // Проверка координат на уникальность
    private static void checkCoordinates(AtomicInteger randomX, AtomicInteger randomY, List<Integer> coordinateX, List<Integer> coordinateY, int index) {
        while (coordinateX.get(index) == randomX.get() && coordinateY.get(index) == randomY.get()) {
            generateCoordinates(randomX, randomY);
        }
        addToCoordinates(randomX, randomY, coordinateX, coordinateY, index);
    }

    // Добавление сгенерированных координат в списки
    private static void addToCoordinates(AtomicInteger randomX, AtomicInteger randomY, List<Integer> coordinateX, List<Integer> coordinateY, int index) {
        coordinateX.add(index, randomX.get());
        coordinateY.add(index, randomY.get());
    }

    // Генерация случайных координат
    private static void generateCoordinates(AtomicInteger randomX, AtomicInteger randomY) {
        randomX.set((int) (Math.random() * weight));
        randomY.set((int) (Math.random() * height));
    }
}