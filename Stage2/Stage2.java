
import java.util.ArrayList;
import java.util.Scanner;

public class Stage2 {

    public static void main(String[] args) {
        try {
            doProblem(args);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(42);
        }
    }
    
    private static void doProblem(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        ArrayList<String> lines = new ArrayList<String>();
        while (lines.isEmpty() || !lines.get(lines.size() - 1).startsWith("(")) {
            lines.add(scan.nextLine());
        }
        
        Position startingPoint = Position.fromString(lines.get(lines.size() - 1));
        lines.remove(lines.size() - 1);
        
        //What's left in lines is the maze
        Maze maze = Maze.fromStrings(lines);
        
        //System.out.println(maze.render());
        
        Graph g = Graph.fromMaze(maze);
        //System.out.println(g);
        
        GraphTraverser gt = new GraphTraverser(g, startingPoint);
        
        String line = "";
        while (!line.contains("DONE")) {
            Position p = gt.getNextMove();
            System.out.println(p);
            gt.doMove(p);
            line = scan.nextLine();
        }
    }
}
