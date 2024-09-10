public class Monster implements Movable {
    private int x, y;
    private int health;
    private int strength;

    public Monster(int x, int y, int health, int strength) {
        this.x = x;
        this.y = y;
        this.health = health;
        this.strength = strength;
    }

  
    // Implement Movable methods
    @Override
    public void move(int dx, int dy, Maze maze) {
        int newX = x + dx;
        int newY = y + dy;
        if (maze.isValidMove(newX, newY)) {
            x = newX;
            y = newY;
        }
    }

    // Implement Obstacle methods
    // Monsters block player movement, but this is not part of an interface method
    public boolean isBlocking() {
        return true;
    }

    // Combat logic when a player interacts with the monster
    public void interact(Player player) {
        System.out.println("Battle started!");
        // Player attacks monster
        int playerDamage = Math.max(0, player.getStrength() - this.strength);
        this.health -= playerDamage;
        System.out.println("You dealt " + playerDamage + " damage to the monster.");

        // Monster attacks player if still alive
        if (this.health > 0) {
            int monsterDamage = Math.max(5, this.strength - player.getStrength()); 
            player.setHealth(player.getHealth() - monsterDamage);
            System.out.println("The monster dealt " + monsterDamage + " damage to you.");
        }
    }

    // Monster-specific methods
    public boolean isAlive() {
        return health > 0;
    }

    // Getters and setters
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
        this.health = health;
    }

    public int getStrength() {
        return strength;
    }
}
