public class Player implements Movable {
    private  String name;
    private int x, y;
    private int health;
    private int strength;
    private int score;
    private String lastMoveMessage;

    public Player(String name, int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.health = 100;
        this.strength = 10;
    }

    @Override
    public void move(int dx, int dy, Maze maze) {
        int newX = x + dx;
        int newY = y + dy;
        if (maze.isValidMove(newX, newY)) {
            x = newX;
            y = newY;
            lastMoveMessage = null;
        } else {
            lastMoveMessage = "There is a wall, you can't go this way.";
        }
    }

    public String getLastMoveMessage() {
        return lastMoveMessage;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        if (health < 0) {
            this.health = 0;
        } else if (health > 100) {
            this.health = 100;
        } else {
            this.health = health;
        }
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        if (strength < 0) {
            this.strength = 0;
        } else if (strength > 100) {
            this.strength = Math.min(strength, 100);
        } else {
            this.strength = strength;
        }
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        if (score < 0) {
            this.score = 0;
        } else {
            this.score = score;
        }
    }
}
