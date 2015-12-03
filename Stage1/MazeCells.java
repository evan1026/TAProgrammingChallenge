enum MazeCell {
    OPEN,
    WALL,
    NONE
}

class MazeCells {
    private final MazeCell cells[][];

    MazeCells(int cells_width, int cells_height){
        cells = new MazeCell[cells_width][cells_height];
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
}