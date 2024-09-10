public class Maze {
    private char[][] grid;
    private int width, height;
    private int exitX;
    private int exitY;

    public Maze(int width, int height) {
        this.width = width;
        this.height = height;
        this.grid = new char[height][width];
        initializeMaze();
    }

    public void setExit(int x, int y) {
        if (isValidMove(x, y)) {
            this.exitX = x;
            this.exitY = y;
            grid[y][x] = 'E'; // Mark the exit with 'E'
        } else {
            throw new IllegalArgumentException("Invalid exit position");
        }
    }


    private void initializeMaze() {
        // Initialize the maze with walls and paths
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (i == 0 || i == height - 1 || j == 0 || j == width - 1) {
                    grid[i][j] = '#'; // Wall
                } else {
                    grid[i][j] = '.'; // Path
                }
            }
        }
    }

    public boolean isValidMove(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height && grid[y][x] != '#';
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isExit(int x, int y) {
        return x == exitX && y == exitY;
    }

    public boolean isWall(int x, int y) {
        return grid[y][x] == '#';
    }

}
