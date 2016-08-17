import java.io.*;
import java.util.*;

/**
 * Adjacency list implementation for the FriendshipGraph interface.
 * 
 * Your task is to complete the implementation of this class.  You may add methods, but ensure your modified class compiles and runs.
 *
 * @author Jeffrey Chan, 2016.
 */
public class AdjList <T extends Object> implements FriendshipGraph<T>
{
    public MyLinkedList[] aList;
    public HashMap<String, Integer> keyValues;
    public int adjListSize;

    /**
	 * Contructs empty graph.
	 */
    public AdjList() {
        adjListSize = 0;
        keyValues = new HashMap<>();
    	// Implement me!
    } // end of AdjList()
    
    
    public void addVertex(T vertLabel) {

        /*Convert T Object to String*/
        String label = vertLabel.toString();

        if(aList == null){
            aList = new MyLinkedList[1];
            keyValues.put(label,0);
        }else{

            MyLinkedList[] temp = new MyLinkedList[adjListSize];

            for(int i = 0; i < adjListSize; i++){
                temp[i] = aList[i];
            }

            aList = new MyLinkedList[adjListSize+1];

            for(int i = 0; i < adjListSize; i++){
                aList[i] =  temp[i];
            }

            keyValues.put(label, adjListSize+1);
            aList[adjListSize+1] = new MyLinkedList();
        }

        adjListSize++;
        // Implement me!
    } // end of addVertex()
	
    
    public void addEdge(T srcLabel, T tarLabel) {
        // Implement me!
    } // end of addEdge()
	

    public ArrayList<T> neighbours(T vertLabel) {
        ArrayList<T> neighbours = new ArrayList<T>();
        
        // Implement me!
        
        return neighbours;
    } // end of neighbours()
    
    
    public void removeVertex(T vertLabel) {
        String label = vertLabel.toString();

        MyLinkedList[] temp = new MyLinkedList[adjListSize-1];

        for(int i = 0; i < adjListSize; i++){
            if(i != keyValues.get(label)){
                temp[i] = aList[i];
            }
        }
        aList = new MyLinkedList[adjListSize-1];

        for(int i = 0; i < adjListSize-1; i++){
            aList[i] = temp[i];
        }
        adjListSize--;

        // Implement me!
    } // end of removeVertex()
	
    
    public void removeEdge(T srcLabel, T tarLabel) {
        // Implement me!
    } // end of removeEdges()
	
    
    public void printVertices(PrintWriter os) {
        // Implement me!
    } // end of printVertices()
	
    
    public void printEdges(PrintWriter os) {
        // Implement me!
    } // end of printEdges()
    
    
    public int shortestPathDistance(T vertLabel1, T vertLabel2) {
    	// Implement me!
    	
        // if we reach this point, source and target are disconnected
        return disconnectedDist;    	
    } // end of shortestPathDistance()
    
} // end of class AdjList