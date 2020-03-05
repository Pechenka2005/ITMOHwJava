package queue;

public class MyArrayQueueModuleTest {
    public static void fill() {
        for (int i = 0; i < 130; i++) {
            ArrayQueueModule.push(i);
        }
    }

    public static void dump() {
        while (!ArrayQueueModule.isEmpty()) {
            System.out.println(ArrayQueueModule.size() + " " +
                    ArrayQueueModule.peek() + " " + ArrayQueueModule.remove());
        }
    }

    public static void clear() {
        ArrayQueueModule.clear();
        System.out.println("Queue's size is " + ArrayQueueModule.size() + ".");
    }

    public static void main(String[] args) {
        fill();
        dump();
        System.out.println("Queue filled");
        fill();
        clear();
    }
}
