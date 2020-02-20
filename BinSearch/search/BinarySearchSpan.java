package search;

public class BinarySearchSpan {
    public static void main(String[] args) {

        int key = Integer.parseInt(args[0]);
        int numArr[] = new int[args.length - 1];
        for (int i = 1; i < args.length; i++) {
            numArr[i - 1] = Integer.parseInt(args[i]);
        }
        int length = numArr.length;
        PairBorders answer = new PairBorders(0,0);
        //numArr.length == ?  && key == ?
        if(length == 0 || key > numArr[0]) {
            //numArr.length == 0  || key > numArr[0]
            answer.leftBorder = 0;
            answer.size = 0;

        } else {
            //numArr.length > 0 && key <= numArr[0];
            answer.leftBorder = BinarySearchSpanRec(numArr, key, 0, length -1, length, new LessOrEqual());

            if(answer.leftBorder == length || numArr[answer.leftBorder] != key) {
                answer.size = 0;

            } else {
                answer.size = BinarySearchSpanRec(numArr, key, 0, length - 1, length, new Less()) - answer.leftBorder;
            }
        }

        System.out.println(answer.leftBorder + " " + answer.size);
    }




    public static int BinarySearchSpanIter(int[] arr, int key, Compare func) {
        int l = 0;
        int answer = arr.length;
        int r = arr.length - 1;
        int mid;
        while (r >= l) {
            mid = (l + r) / 2;
            if (func.compare(arr[mid], key)) {
                r = mid - 1;
                answer = mid;
            } else {
                l = mid + 1;
            }
        }
        return answer;
    }

    public static int BinarySearchSpanRec(int [] arr, int key, int l, int r, int answer, Compare func) {
        int mid = (l + r) / 2;
        if(r < l) {
            return answer;
        }
        if(func.compare(arr[mid], key)) {
            return BinarySearchSpanRec(arr, key, l, mid -1, mid, func);
        }
        return BinarySearchSpanRec(arr, key, mid +1, r,  answer, func);
    }



}
