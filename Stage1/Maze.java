import java.util.ArrayList;
import java.util.Random;

public class Maze {
    
    private enum PathlengthType {
        LESS_THAN,
        GREATER_THAN,
        EQUAL,
        NONE;
    }

    private final int width,
                      height,
                      pathlength;
    private final PathlengthType plType;
    private final MazeCells cells;
    
    public Maze(String[] args) {
        width = Integer.parseInt(args[0]);
        height = Integer.parseInt(args[1]);
        
        //Parse path length, even though it is unimplemented
        if (args.length == 3) {
            String pathlengthString = args[2];
            char firstChar = pathlengthString.charAt(0);
            
            if (firstChar == '>' || firstChar == '<') {
                plType = (firstChar == '>') ? PathlengthType.GREATER_THAN : PathlengthType.LESS_THAN;
                pathlength = Integer.parseInt(pathlengthString.substring(1));
            } else {
                plType = PathlengthType.EQUAL;
                pathlength = Integer.parseInt(pathlengthString);
            }
        } else {
            plType = PathlengthType.NONE;
            pathlength = -1;
        }
        
        //Have cells in between movable cells
        //This increases ease of storage
        cells = new MazeCells(2 * width + 1, 2 * height + 1);
        
        //Open up default movable spaces
        //Close rest with walls
        for (int i = 0; i < cells.getWidth(); ++i) {
            for (int j = 0; j < cells.getHeight(); ++j) {
                if (i % 2 == 1 && j % 2 == 1)
                    cells.set(i, j, MazeCell.OPEN);
                else
                    cells.set(i, j, MazeCell.WALL);
            }
        }
        
        //Generate maze
        ArrayList<Point> points = new ArrayList<Point>();
        points.add(new Point(1,1));
        makeMaze(points);
    }
    
    /**
     * For debug purposes
     * @return 
     */
    @Override
    public String toString() {
        return String.format("Maze:{ width=%d height=%d pathlength=%d plType=%s }", width, height, pathlength, plType);
    }
    
    /**
     * Creates the string that represents the layout of the maze
     * @return 
     */
    public String render() {
        String output = "";
        
        for (int y = 0; y < cells.getHeight(); ++y) {
            for (int x = 0; x < cells.getWidth(); ++x) {
                if (cells.get(x, y) == MazeCell.OPEN) output += " ";
                else {
                    if (cells.checkLeftNeighbor(x, y) == MazeCell.WALL || cells.checkRightNeighbor(x, y) == MazeCell.WALL){
                        if (cells.checkUpperNeighbor(x, y) == MazeCell.WALL || cells.checkLowerNeighbor(x, y) == MazeCell.WALL) {
                            output += "+";
                        } else {
                            output += "-";
                        }
                    } else if (cells.checkUpperNeighbor(x, y) == MazeCell.WALL || cells.checkLowerNeighbor(x, y) == MazeCell.WALL) {
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
    
    /**
     * Generates the maze. It was meant to take in an array of points from which
     * to start based on a generated path based on the path length, but I was
     * unable to implement that part. It just gets (1,1) instead.
     * @param endpoints 
     */
    private void makeMaze(ArrayList<Point> endpoints) {
        Point exit = new Point(2 * width - 1, 2 * height - 1); //The exit spot of the maze
        
        Random rand = new Random();
        
        //Points that we're done with (for now)
        ArrayList<Point> finishedPoints = new ArrayList<Point>();
        
        int lastmove = 0;
        
        boolean done = false;
        while (!done) {
            ArrayList<Point> nextlist = new ArrayList<Point>();
            ArrayList<Point> unmovedPoints = new ArrayList<Point>();

            for (int i = 0; i < endpoints.size(); ++i) {
                Point point = endpoints.get(i);
                Point movePoint = new Point(0,0);
                int direction = rand.nextInt(7); //6 makes it slightly more likely that we won't move
                
                if (direction == 0 && point.x > 1 && cells.checkLeftNeighbor(point) == MazeCell.WALL) {
                    movePoint = new Point(-1, 0);
                } else if (direction == 1 && point.x < cells.getWidth() - 2 && cells.checkRightNeighbor(point) == MazeCell.WALL) {
                    movePoint = new Point(1, 0);
                } else if (direction == 2 && point.y > 1 && cells.checkUpperNeighbor(point) == MazeCell.WALL) {
                    movePoint = new Point(0, -1);
                }else if (direction == 3 && point.y < cells.getHeight() - 2 && cells.checkLowerNeighbor(point) == MazeCell.WALL) {
                    movePoint = new Point(0, 1);
                }
                
                Point wallInBetween = point.add(movePoint);
                Point newPoint = wallInBetween.add(movePoint);
                if (!finishedPoints.contains(newPoint) && cells.isUnconnected(newPoint)) {
                    cells.set(wallInBetween, MazeCell.OPEN);
                    nextlist.add(newPoint);
                    finishedPoints.add(point);
                    lastmove = 0;
                } else {
                    nextlist.add(point);
                    unmovedPoints.add(point);
                    lastmove++;
                }
                
                //It got stuck. Try again on all finished points.
                if (lastmove >= 10) {
                    nextlist.addAll(finishedPoints);
                    finishedPoints.clear();
                }
                
                if (unmovedPoints.size() + finishedPoints.size() == width * height //If we've been to every spot
                        && !cells.isUnconnected(exit)) //And we made it to the exit
                    done = true; //Then we're done
            }
            
            //Update what we're going to mess with next time
            endpoints = nextlist;
        }
        
        //Open up start and exit
        cells.set(1, 0, MazeCell.OPEN);
        cells.set(cells.getWidth() - 2, cells.getHeight() - 1, MazeCell.OPEN);
    }
}
