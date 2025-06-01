package chaselogic;

import java.util.List;

public class ChaseLogic {
    private final Map map; // Используем новый класс Map
    private final EnemyMovement enemyMovement;
    private boolean isChased = false;
    private boolean step = false;
    private int[] playerCoordinates;

    public ChaseLogic(Map map) { // Принимаем Map как аргумент
        this.map = map;
        enemyMovement = new EnemyMovement(map, this);
    }

    public void invokeEnemies(List<Enemy> enemies, int[] playerCoordinates) { // Убрали height и weight, они не нужны
        this.playerCoordinates = playerCoordinates;
        int playerX = playerCoordinates[0];
        int playerY = playerCoordinates[1];
        for (Enemy enemy : enemies) {
            step = Math.random() < 0.3;
            int enemyX = enemy.getCoordinates()[0];
            int enemyY = enemy.getCoordinates()[1];
            moveValidation(playerX, playerY, enemy, enemyX, enemyY);
        }
    }

    private void moveValidation(int playerX, int playerY, Enemy enemy, int enemyX, int enemyY) {
        if (enemyY + 1 == playerY && enemyX == playerX) {
            enemyMovement.moveRight(enemy);
            isChased = true;
        } else if (enemyY - 1 == playerY && enemyX == playerX) {
            enemyMovement.moveLeft(enemy);
            isChased = true;
        } else if (enemyX + 1 == playerX && enemyY == playerY) {
            enemyMovement.moveForward(enemy);
            isChased = true;
        } else if (enemyX - 1 == playerX && enemyY == playerY) {
            enemyMovement.moveBack(enemy);
            isChased = true;
        } else {
            if (!step) {
                int difY = playerY - enemyY;
                if (enemyY < playerY) {
                    if (map.getWidth() - difY > difY) {
                        enemyMovement.moveRight(enemy);
                    } else {
                        enemyMovement.moveLeft(enemy);
                    }
                } else {
                    if (map.getWidth() - difY > difY) {
                        enemyMovement.moveLeft(enemy);
                    } else {
                        enemyMovement.moveRight(enemy);
                    }
                }
            } else {
                int difX = playerX - enemyX;
                if (enemyX < playerX) {
                    if (map.getHeight() - difX > difX) {
                        enemyMovement.moveBack(enemy);
                    } else {
                        enemyMovement.moveForward(enemy);
                    }
                } else {
                    if (map.getHeight() - difX > difX) {
                        enemyMovement.moveForward(enemy);
                    } else {
                        enemyMovement.moveBack(enemy);
                    }
                }
            }
        }
    }


    public void updateEnemyCoordinates(Map map, int x, int y, Entity object, Enemy enemy) {
        map.setObjectAt(x, y, object);
        enemy.setCoordinates(x, y);
    }

    public void caught(int x, int y) {
        if (x == playerCoordinates[0] && y == playerCoordinates[1]) {
            isChased = true;
        }
    }

    public Map getMap() {
        return map;
    }


    public boolean isChased() {
        return isChased;
    }
}