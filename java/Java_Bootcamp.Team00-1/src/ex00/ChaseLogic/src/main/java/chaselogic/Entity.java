package chaselogic;

public class Entity {
    private final int value;
    private final String name;

    public static final Entity ENEMY = new Entity(1, "ENEMY");
    public static final Entity PLAYER = new Entity(2, "PLAYER");
    public static final Entity WALLS = new Entity(3, "WALLS");
    public static final Entity POINT = new Entity(4, "POINT");
    public static final Entity EMPTY = new Entity(0, "EMPTY");

    private Entity(int value, String name) {
        this.value = value;
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Entity that = (Entity) obj;
        return value == that.value;
    }
}