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
        	//assume program does nothing is repeated
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
        	/*
        	 * int[][] temp = new int[volume][volume];
        	
        	System.arraycopy(edges, 0, temp, 0, edges.length);//temp = edges;
        	volume = volume * 2;
        	edges = new int[volume][volume];
        	System.arraycopy(temp, 0, edges, 0, numOfV - 1);//edges = temp;
        	*/
        	
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
        	
        	/*
        	edges.clone();
        	
        	int [][] temp = new int[edges.length][];
        	for(int i =0; i< edges.length;++i)
        	{
        		int[] aMatrix = edges[i];
        		System.out.println(aMatrix.length);
        		int   aLength = aMatrix.length;
        		temp[i] = new int[aLength];
        		System.arraycopy(aMatrix, 0, temp[i], 0, aLength);
        	}
        	
        	volume = volume *2;
        	System.out.println(volume);
        	
        	edges = new int[volume][];
        	for(int i =0; i< volume;++i)
        	{
        		int[] aMatrix = temp[i];
        		int   aLength = aMatrix.length;
        		edges[i] = new int[volume];
        		System.arraycopy(aMatrix, 0, edges[i], 0, aLength);
        	}
        	*/
        	
        	
        	/*
        	 * int [][] myInt = new int[matrix.length][];
			for(int i = 0; i < matrix.length; i++)
			{
			  int[] aMatrix = matrix[i];
			  int   aLength = aMatrix.length;
			  myInt[i] = new int[aLength];
			  System.arraycopy(aMatrix, 0, myInt[i], 0, aLength);
			}
			*/
        	
        }
        
        /*
        // need to cerate new array for edges, create new then copy from old
        tempEdges = new int[numofV][numofV];
        for(int i = 0;i<numOfV;i++)
        {
            for(int j=0;j<numOfV;j++)
            {
                tempEdges[i][j] = edges[i][j];
            }
        }
        //replace array with newly sized array
        edges = new int[numOfV][numOfV];

        for(int i = 0;i<numOfV;i++)
        {
            for(int j=0;j<numOfV;j++)
            {
                edges[i][j] = tempEdges[i][j];
            }
        }*/


        // Implement me!
    } // end of addVertex()
	
    
    public void addEdge(T srcLabel, T tarLabel) {
    	//check if both vertices exist within graph
        boolean error = false;
        if(vertices.get(srcLabel) == null)
        {
        	//System.out.println("error source vertice not found");
        	error = true;
        }
        if(vertices.get(tarLabel) == null)
        {
        	//System.out.println("error target vertice not found");
        	error = true;
        }
        if(error == true)
        	return;
        
        //else get value for each vertice to add edge
        Integer x = (int) vertices.get(srcLabel);
        Integer y = (int) vertices.get(tarLabel);
        
        
        //redundant check
        /*if(x==null||y==null){
            System.out.println("vertice not found");
        	return;
            //error vertix not found
        }*/
        //perofrm more checks
        //then create edge
        //undirected graph, make sure to add edge in both directions
        edges[x][y] = 1;
        edges[y][x] = 1;
        // Implement me!
    } // end of addEdge()
	

    public ArrayList<T> neighbours(T vertLabel) {
        ArrayList<T> neighbours = new ArrayList<T>();
        //String label = vertLabel.toString();
        if(vertices.get(vertLabel) == null)
        {
        	//vertice does not exist within table; exit
        	return null;
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
    
    
    public void removeVertex(T vertLabel) {
      //  String vertLbl = vertLabel.toString();
        //more elegant solution
        //make use of size and volume (size current elements, volume ratio it goes by)
        //grow by standard size each time and no ned to shrink 
        //TO DELETE
        //move row/column to delete to the end of the array, swapping with data currently in that position
        //then just delete the the last row/column in array
        //no need to resize everytime
        if(vertices.get(vertLabel)== null)
        {
        	System.out.println("vertice doesn't exist");
        	return;
        }
        
        //get last element in Map for swapping
        T tempVert  = getKeyFromValue(numOfV - 1);
        //store value of V to remove to switch with element replacing it
        int tempValue = vertices.get(vertLabel);
    	/*
        System.out.println(tempVert + " " +tempValue);
    	
    	System.out.println(edges.length+" "+numOfV);
    	System.out.println(edges[numOfV -1][0]);
    	System.out.println(edges[0][numOfV -1]);
		*/
    	
        //if 
        if(numOfV-1 != tempValue)
        {
            for(int i = 0;i<numOfV; ++i)
            {
                 edges[tempValue][i] = edges[(numOfV-1)][i];
                 edges[i][tempValue] = edges[i][(numOfV-1)];
                 //need to update map with correct values
            }
        }
        
        for(int i =0;i<numOfV;++i)
        {
        	edges[numOfV-1][i] = 0;
        	edges[i][numOfV-1] = 0;
        }
        //System.out.println(vertices.toString() + tempVert + tempValue);
        vertices.remove(vertLabel);
        vertices.put(tempVert, tempValue);
        
        numOfV--;
        //System.out.println(vertices.toString());
        /*
        int v = (int)vertices.get(vertLbl);
        if(numOfV != v)
        {
            for(int i = 0;i<numOfV; ++i)
            {
                 edges[v][i] = edges[numOfV][i];
                 edges[i][v] = edges[i][numOfV];
                 //need to update map with correct values
            }
        }
        
        for(int i =0;i<numOfV;++i)
        {
        	edges[numOfV][i] = 0;
        	edges[i][numOfV] = 0;
        }
        
        T tempKey = getKeyFromValue(numOfV);
        int tempInt  = vertices.get(vertLabel);
        vertices.remove(tempKey);
        vertices.remove(vertLabel);
        
        System.out.println(vertices.toString());
        vertices.put(tempKey, tempInt);
        System.out.println(vertices.toString());

        numOfV--;
        
        */
        /*
        if(v==null){
            //node doesn't exsisst
            return;
        }


        int[][] tempV = new int[numOfV -1][numOfV -1];
        int newI = 0, newJ = 0;
        for(int i =0;i <numOfV;i++){
            if(i!=v){
                for(int j = 0;j<numOfV;j++){
                    if(i==v||j==v){
                        
                    }
                    else if{
                        tempV[newI][newJ] = edges[i][j];
                        newJ++;
                    }
                    
                }
                newJ = 0;
                newI++;
            }
        }
        */
        // Implement me!
    } // end of removeVertex()
	
    
    public void removeEdge(T srcLabel, T tarLabel) {
        String srcLbl = srcLabel.toString();
        String tarLbl = tarLabel.toString();

        Integer x = (int)vertices.get(srcLbl);
        Integer y = (int)vertices.get(tarLbl);
        

        if(x==null||y==null){
            System.out.println("vertice not found");
        	return;
        }

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
        
    	for(int i =0;i<numOfV;++i)
    	{
    		for(int j =0;j<numOfV;++j)
    		{
    			//System.out.println('i' + 'j' + numOfV);
    			if(edges[i][j] == 1)
    			{
    				String edge1 = getKeyFromValue(i).toString();
    				String edge2 = getKeyFromValue(j).toString();
    				os.print(edge1 +" ");
    				os.println(edge2);
    			}
    		}
    	}
    	// Implement me!
    } // end of printEdges()
    
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
    
    public int shortestPathDistance(T vertLabel1, T vertLabel2) {
    	// Implement me!
        
    	boolean[] marked = new boolean[numOfV];
    	for(int i =0;i<numOfV; i++)
    	{
    		marked[i] = false;
    	}
    	
    	ArrayDeque<KeyEntry> list = new ArrayDeque<KeyEntry>();
    	
    	int d =0;
    	list.add(new KeyEntry(vertLabel1, d));
    	
    	
    	
    	while(!list.isEmpty())
    	{
    		KeyEntry entryKey = list.remove();
    		
    		T currentT = entryKey.getVertex();
    		
    		if(currentT.equals(vertLabel2) )
    		{
    			return entryKey.getDistance();
    		}
    		
    		marked[vertices.get(currentT)] = true;
    		
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
