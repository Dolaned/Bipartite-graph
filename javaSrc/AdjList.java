import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Adjacency list implementation for the FriendshipGraph interface.
 * <p>
 * Your task is to complete the implementation of this class.  You may add methods, but ensure your modified class compiles and runs.
 *
 * @author Jeffrey Chan, 2016.
 */
public class AdjList<T extends Object> implements FriendshipGraph<T> {
    public MyLinkedList[] aList;
    public HashMap<T, Integer> keyValues;
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

        if (aList == null) {
            aList = new MyLinkedList[1];
            aList[0] = new MyLinkedList();
            keyValues.put(vertLabel, 0);
        } else {

            MyLinkedList[] temp = new MyLinkedList[adjListSize];

            for (int i = 0; i < adjListSize; i++) {
                temp[i] = aList[i];
            }

            aList = new MyLinkedList[adjListSize + 1];

            for (int i = 0; i < adjListSize; i++) {
                aList[i] = temp[i];
            }

            keyValues.put(vertLabel, adjListSize + 1);
            aList[adjListSize + 1] = new MyLinkedList();
        }

        adjListSize++;
    } // end of addVertex()


    public void addEdge(T srcLabel, T tarLabel) throws IndexOutOfBoundsException {

        if (keyValues.get(srcLabel) == null || keyValues.get(tarLabel) == null) {
            throw new IndexOutOfBoundsException("Index not found");
        }

        int vertSrcEdge = keyValues.get(srcLabel);
        int vertTarEdge = keyValues.get(tarLabel);

        this.aList[vertSrcEdge].add(vertTarEdge);
        this.aList[vertTarEdge].add(vertSrcEdge);

    } // end of addEdge()

    public ArrayList<T> neighbours(T vertLabel) throws IndexOutOfBoundsException {

        ArrayList<T> neighbours = new ArrayList<T>();

        if (keyValues.get(vertLabel) == null) {
            throw new IndexOutOfBoundsException("vertex not found");
        }

        int vertexForNeighbours = keyValues.get(vertLabel);

        for (Map.Entry<T, Integer> entry : keyValues.entrySet()) {
            int value = entry.getValue();

            if (aList[vertexForNeighbours].search(value)) {
                neighbours.add(entry.getKey());
            }
        }

        return neighbours;
    } // end of neighbours()


    public void removeVertex(T vertLabel) {
        int arrayVal = keyValues.get(vertLabel);
        ArrayList<T> neighbours = neighbours(vertLabel);

        //remove edges
        if (aList[arrayVal].listSize > 0) {
            for (T neighbour : neighbours) removeEdge(vertLabel, neighbour);
        }

        //create temp array and copy
        MyLinkedList[] temp = new MyLinkedList[adjListSize - 1];

        for (int i = 0; i < adjListSize; i++) {
            if (i != arrayVal) {
                temp[i] = aList[i];
            }
        }
        //readjust list and copy back
        aList = new MyLinkedList[adjListSize - 1];


        System.arraycopy(temp, 0, aList, 0, adjListSize - 1);
        //decrement list size and update hashmap

        for (Map.Entry<T, Integer> entry : keyValues.entrySet()) {
            if(entry.getValue() > arrayVal){
                keyValues.put(entry.getKey(),entry.getValue()-1);
            }
        }
        adjListSize--;
        keyValues.remove(vertLabel);

    } // end of removeVertex()


    public void removeEdge(T srcLabel, T tarLabel) {


        if (keyValues.get(srcLabel) == null || keyValues.get(tarLabel) == null) {
            throw new IndexOutOfBoundsException("Index not found");
        }

        int vertSrcEdge = keyValues.get(srcLabel);
        int vertTarEdge = keyValues.get(tarLabel);

        this.aList[vertSrcEdge].remove(vertTarEdge);
        this.aList[vertTarEdge].remove(vertSrcEdge);

    } // end of removeEdges()


    public void printVertices(PrintWriter os) {

        for (Map.Entry<T, Integer> entry : keyValues.entrySet()) {
            os.print(entry.getKey() + " ");
        }
    } // end of printVertices()


    public void printEdges(PrintWriter os) {

        for(Map.Entry<T, Integer> entry: keyValues.entrySet()){

        }
        // Implement me!
    } // end of printEdges()


    public int shortestPathDistance(T vertLabel1, T vertLabel2) {
        // Implement me!

        // if we reach this point, source and target are disconnected
        return disconnectedDist;
    } // end of shortestPathDistance()

} // end of class AdjList