public abstract class Item {
    protected int x, y;

    public Item(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

class Treasure extends Item {
    private int value;

    public Treasure(int x, int y, int value) {
        super(x, y);
        this.value = value;
    }
    public int getValue() {
        return value;
    }
}