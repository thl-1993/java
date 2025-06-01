package chaselogic;

public class Map {
    private final Entity[][] grid; // Хранит объекты Entity

    public Map(int height, int width) {
        grid = new Entity[height][width];
        initializeGrid();
    }

    private void initializeGrid() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = Entity.EMPTY; // Инициализируем все пустыми объектами
            }
        }
    }

    public Entity getObjectAt(int x, int y) {
        return grid[x][y]; // Возвращаем объект Entity
    }

    public void setObjectAt(int x, int y, Entity object) {
        grid[x][y] = object; // Устанавливаем объект Entity в указанную ячейку
    }


    public int getHeight() {
        return grid.length; // Возвращаем высоту карты
    }

    public int getWidth() {
        return grid[0].length; // Возвращаем ширину карты
    }

    public Entity[][] getGrid() { // Новый метод для получения grid
        return grid; // Возвращаем двумерный массив объектов Entity
    }

//    public void clearObjectAt(int x, int y) {
//        grid[x][y] = Entity.EMPTY; // Очищаем ячейку, устанавливая ее в EMPTY
//    }
}