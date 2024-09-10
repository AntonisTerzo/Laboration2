import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    private Maze maze;
    private Player player;
    private List<Monster> monsters;
    private List<Item> items;
    private Scanner scanner;

    public Game(int width, int height) {
        maze = new Maze(width, height);
        scanner = new Scanner(System.in);
        System.out.println("Welcome to the Maze Game! What is your name?");
        String name = scanner.nextLine();
        player = new Player(name, 1, 1);
        monsters = new ArrayList<>();
        items = new ArrayList<>();
        initializeGame();
    }

    private void initializeGame() {
        // Place monsters in the maze
        monsters.add(new Monster(5, 5, 50, 5));
        monsters.add(new Monster(10, 8, 75, 8));
        monsters.add(new Monster(15, 3, 100, 10));
        monsters.add(new Monster(12, 6, 100, 5));
        monsters.add(new Monster(6, 4, 100, 6));
        // Place treasures in the maze
        items.add(new Treasure(8, 2, 100));
        items.add(new Treasure(12, 4, 200));
        items.add(new Treasure(15, 6, 400));
        items.add(new Treasure(13, 8, 500));
        // Ensure the exit is placed
        maze.setExit(maze.getWidth() - 2, maze.getHeight() - 2);
    }

    public void play() {
        while (!isGameOver()) {
            displayGame();
            System.out.print("Enter move (W/A/S/D): ");
            String move = scanner.nextLine().toUpperCase();
            processMove(move);
            updateGameState();
        }
        System.out.println("Your final score: " + player.getScore());
    }

    private void displayGame() {
        System.out.println("\nMaze:");
        for (int y = 0; y < maze.getHeight(); y++) {
            for (int x = 0; x < maze.getWidth(); x++) {
                if (player.getX() == x && player.getY() == y) {
                    System.out.print("P ");
                } else if (maze.isWall(x, y)) {
                    System.out.print("# ");
                } else if (maze.isExit(x, y)) {
                    System.out.print("E ");
                } else if (isMonsterAt(x, y)) {
                    System.out.print("M ");
                } else if (isItemAt(x, y)) {
                    System.out.print("I ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
        System.out.println("\nPlayer: " + player.getName() + " | Health: " + player.getHealth() + " | Score: "
                + player.getScore());
    }

    private boolean isMonsterAt(int x, int y) {
        for (Monster monster : monsters) {
            if (monster.getX() == x && monster.getY() == y) {
                return true;
            }
        }
        return false;
    }

    private boolean isItemAt(int x, int y) {
        for (Item item : items) {
            if (item.getX() == x && item.getY() == y) {
                return true;
            }
        }
        return false;
    }

    private void processMove(String move) {
        int dx = 0, dy = 0;
        switch (move) {
            case "W":
                dy = -1;
                break;
            case "S":
                dy = 1;
                break;
            case "A":
                dx = -1;
                break;
            case "D":
                dx = 1;
                break;
        }
        player.move(dx, dy, maze);
        String message = player.getLastMoveMessage();
        if (message != null) {
            System.out.println(message);
        }
    }

    private void updateGameState() {
        // Update monster positions
        for (Monster monster : monsters) {
            int dx = (int) (Math.random() * 3) - 1;
            int dy = (int) (Math.random() * 3) - 1;
            monster.move(dx, dy, maze);
        }

        // Check for collisions with monsters
        for (Monster monster : new ArrayList<>(monsters)) {
            if (monster.getX() == player.getX() && monster.getY() == player.getY()) {
                System.out.println("You encountered a monster!");
                monster.interact(player);
                System.out.println("Monster health: " + monster.getHealth());
                System.out.println("Your health: " + player.getHealth());
                if (!monster.isAlive()) {
                    System.out.println("You defeated the monster!");
                    monsters.remove(monster);
                }
                if (player.getHealth() <= 0) {
                    System.out.println("Game Over! You were defeated by a monster.");
                    return; // Exit the method if player is defeated
                }
            }
        }

        // Check for item pickups
        for (Item item : new ArrayList<>(items)) { // Create a copy to avoid ConcurrentModificationException
            if (item.getX() == player.getX() && item.getY() == player.getY()) {
                if (item instanceof Treasure) {
                    player.setScore(player.getScore() + ((Treasure) item).getValue());
                    System.out.println("You found a treasure! Score +" + ((Treasure) item).getValue());
                    items.remove(item);
                }
            }
        }
    }

    private boolean isGameOver() {
        // Check if the player has been defeated
        if (player.getHealth() <= 0) {
            System.out.println("Game Over! You were defeated by a monster.");
            return true;
        }
        // Check if the player has reached the exit
        if (maze.isExit(player.getX(), player.getY())) {
            System.out.println("Congratulations! You've reached the exit!");
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Game game = new Game(20, 10);
        game.play();
        game.scanner.close();
    }
}