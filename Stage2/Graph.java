
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Graph {
    Set<Node> V; //vertexes. Each vertex knows what it's connected to
    String[] renderedMaze;
    
    public Graph(String[] _renderedMaze) {
        V = new HashSet<Node>();
        renderedMaze = _renderedMaze;
    }
    
    static Graph fromMaze(Maze m) {
        Graph newGraph = new Graph(m.renderStrings());
                
        for (int i = 0; i < m.getWidth(); ++i) {
            for (int j = 0; j < m.getHeight(); ++j) {
                Point p = new Point(i, j);
                if (p.hasPosition()) {
                    Position pos = p.toPosition();
                    Node n = new Node(pos);
                    newGraph.V.add(n);
                }
            }
        }
        
        for (Node n : newGraph.V) {
            Point[] directions = {new Point(-1,  0),
                                  new Point( 1,  0),
                                  new Point( 0, -1),
                                  new Point( 0,  1)};

            n.getEdges().add(n);
            
            for (Point d : directions) {
                Position currPos = n.pos;
                while (m.get(currPos.toPoint().add(d)) == MazeCell.OPEN) {
                    Point p = new Point(currPos.x, currPos.y).add(d);
                    currPos = new Position(p.x, p.y);
                    n.getEdges().add(newGraph.getNode(currPos));
                }
            }
        }
        
        return newGraph;
    }
    
    Node getNode(Position p) {
        Iterator<Node> i = V.iterator();
        while(i.hasNext()) {
            Node n = i.next();
            if (n.pos.equals(p)) return n;
        }

        return null;
    }
    
    @Override
    public String toString() {
        String output = "{";
        
        Iterator<Node> i = V.iterator();
        while(i.hasNext()) {
            Node n = i.next();
            output += "\n    " + n;
        }
        
        output += "\n}";
        
        return output;
    }
    
    public boolean areConnected(Node p1, Node p2) {
        return p1.getEdges().contains(p2);
    }
}

class Node {
    
    enum Type {
        UNKNOWN,
        EMPTY,
        GOLD
    }
    
    final Position pos;
    private Set<Node> edges;
    Type t;

    Node(Position p, Set<Node> e, Type _t) {
        pos = p;
        setEdges(e);
        t = _t;
    }
    Node(Position p) {
        this(p, new HashSet<Node>(), Type.UNKNOWN);
    }

    Set<Node> getEdges() {
        return edges;
    }
    final void setEdges(Set<Node> edges) {
        this.edges = edges;
    }
    
    @Override
    public String toString() {
        String output = "Node: { Position: " + pos + ", " + "Edges: { ";
            
        Iterator<Node> j = getEdges().iterator();
        while (j.hasNext()) {
            Node m = j.next();
            if (m != null) output += m.pos + " ";
        }

        output += "} Type: " + t + " }";

        return output;
    }
    
    @Override
    public boolean equals (Object n) {
        if (!(n instanceof Node)) return false;
        Node m = (Node) n;
        return m.pos.equals(pos);
    }

}
