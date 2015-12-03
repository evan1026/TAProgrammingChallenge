public class Stage1 {
    public static void main(String[] args) {
        try {
            doProblem(args);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(42);
        }
    }
    
    private static void doProblem(String[] args) {
        if (args.length > 3 || args.length < 2) {
            throw new IllegalArgumentException("Invaild number of parameters passed: " + args.length);
        }
        
        Maze maze = new Maze(args);
        System.out.println(maze.render());
    }
}
