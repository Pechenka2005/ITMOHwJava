package queue;

public class ArrayQueueADT {
    private int size;
    private int first;
    private int last;
    private Object[] array = new Object[5];

    //pre: e != null && queue != null
    //post: (size' == size + 1 && ((first - 1) + array.length) % array.length && size <= 'array.length
    public static void push(ArrayQueueADT queue, Object e) {
        if(queue.last == queue.first && !isEmpty(queue)) {
            queue.array = copyArrayQueue(queue);
        }
        queue.first = ((queue.first - 1) + queue.array.length) % queue.array.length;
        queue.array[queue.first] = e;
        queue.size++;
    }
    // pre: size > 0 && queue != null
    // post: res == array[((last - 1) + array.length) % array.length]
    public static Object peek(ArrayQueueADT queue) {
        assert queue.size > 0;
        return queue.array[(( queue.last - 1) + queue.array.length) % queue.array.length];
    }
    // pre: size > 0 && queue != null
    // post: 'last == ((last - 1) + array.length) % array.length && res == array['last]
    // && 'size == size - 1
    public static Object remove(ArrayQueueADT queue) {
        Object x = peek(queue);
        queue.array[((queue.last - 1) + queue.array.length) % queue.array.length] = null;
        queue.last = ((queue.last - 1) + queue.array.length) % queue.array.length;
        queue.size--;
        return x;
    }
    //pre: size == array.length && queue != null
    //post: 'array.length == 2 * array.length && forall i = first ... array.length : 'array[i - first] == array[i] &&
    // forall i = 0 ... first - 1 : 'array[i - first + array.length] == array[i] && first == 0 && 'last == size
    private static Object[] copyArrayQueue(ArrayQueueADT queue) {
        Object[] temp = new Object[queue.array.length * 2];
        for(int i = queue.first; i < queue.array.length; i++) {
            temp[i - queue.first] = queue.array[i];
        }
        for(int i = 0; i < queue.first; i++) {
            temp[i - queue.first + queue.array.length] = queue.array[i];
        }
        queue.first = 0;
        queue.last = queue.size;
        return temp;
    }

    // pre: e != null && queue != null
    // post:  array[last++] = e && (size == array.length && 'array.length == array.length * 2 || size < array.length)
    // 'last == last % array.length && 'size == size + 1;
    public static void enqueue(ArrayQueueADT queue, Object e) {
        if(queue.last == queue.first && !isEmpty(queue)) {
            queue.array = copyArrayQueue(queue);
        }

        queue.array[queue.last++] = e;
        queue.last %= queue.array.length;
        queue.size++;
    }
    // pre: size > 0 && queue != null
    // post:res = array[first]
    public static Object element(ArrayQueueADT queue) {
        assert queue.size > 0;
        return queue.array[queue.first];
    }
    // pre: size > 0 && queue != null
    // post:  res = array[first] && array[first] = null && 'first == first % array.length && 'size == size - 1
    public static Object dequeue(ArrayQueueADT queue) {
        assert queue.size > 0;
        Object x = queue.array[queue.first];
        queue.array[queue.first++] = null;
        queue.first %= queue.array.length;
        queue.size--;
        return x;
    }
    // pre: queue != null
    // post: res == size
    public static int size(ArrayQueueADT queue) {
        return queue.size;
    }
    // pre: queue != null
    // post: (res == false && size > 0) || (res == false && size == 0)
    public static boolean isEmpty(ArrayQueueADT queue) {
        return queue.size == 0;
    }
    // pre: queue != null
    // post: array.length == 5 && forall i == 0 ... 4 : array[i] == null
    // && first == last == size == 0
    public static void clear(ArrayQueueADT queue) {
        queue.array = new Object[5];
        queue.first = queue.last = queue.size = 0;
    }

}