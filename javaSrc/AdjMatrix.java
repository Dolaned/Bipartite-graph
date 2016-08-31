import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * Adjacency matrix implementation for the FriendshipGraph interface.
 * 
 * Your task is to complete the implementation of this class.  You may add methods, but ensure your modified class compiles and runs.
 *
 * @author Jeffrey Chan, 2016.
 */
public class AdjMatrix <T extends Object> implements FriendshipGraph<T>
{

	//map of vertices and their lcaotion within the array
	private HashMap<T, Integer> vertices;
    //edges where 
	private int[][] edges;
    //current number of elements (vertices)
	private int numOfV;
    //rate by which the edge array grows.
	private int volume;
    /**
	 * Contructs empty graph.
	 */
    public AdjMatrix() {
        vertices = new HashMap<>();
        //need to initilise each value
        volume = 10;
        edges = new int[volume][volume];
        numOfV = 0;
    	// Implement me! Done!
    } // end of AdjMatrix()
    
    
    public void addVertex(T vertLabel) {
        //better solution using volume size to grow only as needed, rather than everytime
        
        
        //key = vertice / value = position in array
        //no duplicate keys, so check if vertce exists
    	if(vertices.get(vertLabel)!= null)
        {
        	//assume program does nothing
    		//System.out.println("vertice already exsists");
        	return;
        }
    	
    	vertices.put(vertLabel, numOfV);
        numOfV++;
        
        if(numOfV <= volume)
        {
        	//dont need to resize as still smaller than volume
        }
        else
        {
        	//gone for simple brute force solution to intialise array
        	//copy old array into tempary one
        	int[][] temp = new int[edges.length][edges.length];
        	
        	for(int i = 0; i < edges.length;i++)
        	{
        		for(int j = 0;j < edges.length;j++)
        		{
        			temp[i][j] = edges[i][j]; 
        		}
        	}
        	//growth factor
        	volume = volume * 2;
        	//create new array
        	edges = new int[volume][volume];
        	//populate new array with old values
        	for(int i = 0; i < temp.length;i++)
        	{
        		for(int j = 0;j < temp.length;j++)
        		{
        			edges[i][j] = temp[i][j]; 
        		}
        	}
        }
    } // end of addVertex()
	
    
    public void addEdge(T srcLabel, T tarLabel) throws IllegalArgumentException {
    	//check if both vertices exist within graph
    
    	if(vertices.get(srcLabel) == null || vertices.get(tarLabel) == null)
    	{
    		throw new IllegalArgumentException("one or both of incident vertices"
    				+ " does not exist");
    	}
    	
    	//else get value for each vertice to add edge
    	Integer x = vertices.get(srcLabel);
    	Integer y = vertices.get(tarLabel);

    	//then create edge
    	//undirected graph, make sure to add edge in both directions
    	edges[x][y] = 1;
    	edges[y][x] = 1;
    	// Implement me!
        
    } // end of addEdge()
	

    public ArrayList<T> neighbours(T vertLabel) throws IllegalArgumentException{
    	ArrayList<T> neighbours = new ArrayList<T>();
    	
    	if(vertices.get(vertLabel) == null)
    	{
    		//vertice does not exist within table; exit
    		throw new IllegalArgumentException("Vertex does not exist");
    	}

    	//otherwise need to find out all the vertices attached
    	int v = (int) vertices.get(vertLabel);
    	
    	//after finding column for V check all other V for edges
    	for(int i = 0; i <numOfV; ++i)
    	{
    		if(edges[i][v] == 1)
    		{
    			//get key based on value then add to ArrayList
    			neighbours.add(getKeyFromValue(i));
    		}
    	}
    	// Implement me!

    	return neighbours; 

    } // end of neighbours()
    
    
    public void removeVertex(T vertLabel) throws IllegalArgumentException{
    	//more elegant solution
    	//make use of size and volume (size = current elements, 
    	//								volume ratio it grows by)
    	//grow by standard size each time and no need to shrink 
    	//TO DELETE
    	//move row/column to delete to the end of the array, 
    	//	swapping with data currently in that position
    	//then just delete the the last row/column in array
    	//no need to resize everytime
    	
    	//check that vertice exists
    	if(vertices.get(vertLabel) == null)
    	{
    		throw new IllegalArgumentException("Vertex does not exist");
    	}

    	//get last V in Map for swapping
    	T tempVert  = getKeyFromValue(numOfV - 1);
    	//store value of V to remove to switch with element replacing it
    	int tempValue = vertices.get(vertLabel);
    	
    	//if V to remove is not last V in map, need to swap with last V
    	if(numOfV-1 != tempValue)
    	{
    		for(int i = 0;i<numOfV; ++i)
    		{
    			if(i != tempValue)
    			{
    				edges[tempValue][i] = edges[(numOfV-1)][i];
    				edges[i][tempValue] = edges[i][(numOfV-1)];
    			    //need to update map with correct values
    			}
    			else if(i == numOfV -1)
    			{
    				edges[tempValue][tempValue] = edges[i][i];
    			}
    		}
    	}
    	
    	//ensure no incorrect values in array
    	for(int i =0;i<numOfV;++i)
    	{
    		edges[numOfV-1][i] = 0;
    		edges[i][numOfV-1] = 0;
    	}
    	
    	//update map with new value for last element
    	vertices.put(tempVert, tempValue);

    	//remove V from map
    	vertices.remove(vertLabel);
    	
    	//decrement number of vertices
    	numOfV--;
        
    } // end of removeVertex()
	
    
    public void removeEdge(T srcLabel, T tarLabel) throws IllegalArgumentException {
        
    	if(vertices.get(srcLabel) == null || vertices.get(tarLabel) == null)
        {
        	throw new IllegalArgumentException("One or both dont exist");
        }
    	
    	//get location of V in array
    	Integer x = (int)vertices.get(srcLabel);
        Integer y = (int)vertices.get(tarLabel);
        
        
        
        //change both directions of the graph
        edges[x][y] = 0;
        edges[y][x] = 0;
        
        // Implement me!
    } // end of removeEdges()
	
    
    public void printVertices(PrintWriter os) {
        
    	//os.println(vertices.keySet().toString());
    	for(T key: vertices.keySet())
    	{
    		os.append(key.toString() + " ");
    	}
    	os.append("\n");
    	os.flush();
    	
    	
    	// Implement me!
    } // end of printVertices()
	
    
    public void printEdges(PrintWriter os) {
        
    	//go through array to find edges
    	for(int i =0;i<numOfV;++i)
    	{
    		for(int j =0;j<numOfV;++j)
    		{
    			//System.out.println('i' + 'j' + numOfV);
    			//if edge found get V turn to string and push to os
    			if(edges[i][j] == 1)
    			{
    				String edge1 = getKeyFromValue(i).toString();
    				String edge2 = getKeyFromValue(j).toString();
    				
    				os.println(edge1+" "+edge2);
    			}
    		}
    	}
    	// Implement me!
    } // end of printEdges()
    
    
    //my own method, used to find the Key (or V) based on the value
    //slow as it will need to check all Keys to find correct value
    public T getKeyFromValue(int value)
    {
    	for(T o : vertices.keySet())
		{
			if(vertices.get(o).equals(value))
			{
				return o;
			}
		}
    	return null;
    }
    
    public int shortestPathDistance(T vertLabel1, T vertLabel2) throws IllegalArgumentException{
    	// Check if both V are in Map
        if(!vertices.containsKey(vertLabel1) || !vertices.containsKey(vertLabel2))
        {
        	throw new IllegalArgumentException("One or both dont exist");
        }
        
        //create array of visted nodes
    	boolean[] marked = new boolean[numOfV];
    	for(int i =0;i<numOfV; i++)
    	{
    		marked[i] = false;
    	}
    	
    	//create queue of nodes to check
    	ArrayDeque<KeyEntry> list = new ArrayDeque<KeyEntry>();
    	
    	//put first node in queue and start distance counter
    	int d =0;
    	list.add(new KeyEntry(vertLabel1, d));
    	
    	//keep going until no more nodes to check
    	while(!list.isEmpty())
    	{
    		KeyEntry entryKey = list.remove();
    		
    		T currentT = entryKey.getVertex();
    		//if dest the print out distance value
    		if(currentT.equals(vertLabel2) )
    		{
    			return entryKey.getDistance();
    		}
    		
    		//otherwise mark as visted and check neighbours
    		marked[vertices.get(currentT)] = true;
    		
    		//repeat process for finding nieghbours and add each to the queue
    		for(int i =0;i<numOfV; ++i)
    		{
    			if(edges[vertices.get(currentT)][i] == 1)
    			{
    				if(marked[i] == false)
    				{
    					list.add(new KeyEntry(getKeyFromValue(i), 
    							entryKey.getDistance()+1));
    				}
    			}
    		}
    	}
    	
    	// if we reach this point, source and target are disconnected
        return disconnectedDist;    	
    } // end of shortestPathDistance()
    
    //class to handle the data stored in the queue for BFS
    class KeyEntry
    { 
    	private T vertex; 
    	private int distance; 
    	
    	 
    	KeyEntry(T t, int i)
    	{ 
    		this.vertex = t; 
    		this.distance = i; 
    	} 
    	
    	 
    	T getVertex()
    	{ 
    		return this.vertex; 
    	} 
    	
    	int getDistance()
    	{ 
    		return this.distance; 
    	} 

    }
} // end of class AdjMatrix
