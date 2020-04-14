package queue;


public class ArrayQueue {
    // Inv: forall i :[0 ... array.length] && ()
    private int size;
    private int first;
    private int last;
    private Object[] array = new Object[5];

    //pre: e != null
    //post: size' == size + 1 && ((first - 1) + array.length) % array.length && size <= 'array.length
    public void push(Object e) {
        if(last == first && !isEmpty()) {
            array = copyArrayQueue();
        }
        first = ((first - 1) + array.length) % array.length;
        array[first] = e;
        size++;
    }
    // pre: size > 0
    // post: res == array[((last - 1) + array.length) % array.length]
    public Object peek() {
        assert size > 0;
        return array[((last - 1) + array.length) % array.length];
    }
    // pre: size > 0
    // post: 'last == ((last - 1) + array.length) % array.length && res == array['last]
    // && 'size == size - 1
    public Object remove() {
        Object x = peek();
        array[((last - 1) + array.length) % array.length] = null;
        last = ((last - 1) + array.length) % array.length;
        size--;
        return x;
    }
    //pre: size == array.length
    //post: 'array.length == 2 * array.length && forall i = first ... array.length : 'array[i - first] == array[i] &&
    // forall i = 0 ... first - 1 : 'array[i - first + array.length] == array[i] && first == 0 && 'last == size
    private Object[] copyArrayQueue() {
        Object[] temp = new Object[array.length * 2];
        for(int i = first; i < array.length; i++) {
            temp[i - first] = array[i];
        }
        for(int i = 0; i < first; i++) {
            temp[i - first + array.length] = array[i];
        }
        first = 0;
        last = size;
        return temp;
    }

    // pre: e != null
    // post:  array[last + 1] = e && (size == array.length && 'array.length == array.length * 2 || size < array.length)
    // 'last == last % array.length && 'size == size + 1;
    public void enqueue(Object e) {
        if(last == first && !isEmpty()) {
            array = copyArrayQueue();
        }

        array[last++] = e;
        last %= array.length;
        size++;
    }
    // pre: size > 0
    // post:res = array[first]
    public Object element() {
        assert size > 0;
        return array[first];
    }

    // pre: size > 0
    // post:  res = array[first] && array[first] = null && 'first == first % array.length && 'size == size - 1
    public Object dequeue() {
        assert size > 0;
        Object x = array[first];
        array[first++] = null;
        first %= array.length;
        size--;
        return x;
    }
    // pre: true
    // post: res == size
    public int size() {
        return size;
    }
    // pre: true
    // post: (res == false && size > 0) || (res == false && size == 0)
    public boolean isEmpty() {
        return size == 0;
    }


    public void clear() {
       array = new Object[5];
       first = last = size = 0;
    }

}
