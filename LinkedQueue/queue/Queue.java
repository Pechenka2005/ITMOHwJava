package queue;

public interface Queue {

    void enqueue(Object e);

    Object element();

    Object dequeue();

    int size();

    boolean isEmpty();

    void clear();
}
