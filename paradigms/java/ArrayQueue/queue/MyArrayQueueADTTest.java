package queue;

public class MyArrayQueueADTTest {
    public static void fill(ArrayQueueADT queue) {
        for (int i = 0; i < 30; i++) {
            ArrayQueueADT.push(queue, i);
        }
    }

    public static void dump(ArrayQueueADT queue) {
        while (!ArrayQueueADT.isEmpty(queue)) {
            System.out.println(ArrayQueueADT.size(queue) + " " +
                    ArrayQueueADT.peek(queue) + " " + ArrayQueueADT.remove(queue));
        }
    }

    public static void clear(ArrayQueueADT queue) {
        ArrayQueueADT.clear(queue);
        System.out.println("Queue's size is " + ArrayQueueADT.size(queue) + ".");
    }

    public static void main(String[] args) {
        ArrayQueueADT queue = new ArrayQueueADT();
        fill(queue);
        dump(queue);
        System.out.println("Queue filled");
        fill(queue);
        clear(queue);
    }
}
