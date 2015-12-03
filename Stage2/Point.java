class Point {
    final int x, y;
    Point(int _x, int _y) {
        x = _x;
        y = _y;
    }
    
    boolean equals(Point p) {
        return p.x == x && p.y ==y;
    }
    
    Point add(Point p) {
        return new Point(p.x + x, p.y + y);
    }
    
    boolean hasPosition() {
        return (x % 2 == 1) && (y % 2 == 1);
    }
    
    Position toPosition() {
        if (hasPosition())
            return new Position((x - 1) / 2, (y - 1) / 2);
        return null;
    }
    static Position toPosition(Point p) {
        return p.toPosition();
    }
    static Position toPosition(int x, int y) {
        return Point.toPosition(new Point(x,y));
    }
    
    static Point fromString(String s) {
        s = s.replace("(", "");
        s = s.replace(")", "");
        String[] nums = s.split(",");
        
        int x, y;
        x = Integer.parseInt(nums[0]);
        y = Integer.parseInt(nums[1]);
        
        return new Point(x,y);
    }
    
    @Override
    public String toString() {
        return String.format("(%d, %d)", x, y);
    }
}