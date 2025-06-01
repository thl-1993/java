package game;

public class Player {
    private final int[] coordinates;

    // public Player(Data data) {coordinates = new int[2];}

    public Player() {
        coordinates = new int[2];
    }
    public int[] getCoordinates() {
        return coordinates;
    }
    public void setCoordinates(int x, int y) {
        coordinates[0] = x;
        coordinates[1] = y;
    }
}