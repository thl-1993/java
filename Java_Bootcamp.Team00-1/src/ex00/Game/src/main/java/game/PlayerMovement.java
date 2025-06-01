package game;

import chaselogic.Entity;
import chaselogic.Map;

import java.io.FileNotFoundException;

public class PlayerMovement {
    private final Data data;
    private final Game gameManager;

    public PlayerMovement(Data data, Game gameManager) {
        this.data = data;
        this.gameManager = gameManager;
    }

    public void moveForward(Map map) throws FileNotFoundException { // Изменено на Map
        int x = data.getPlayerCoordinates()[0]; //__________
        int y = data.getPlayerCoordinates()[1]; //__________

        if (x == 0) {
            if (map.getObjectAt(data.getHeight() - 1, y) != Entity.WALLS) {
                map.setObjectAt(x, y, Entity.EMPTY); // Обновляем старую позицию
                x = data.getHeight() - 1;
            }
        } else if (map.getObjectAt(x - 1, y) != Entity.WALLS) {
            map.setObjectAt(x, y, Entity.EMPTY);
            x -= 1;
        }

        gameManager.checkGameStatus(map, x, y); // Изменен параметр на Map
    }

    public void moveBack(Map map) throws FileNotFoundException {
        int x = data.getPlayerCoordinates()[0];/////___________
        int y = data.getPlayerCoordinates()[1];////____________

        if (x == data.getHeight() - 1) {
            if (map.getObjectAt(0, y) != Entity.WALLS) {
                map.setObjectAt(x, y, Entity.EMPTY);
                x = 0;
            }
        } else if (map.getObjectAt(x + 1, y) != Entity.WALLS) {
            map.setObjectAt(x, y, Entity.EMPTY);
            x += 1;
        }

        gameManager.checkGameStatus(map, x, y);
    }

    public void moveLeft(Map map) throws FileNotFoundException {
        int x = data.getPlayerCoordinates()[0]; //___________
        int y = data.getPlayerCoordinates()[1]; //__________

        if (y == 0) {
            if (map.getObjectAt(x, data.getWeight() - 1) != Entity.WALLS) {
                map.setObjectAt(x, y, Entity.EMPTY);
                y = data.getWeight() - 1;
            }
        } else if (map.getObjectAt(x, y - 1) != Entity.WALLS) {
            map.setObjectAt(x, y, Entity.EMPTY);
            y -= 1;
        }

        gameManager.checkGameStatus(map, x, y);
    }

    public void moveRight(Map map) throws FileNotFoundException {
        int x = data.getPlayerCoordinates()[0]; //___________
        int y = data.getPlayerCoordinates()[1]; //__________

        if (y == data.getWeight() - 1) {
            if (map.getObjectAt(x, 0) != Entity.WALLS) {
                map.setObjectAt(x, y, Entity.EMPTY);
                y = 0;
            }
        } else if (map.getObjectAt(x, y + 1) != Entity.WALLS) {
            map.setObjectAt(x, y, Entity.EMPTY);
            y += 1;
        }
        gameManager.checkGameStatus(map, x, y);
    }
}