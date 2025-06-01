package chaselogic;

public class EnemyMovement {
    private final Map map; // Используем новый класс Map
    private final ChaseLogic chaseLogic;

    public EnemyMovement(Map map, ChaseLogic chaseLogic) {
        this.map = map;
        this.chaseLogic = chaseLogic;
    }

    public void moveEnemy(Enemy enemy, int newX, int newY) {
        int x = enemy.getCoordinates()[0];
        int y = enemy.getCoordinates()[1];

        if (isValidMove(newX, newY)) {
            chaseLogic.caught(x, y);
            map.setObjectAt(x, y, Entity.EMPTY);  // Освобождаем старую позицию
            chaseLogic.updateEnemyCoordinates(map, newX, newY, Entity.ENEMY, enemy);
        } else {
            chaseLogic.caught(x, y);
            chaseLogic.updateEnemyCoordinates(map, x, y, Entity.ENEMY, enemy);
        }
    }

    public void moveForward(Enemy enemy) {
        int x = enemy.getCoordinates()[0];
        moveEnemy(enemy, x - 1, enemy.getCoordinates()[1]);
    }

    public void moveBack(Enemy enemy) {
        int x = enemy.getCoordinates()[0];
        moveEnemy(enemy, x + 1, enemy.getCoordinates()[1]);
    }

    public void moveLeft(Enemy enemy) {
        int y = enemy.getCoordinates()[1];
        moveEnemy(enemy, enemy.getCoordinates()[0], y - 1);
    }

    public void moveRight(Enemy enemy) {
        int y = enemy.getCoordinates()[1];
        moveEnemy(enemy, enemy.getCoordinates()[0], y + 1);
    }

    private boolean isValidMove(int x, int y) {
        // Проверка на допустимость хода с учетом класса Map
        return x >= 0 && x < map.getHeight() && y >= 0 && y < map.getWidth() &&
                map.getObjectAt(x, y) != Entity.WALLS &&
                map.getObjectAt(x, y) != Entity.POINT &&
                map.getObjectAt(x, y) != Entity.ENEMY;
    }
}