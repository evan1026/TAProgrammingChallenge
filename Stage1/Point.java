class Point {
    int x, y;
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
}