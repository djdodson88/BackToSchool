package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Pedestrian
{
	private Image image, left, up, right, down;
	private ArrayList<Point> path;
	private Point location;
	private int pathIndex;
	private boolean invalid;
	
	public Pedestrian(Point start, Point destination, Campus campus)
	{
		try {
			left = ImageIO.read(new File("art/characters/pedestrian1_left.png"));
			up = ImageIO.read(new File("art/characters/pedestrian1_back.png"));
			right = ImageIO.read(new File("art/characters/pedestrian1_right.png"));
			down = ImageIO.read(new File("art/characters/pedestrian1_front.png"));
		} catch (IOException e) {System.out.println(e.getLocalizedMessage());};
		
		// variables for drawing on campus
		location = start;
		image = down;
		
		// find a path from the start point to the destination point
		pathIndex = 0;
		path = aStarSearch(start, destination, campus);	
		if (path == null)
			invalid = true;
		else
			invalid = false;
	}
	
	public boolean isInvalid()
	{
		return invalid;
	}
	
	public boolean hasMove() 
	{
		if(pathIndex <= path.size()-1)
			return true;
		else
			return false;
	}
	
	public void move()
	{	
		if (pathIndex <= path.size()-1)
		{
			Point next = path.get(pathIndex++);
			
			if (next.x < location.x)
				image = left;
			else if (next.x > location.x)
				image = right;
			else if (next.y < location.y)
				image = up;
			else if (next.y > location.y)
				image = down;
			
			location = next;
		}
		else
		{
			//TODO: handle pedestrian at destination
		}
	}
	
	public Point getLocation()
	{
		return location;
	}
	
	public Image getImage()
	{
		return image;
	}
	
	private ArrayList<Point> aStarSearch(Point location, Point destination, Campus campus)
	{
    	int traveled = 0;
    	PriorityQueue<CPoint> frontier = new PriorityQueue<CPoint>(); 
    	ArrayList<Point> solution = new ArrayList<Point>();
    	ArrayList<Point> observations;
    	HashMap<Integer,Point> explored = new HashMap<Integer,Point>();
    	
    	Point start = location;
    	Point end = destination;
    	explored.put(start.hashCode(), start);
    	Point current = start;
    	solution.add(start);
    	
    	
		while (!(solution.get(0).equals(start) && solution.get(solution.size()-1).equals(end)))
    	{
    		observations = campus.getAdjacent(current);
    		
    		if (observations.size() == 0)
    		{	System.out.println("Impossible destination.");
    			return null;
    		}
    		else if (observations.size() >= 1)
    		{	
    			for (Point p : observations)
    			{	if (explored.get(p.hashCode()) == null)
    				{	double heuristic = Math.pow((Math.pow((end.x-p.x), 2) + Math.pow((end.y-p.y), 2)),.5);
    					frontier.add(new CPoint(p, heuristic, traveled+1));
    					explored.put(p.hashCode(), p);
    					
    					if (p.equals(end))
    					{	solution.add(p);
    						return cleanSolution(solution, campus, true);
    					}
    				}	
    			}
    			
    			if (frontier.size() == 0)
    			{	System.out.println("Maze is not solvable");
    				return null;
    			}
    			
    			// set variables for next iteration
    			CPoint next = frontier.remove();	// best valued move  				
    			traveled = next.getTraveled();
    			current = next.getPoint();
    			solution.add(current);
    		}
    	}
		
		return cleanSolution(solution, campus, true);
    }
	
	private ArrayList<Point> cleanSolution(ArrayList<Point> solution, Campus campus, boolean backward)
	{	
		Point current, other;
		int index = solution.size()-1;
    	while (index > 1)
    	{	current = solution.get(index);
    		other = solution.get(index-1);
    		while (!(campus.getAdjacent(current).contains(other)))	// weed out invalid moves
    		{	
    			// the move before this one had to exist in the search path at some point, backtrack until found
    			solution.remove(index-1);
    			index--; // current's index is one less due to the removal
    			other = solution.get(index-1);	// only other need be updated for next iteration
    		}
    		index--;
    	}
    	
    	//if (!campus.getAdjacent(solution.get(0)).contains(solution.get(1)))
    		//System.out.println("Invalid solution");
    	
    	return solution;
	}

	/* CPoint
	 * made from points in Campus for use with A* Search
	 * heuristic: value of point to pedestrian
	 * traveled: distance from a point to the pedestrian
	 */
	private class CPoint implements Comparable<CPoint>
	{
	    private Point point;
	    private double heuristic;
	    private int traveled;

	    public CPoint(Point pair, double heuristic, int traveled)
	    {
	        this.point = pair;
	        this.heuristic = heuristic;
	        this.traveled = traveled;
	    }

	    public double getHeuristic()
	    {
	        return heuristic;
	    }
	    
	    public int getTraveled()
	    {
	    	return traveled;
	    }

	    public Point getPoint()
	    {
	        return point;
	    }

	    public boolean equals(Object o)
	    {
	        CPoint cp = (CPoint)o;
	        return cp.equals(cp.getPoint());
	    }

	    //lower values get higher priority
	    public int compareTo(CPoint other)
	    {
	        if(((heuristic*2)+traveled) > (other.getHeuristic()*2 + other.getTraveled()))
	            return 1;
	        else if(((heuristic*2)+traveled) < (other.getHeuristic()*2 + other.getTraveled()))
	            return -1;
	        else
	            return 0; 
	    }
	}
	
	public static void main(String[] args)
	{
		Campus campus = new Campus();
		ArrayList<Point> doors = campus.getDoors(true);
		int pick = (int)(Math.random()*doors.size());
		Point destination = doors.get(pick);
		
		Point point = new Point(-1,-1);
		while (!campus.isTraversable(point.x,point.y))
		{
			int x = (int)(Math.random()*30);
			int y = (int)(Math.random()*20);
			point.setLocation(x,y);
		}
		Point dest = new Point(destination.x, destination.y+1);
		Pedestrian pedestrian = new Pedestrian(point, dest, campus);
		
		ArrayList<Point> path = pedestrian.path;
		
		System.out.println("Start:" + point.toString());
		System.out.println("Destination:" + destination.toString());
		for (Point p : path)
			System.out.println(p.toString());
	}
}