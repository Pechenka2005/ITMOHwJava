package queue;

public class MyArrayQueueTest {
    public static void fill(ArrayQueue queue) {
        for (int i = 0; i < 20; i++) {
            queue.push(i);
        }
    }

    public static void dump(ArrayQueue queue) {
        while (!queue.isEmpty()) {
            System.out.println(queue.size() + " " + queue.peek() + " " + queue.remove());
        }
    }

    public static void clear(ArrayQueue queue) {
        queue.clear();
        System.out.println("Queue's size is " + queue.size() + ".");
    }

    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue();
        fill(queue);
        dump(queue);
        System.out.println("Queue filled.");
        fill(queue);
        clear(queue);
    }
}
