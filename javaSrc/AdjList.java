import java.io.PrintWriter;
import java.security.KeyPair;
import java.util.*;

/**
 * Adjacency list implementation for the FriendshipGraph interface.
 * <p>
 * Your task is to complete the implementation of this class.  You may add methods, but ensure your modified class compiles and runs.
 * TODO: implement shortest path (BFS)
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
        aList = new MyLinkedList[1];
    } // end of AdjList()


    public void addVertex(T vertLabel) {

        if (keyValues.get(vertLabel) != null) {
            System.out.println("Vertex Exists");
        } else {
            if (adjListSize == 0) {
                aList[0] = new MyLinkedList();
                keyValues.put(vertLabel, adjListSize);
            } else {
                MyLinkedList[] temp = new MyLinkedList[adjListSize];

                for (int i = 0; i < adjListSize; i++) {
                    temp[i] = aList[i];
                }

                aList = new MyLinkedList[adjListSize + 1];

                for (int i = 0; i < adjListSize; i++) {
                    aList[i] = temp[i];
                }

                keyValues.put(vertLabel, adjListSize);
                //access as current size because array indexes start at 0
                aList[adjListSize] = new MyLinkedList();
            }
            adjListSize++;
        }
    } // end of addVertex()


    public void addEdge(T srcLabel, T tarLabel) {

        if (keyValues.get(srcLabel) == null || keyValues.get(tarLabel) == null) {
            System.out.println("Index Not Found");
        } else {

            int vertSrcEdge = keyValues.get(srcLabel);
            int vertTarEdge = keyValues.get(tarLabel);

            if (!(aList[vertSrcEdge].search(vertTarEdge) && aList[vertTarEdge].search(vertSrcEdge))) {
                aList[vertSrcEdge].add(vertTarEdge);
                aList[vertTarEdge].add(vertSrcEdge);
            } else {
                System.out.println("Edge already Exists");
            }
        }
    } // end of addEdge()

    public ArrayList<T> neighbours(T vertLabel) {
        ArrayList<T> neighbours = new ArrayList<T>();

        if (keyValues.get(vertLabel) == null) {
            System.out.println("vertex not found");
        } else {
            int vertexForNeighbours = keyValues.get(vertLabel);
            //System.out.println("neighbour Label: "+vertLabel.toString() +" vertex value:  " + vertexForNeighbours );

            for (int i = 0; i < aList[vertexForNeighbours].listSize; i++) {
                for (Map.Entry<T, Integer> entry : keyValues.entrySet()) {
                    if (aList[vertexForNeighbours].get(i) == entry.getValue() && vertLabel != entry.getKey()) {
                        neighbours.add(entry.getKey());
                    }
                }
            }
        }
        return neighbours;
    } // end of neighbours()


    public void removeVertex(T vertLabel) {

        if (keyValues.get(vertLabel) == null) {
            System.out.println("Vertex Not Found");
        } else {
            int arrayVal = keyValues.get(vertLabel);
            ArrayList<T> neighbours = neighbours(vertLabel);

            //remove edges
            if (aList[arrayVal].listSize > 0) {
                //System.out.println(neighbours.toString());
                for (T neighbour : neighbours){
                    removeEdge(vertLabel, neighbour);
                }
            }

            //create temp array and copy
            MyLinkedList[] temp = new MyLinkedList[adjListSize - 1];

            for (int i = 0; i < adjListSize-1; i++) {

                if (i >= arrayVal) {
                    temp[i] = aList[i + 1];
                } else {
                    temp[i] = aList[i];
                }
            }
            //readjust list and copy back
            aList = new MyLinkedList[adjListSize - 1];


            System.arraycopy(temp, 0, aList, 0, adjListSize - 1);
            //decrement list size and update hashmap

            for (Map.Entry<T, Integer> entry : keyValues.entrySet()) {
                if (entry.getValue() > arrayVal) {
                    for(int i = 0; i < adjListSize-1; i ++){
                        if(aList[i].search(entry.getValue())){
                            aList[i].remove(entry.getValue());
                            aList[i].add(entry.getValue() - 1);
                        }
                    }
                    //System.out.println(entry.getKey().toString() + "    "+entry.getValue());
                    keyValues.put(entry.getKey(), entry.getValue() - 1);
                }
            }
            adjListSize--;
            keyValues.remove(vertLabel);
        }


    } // end of removeVertex()


    public void removeEdge(T srcLabel, T tarLabel) {

        if (keyValues.get(srcLabel) == null || keyValues.get(tarLabel) == null) {
            System.out.println("1 or more vertexes not found");
        } else {
            int vertSrcEdge = keyValues.get(srcLabel);
            int vertTarEdge = keyValues.get(tarLabel);

            aList[vertSrcEdge].remove(vertTarEdge);
            aList[vertTarEdge].remove(vertSrcEdge);
        }
    } // end of removeEdges()


    public void printVertices(PrintWriter os) {

        List<String> sortedKeys=new ArrayList(keyValues.keySet());
        Collections.sort(sortedKeys);
        for (String key : sortedKeys) {
            os.append(key.toString() + " ");
        }
        os.append("\n");
        os.flush();
    } // end of printVertices()


    public void printEdges(PrintWriter os) {
        for (T key : keyValues.keySet()) {
            ArrayList<T> n = neighbours(key);
            for (T oj : n) {
                os.append(oj.toString() + " ");
                os.append(key.toString() + "\n");

            }
        }
        os.flush();
    } // end of printEdges()


    public int shortestPathDistance(T vertLabel1, T vertLabel2) {

        ArrayDeque<KeyEntry> q = new ArrayDeque<>();
        HashMap<T, Boolean> visited = new HashMap<>();

        q.add(new KeyEntry(vertLabel1, 0));
        visited.put(vertLabel1, true);//set current nodes visited state to true;

        while (!q.isEmpty()) {
            //remove keyentry from queue
            KeyEntry entry  = q.remove(); // remove the head of queue

            //assign current to the entries vertex
            T current = entry.getVertex();

            if (current.equals(vertLabel2)) {
                return entry.getDistance();
            }else{
                for (T node : neighbours(current)) {
                    if (!visited.containsKey(node)) {
                        q.add(new KeyEntry(node, entry.getDistance() +1));
                        visited.put(current, true);
                    }
                }
            }
        }
        // if we reach this point, source and target are disconnected
        return disconnectedDist;
    } // end of shortestPathDistance()

class KeyEntry{
    private T vertex;
    private int distance;

    KeyEntry(T t, int i){
        this.vertex = t;
        this.distance = i;
    }

     T getVertex(){
        return this.vertex;
    }
     int getDistance(){
        return this.distance;
    }
}
} // end of class AdjList