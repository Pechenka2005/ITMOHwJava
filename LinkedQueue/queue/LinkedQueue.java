package queue;

public class LinkedQueue extends AbstractQueue {

    private Node head = null;
    private Node tail = null;


    public void enqueue(Object e) {
        if(isEmpty()) {
            head = tail = new Node(e);
        }
        else {
            Node x = new Node(e);
            tail.setNext(x);
            tail = tail.getNext();
        }
        size++;
    }
    public Object element() {
        assert size > 0;
        return head.getValue();
    }

    public Object dequeue() {
        assert size > 0;
        size--;
        Object x = head.getValue();
        head = head.getNext();
        return x;
    }


}
