
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class GraphTraverser {
    private final Graph graph;
    private Position pos;
    private ArrayList<Node> uncollectedGold = new ArrayList<Node>();
    private ArrayList<Node> unknownNodes = new ArrayList<Node>();
    private LinkedList<Position> currentPath = new LinkedList<Position>();
    
    
    public GraphTraverser(Graph g, Position startingPoint) {
        graph = g;
        pos = startingPoint;
        
        if (graph.getNode(pos) == null) {
            throw new IllegalArgumentException("Starting position not in map.");
        }
        
        collectUnknownNodes();
        
        //for (Node n : unknownNodes) {
        //    System.out.println(n);
        //}
    }
    
    private void collectUnknownNodes() {
        ArrayList<Node> nwun = new ArrayList<Node>(); //nwun = nodes with unvisited neighbors
        nwun.add(graph.getNode(pos));
        
        while (!nwun.isEmpty()) {
            ArrayList<Node> nextNwun = new ArrayList<Node>();
            for (Node n : nwun) {
                unknownNodes.add(n);
                
                for (Node m : n.getEdges()) {
                    if (!unknownNodes.contains(m) && !nextNwun.contains(m)) {
                        nextNwun.add(m);
                    }
                }
            }
            
            nwun = nextNwun;
        }
    }
    
    public Position getNextMove() {
        Node currNode = graph.getNode(pos);
        if (currNode.t == Node.Type.UNKNOWN)
            return pos;
        
        if (!currentPath.isEmpty()) {
            //System.out.println("On the way to " + currentPath.get(currentPath.size() - 1));
            Position p = currentPath.get(0);
            currentPath.remove(0);
            return p;
        }
        
        for (Node n : unknownNodes) {
            if (n.getEdges().contains(currNode)) return n.pos;
            
            currentPath = getDirections(currNode, n);
            
            return getNextMove(); //Just so I don't have to copy/paste what's above
        }
        
        return null;
    }
    
    public void doMove(Position p) {
        pos = p;
        /*for (Node n : graph.getNode(pos).getEdges()) {
            n.t = Node.Type.EMPTY;
            Point stringPos = n.pos.toPoint();
            graph.renderedMaze[stringPos.y] = graph.renderedMaze[stringPos.y].substring(0, stringPos.x) + "*" + graph.renderedMaze[stringPos.y].substring(stringPos.x + 1);
            unknownNodes.remove(graph.getNode(n.pos));
        }*/
        graph.getNode(pos).t = Node.Type.EMPTY;
        unknownNodes.remove(graph.getNode(pos));
        
        Point stringPos = pos.toPoint();
        graph.renderedMaze[stringPos.y] = graph.renderedMaze[stringPos.y].substring(0, stringPos.x) + "#" + graph.renderedMaze[stringPos.y].substring(stringPos.x + 1);
        
        /*for (String s : graph.renderedMaze) {
            System.out.println(s);
        }*/
    }
    
    public boolean hasNextMove() {
        return unknownNodes.size() != 0;
    }
    
    

    public LinkedList<Position> getDirections(Node start, Node finish){
        Map<Node, Boolean> vis = new HashMap<Node, Boolean>();
        Map<Node, Node> prev = new HashMap<Node, Node>();
        LinkedList<Position> directions = new LinkedList<>();
        LinkedList<Node> q = new LinkedList<>();
        Node current = start;
        q.add(current);
        vis.put(current, true);
        while(!q.isEmpty()){
            current = q.remove();
            if (current.equals(finish)){
                break;
            }else{
                for(Node node : current.getEdges()){
                    if(!vis.containsKey(node)){
                        q.add(node);
                        vis.put(node, true);
                        prev.put(node, current);
                    }
                }
            }
        }
        if (!current.equals(finish)){
            System.out.println("can't reach destination");
        }
        for(Node node = finish; node != null; node = prev.get(node)) {
            directions.addFirst(node.pos);
        }
        directions.removeFirst();
        return directions;
    }
}
