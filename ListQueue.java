import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListQueue<E> {
    //class for creating ListQueue.
    public class Node<E>{
        //inner class for creating a node
        private E data;
        //creating data field for data in the node
        private Node<E> next;
        //creating data field for next node of the node
        private int priority;
        //creating data field for the priority of a node
        public Node(E dataItem){
            //initializing the node if only data is given
            data = dataItem;
            next = null;
            priority = Integer.MAX_VALUE;;
        }
        public Node(E dataItem, int priority){
            //initializing node when only data and priority is give
            this.priority = priority;
            data = dataItem;
            next = null;
        }
        public Node(E dataItem, Node<E> next, int priority){
            //initializing node when all attributes are given
            this.priority = priority;
            this.next = next;
            data = dataItem;
        }
        public E getData(){
            return data;
        }
        //returns the data of a node
        public Node<E> getNext(){
            return next;
        }
        //returns the next node to that of the current node
    }
    public class Iter implements Iterator<E> {
        //inner class for iterating through the ListQueue

        private Node<E> next =  front;
        //creating data field for node as front

        public boolean hasNext() {
            //checking if there is another node following the current one
            if (next == null) {
                return false;
            }
            return true;
        }
        public E next(){
            //returns the data stored in the next node (attribute) and will update the next node attribute with the next node of next node It will throwNoSuchElementException if next node is null
            if(next.getData() == null){
                throw new NoSuchElementException();
            }
            E smth = next.getData();
            next = next.getNext();
            return smth;
        }
        @Override
        public void remove(){
            throw new UnsupportedOperationException();
        }
        //throws UnsupportedOperationException

    }
    private Node<E> front;
    //creates data field for the node of the front of the ListQueue
    private int size;
    //creates data field for the size of the ListQueue
    public ListQueue(){
        //initializes ListQueue when it is empty
        front = null;
        size = 0;
    }
    public ListQueue(Node<E> first){
        //initializes ListQueue when given first node
        front = first;
        size = 1;

    }
    public void setFront(Node<E> front){
        this.front = front;
    }
    //sets the front of the ListQueue
    public Node<E> getFront(){
        return front;
    }
    //returns front of ListQueue
    public int getSize(){
        return size;
    }
    //returns size of ListQueue
    public E peek(){
        //returns the data of the front node
        if(front == null){
            return null;
        }
        return front.getData();
    }
    public boolean offer(E item, int priority){
        //adds item to a position according to its priority. If there exists same-priority tasks in the list, item will be added after the existing same-priority level tasks. Throws NullPointerException if the item sent to the method is null
        Node<E> on = new Node(item, priority);
        if(item == null){
            throw new NullPointerException();
        }
        else if(front == null){
            front = on;
            on.next = null;
            size++;
            return true;
        }
        else if(front.priority > on.priority){
            on.next = front;
            front = on;
            size++;
            return true;
        }
        Node<E> current = front;
        while(current.getNext() != null){
            if(current.priority == on.priority && current.getNext().priority != on.priority){
                on.next = current.next;
                current.next = on;
                size++;
                return true;
            }
            else if(current.getNext().priority > on.priority && current.priority < on.priority){
                on.next = current.next;
                current.next = on;
                size++;
                return true;
            }

            current = current.next;
        }if(current != null && current.getNext() == null){
            if(current.priority <= on.priority){
                on.next = null;
                current.next = on;
                size++;
                return true;
            }on.next = current;
            current.next = null;
            size++;
            return true;
        }
        return false;
    }

    public boolean addRear(E item){
        //adds item at the end of queue. It always returns true except it throws NullPointerException if the item sent to the method is null.
        Node<E> on = new Node(item);
        if(item == null){
            throw new NullPointerException();
        }
        if(front == null){
            front = on;
            size++;
            return true;
        }
        Node<E> current = front;
        while(current.next != null){
            current = current.next;
        }
        current.next = on;
        on.next = null;
        size++;
        return true;
    }

    public E poll(){
        //returns the data at the front of the queue and removes it from the queue. Throws NullPointerException if the item at the front of the queue is null
        if(front.getData()==null){
            throw new NullPointerException();
        }
        E curr = front.getData();
        front = front.next;
        size--;
        return curr;

    }
    public boolean remove (Node<E> toBeRemoved){
        //takes a node to be removed and removes it from the queue. Correct links needs to be established after the node is removed
        if(toBeRemoved == front && size == 1){
            front = null;
            size = 0;
            return true;
        }
        else if (toBeRemoved == front){
            front = front.next;
            size--;
            return true;
        }
        else {
            Node<E> current = front;
            while (current.next != toBeRemoved && current.next != null) {
                current = current.next;
            }
            if (current.next == toBeRemoved) {
                current.next = toBeRemoved.next;
                toBeRemoved.next = null;
                size--;
                return true;
            } else {
                return false;
            }
        }
    }
    public Iterator<E> iterator(){
        //returns instance of the Iter class defined above
        return new Iter();

    }
}
