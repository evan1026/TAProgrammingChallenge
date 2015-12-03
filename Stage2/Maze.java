import java.util.ArrayList;

enum MazeCell {
    OPEN,
    WALL,
    NONE
}

class Maze {
    private final MazeCell cells[][];

    Maze(int width, int height){
        cells = new MazeCell[width][height];
    }

    MazeCell get(int x, int y) {
        return cells[x][y];
    }
    MazeCell get(Point p) {
        return get(p.x, p.y);
    }

    void set(int x, int y, MazeCell val) {
        cells[x][y] = val;
    }
    void set(Point p, MazeCell val) {
        set(p.x, p.y, val);
    }

    int getWidth() {
        return cells.length;
    }

    int getHeight() {
        return cells[0].length;
    }

    MazeCell checkLeftNeighbor(int x, int y){
        if (x <= 0) return MazeCell.NONE;
        else        return cells[x - 1][y];
    }
    MazeCell checkLeftNeighbor(Point p){
        return checkLeftNeighbor(p.x, p.y);
    }

    MazeCell checkRightNeighbor(int x, int y){
        if (x >= getWidth() - 1) return MazeCell.NONE;
        else                     return cells[x + 1][y];
    }
    MazeCell checkRightNeighbor(Point p){
        return checkRightNeighbor(p.x, p.y);
    }

    MazeCell checkUpperNeighbor(int x, int y){
        if (y <= 0) return MazeCell.NONE;
        else        return cells[x][y - 1];
    }
    MazeCell checkUpperNeighbor(Point p){
        return checkUpperNeighbor(p.x, p.y);
    }

    MazeCell checkLowerNeighbor(int x, int y){
        if (y >= getHeight() - 1) return MazeCell.NONE;
        else                      return cells[x][y + 1];
    }
    MazeCell checkLowerNeighbor(Point p){
        return checkLowerNeighbor(p.x, p.y);
    }

    boolean isUnconnected(Point p) {
        return isUnconnected(p.x, p.y);
    }
    boolean isUnconnected(int x, int y) {
        return checkRightNeighbor(x, y) == MazeCell.WALL &&
               checkLeftNeighbor(x, y) == MazeCell.WALL &&
               checkUpperNeighbor(x, y) == MazeCell.WALL &&
               checkLowerNeighbor(x, y) == MazeCell.WALL;
    }
    
    /**
     * Creates the string that represents the layout of the maze
     * @return 
     */
    public String render() {
        String output = "";
        
        for (int y = 0; y < getHeight(); ++y) {
            for (int x = 0; x < getWidth(); ++x) {
                if (get(x, y) == MazeCell.OPEN) output += " ";
                else {
                    if (checkLeftNeighbor(x, y) == MazeCell.WALL || checkRightNeighbor(x, y) == MazeCell.WALL){
                        if (checkUpperNeighbor(x, y) == MazeCell.WALL || checkLowerNeighbor(x, y) == MazeCell.WALL) {
                            output += "+";
                        } else {
                            output += "-";
                        }
                    } else if (checkUpperNeighbor(x, y) == MazeCell.WALL || checkLowerNeighbor(x, y) == MazeCell.WALL) {
                        output += "|";
                    } else {
                        output += " ";
                    }
                }
            }
            output += "\n";
        }
        
        return output;
    }
    
    public String[] renderStrings() {
        return render().split("\n");
    }
    
    static Maze fromStrings(ArrayList<String> strings) {
        Maze newMaze = new Maze(strings.get(0).length(), strings.size());
        
        for (int y = 0; y < strings.size(); ++y) {
            for (int x = 0; x < strings.get(y).length(); ++x) {
                if (strings.get(y).charAt(x) == '+'
                        || strings.get(y).charAt(x) == '-'
                        || strings.get(y).charAt(x) == '|')
                    newMaze.set(x, y, MazeCell.WALL);
                else
                    newMaze.set(x, y, MazeCell.OPEN);
            }
        }
        
        return newMaze;
    }
}