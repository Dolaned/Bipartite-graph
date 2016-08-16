

/**
 * Created by dylanaird on 12/08/2016.
 */
public class MyLinkedList{
    protected int listSize;
    protected Node listHead;

    public MyLinkedList() {
        this.listSize = 0;
        this.listHead = null;

    }

    /*add a new value in */
    public void add(int val) {
        Node node = new Node(val);

        /*If the list head node is set to null, set this node to the head of the list*/
        if(this.listHead == null){
            this.listHead = node;

        /*else set the new nodes next to the current list head and add the new node to the top of the list.*/
        }else{
            node.setNext(this.listHead);
            this.listHead = node;
        }
        this.listSize++;
    }


    public Object get(int index) {
    }


    public void add(int index, int newValue) throws IndexOutOfBoundsException {

        if(index >= this.listSize || index < 0){
            throw new IndexOutOfBoundsException("Invalid index inputted");
        }


        Node newNode = new Node(newValue);

        if (this.listHead == null) {
            this.listHead = newNode;
        }
        // list is not empty
        else {
            Node currNode = this.listHead;
            for (int i = 0; i < index-1; ++i) {
                currNode = currNode.getNodeNext();
            }

            newNode.setNext(currNode.getNodeNext());
            currNode.setNext(newNode);
        }

        this.listSize += 1;

    }


    public int remove(int index) {


    }

    private class Node{
        /*The Nodes Value*/
        protected int nodeValue;
        /*This Nodes Next Node*/
        protected Node nodeNext;


        public Node(int val){
            this.nodeValue = val;
            this.nodeNext = null;
        }

        public int getNodeValue(){
            return this.nodeValue;
        }
        public Node getNodeNext(){
            return this.nodeNext;
        }

        public void setNext(Node node){
            this.nodeNext = node;
        }
        public void setValue(int value){
            this.nodeValue = value;
        }


    }
}
