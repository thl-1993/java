package game;

import chaselogic.ChaseLogic;
import chaselogic.Entity;
import chaselogic.Map;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.Scanner;

public class Game {
    private static Scanner scanner;
    private Data data;
    private PlayerMovement playerMovement;
    private MapPainter mapPainter;
    private ChaseLogic chaseLogic;
    private Map map; // Изменено с int[][] на Map
    private final Path pathProperties;
    private final Args jargs;
    private static boolean isRendered = false;
    private static boolean isDev = false;

    public Game(Path pathProperties, Args jargs) {
        this.pathProperties = pathProperties;
        this.jargs = jargs;
        scanner = new Scanner(System.in);
    }

    public void startGame() throws FileNotFoundException {
        if (!isRendered) {
            mapRendering();
            isRendered = true; // Исправлено на true
        }
        mapPainter.paintMap(map); // Изменено на map.getGrid()

        while (true) {
            System.out.print("Move->");
            String command = scanner.nextLine();

            if (command.equals("9")) {
                System.out.println("Exit");
                System.exit(0);
            }

            switch (command.toLowerCase()) { // Игнорировать регистр ввода
                case "w":
                    playerMovement.moveForward(map); // Использовать методы PlayerMovement
                    break;
                case "s":
                    playerMovement.moveBack(map);
                    break;
                case "a":
                    playerMovement.moveLeft(map);
                    break;
                case "d":
                    playerMovement.moveRight(map);
                    break;
                default:
                    System.out.println("Enter W, A, S, or D.");
                    continue; // Повторный запрос на ввод команды
            }
        }
    }

    private void mapRendering() throws FileNotFoundException {
        data = new Data(ParseProperties.parserHandler(pathProperties), jargs);
        isDev = data.getProfile().equals("dev");
        mapPainter = new MapPainter(data);
        playerMovement = new PlayerMovement(data, this);
        MapGenerator mapGenerator = new MapGenerator(data);
        map = mapGenerator.generateMap(); // Теперь map является объектом Map
        chaseLogic = new ChaseLogic(map);
    }

    public void checkGameStatus(Map map, int x, int y) throws FileNotFoundException {
        // Проверяем, является ли объект в клетке "POINT"
        if (Entity.POINT.equals(map.getObjectAt(x, y))) {
            System.out.println("You won!");
            promptForRetry();
        } else {
            updateMap(x, y, Entity.PLAYER); // Здесь обновляем карту
        }

        // Проверка статуса логики преследования
        if (chaseLogic.isChased()) {
            System.out.println("You was chased!");
            promptForRetry();
        }
    }

    // Метод для вывода сообщения и предложения перезапуска игры
    private void promptForRetry() throws FileNotFoundException {
        while (true) { // Цикл продолжается, пока не будет введен корректный ответ
            System.out.print("Retry? Yes/No -> ");
            String command = scanner.nextLine().trim().toLowerCase();

            if (command.equals("yes") || command.equals("y")) {
                isRendered = false;
                startGame();
                break;
            } else if (command.equals("no") || command.equals("n")) {
                System.exit(0);
            } else {
                System.out.println("Retry? Yes/No ->");
            }
        }
    }

    private void updateMap(int x, int y, Entity object) throws FileNotFoundException {
        // Проверяем, находится ли игрок рядом с врагами
      //  checkPlayerAround(x, y);

        // Устанавливаем нового объекта на карте
        map.setObjectAt(x, y, object);

        // Обновляем координаты игрока в данных
        data.setPlayerCoordinates(x, y);

        // Отображаем обновленную карту
        mapPainter.paintMap(map); // Используем объект Map

        // Если включен режим разработки, обрабатываем специальные действия
        if (isDev) {
            handleDevMode(x, y, object); // Передаем x, y, object
        } else {
            // Вызываем логику преследования врагов
            chaseLogic.invokeEnemies(data.getEnemies(), data.getPlayerCoordinates()); /////
            mapPainter.paintMap(map); // Обновляем отображение карты
        }
    }

    private void handleDevMode(int x, int y, Entity object) throws FileNotFoundException {
        System.out.print("Enter 8 to accept enemy step or 9 to exit -> ");
        String command = scanner.nextLine();

        switch (command) {
            case "8":
                // Вызываем логику движения врагов
                chaseLogic.invokeEnemies(data.getEnemies(), data.getPlayerCoordinates());///////
                // Обновляем отображение карты с полным объектом Map
                mapPainter.paintMap(map);
                break;
            case "9":
                System.exit(0);
                break;
            default:
                // Если введена неверная команда, просто вызываем updateMap снова
                updateMap(x, y, object);
        }
    }

//    private void checkPlayerAround(int x, int y) throws FileNotFoundException {
//        // Используем метод getObjectAt для проверки статуса вокруг игрока
//        if (map.getObjectAt(x, y) == Entity.ENEMY) {
//            System.out.println("You have died!");
//            System.out.print("Do you want to retry the game? Yes/No -> ");
//            String command = scanner.nextLine().toLowerCase();
//            if (command.equals("yes")) {
//                isRendered = false;
//                startGame();
//            } else {
//                System.exit(0);
//            }
//        }
//    }
}