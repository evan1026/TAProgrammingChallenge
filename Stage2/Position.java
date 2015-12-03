public class Position {
    

    final int x, y;

    public Position(int _x, int _y) {
        x = _x;
        y = _y;
    }
    
    Point toPoint() {
        return new Point(2 * x + 1, 2 * y + 1);
    }
    static Point toPoint(Position p) {
        return toPoint(p.x, p.y);
    }
    static Point toPoint(int x, int y) {
        return toPoint(new Position(x, y));
    }
    
    @Override
    public boolean equals(Object p) {
        if (!(p instanceof Position)) return false;
        Position q = (Position) p;
        return q.x == x && q.y == y;
    }
    
    @Override
    public String toString() {
        return String.format("(%d, %d)", x, y);
    }
    
    static Position fromString(String s) {
        s = s.replace("(", "");
        s = s.replace(")", "");
        String[] nums = s.split(",");
        
        int x, y;
        x = Integer.parseInt(nums[0]);
        y = Integer.parseInt(nums[1]);
        
        return new Position(x,y);
    }
    
}
