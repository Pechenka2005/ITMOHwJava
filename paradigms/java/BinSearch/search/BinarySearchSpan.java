package search;

import java.util.Random;

public class BinarySearchSpan {
    // pre: args.length > 0 forall: i in [1 ... args.length - 2]: arr[i] >= arr[i + 1] && forall i : args[i] == int
    // post: (answer.leftBorder == i,  min i: key == args[i + 1] && args[answer.leftBorder + answer.size] == key) ||
    // answer.leftBorder == i,  min i: key < args[i + 1] && asnwer.size == 0
    public static void main(String[] args) {

        int key = Integer.parseInt(args[0]);

        int numArr[] = new int[args.length - 1];
        for (int i = 1; i < args.length; i++) {
            numArr[i - 1] = Integer.parseInt(args[i]);
        }

        int length = numArr.length;
        PairBorders answer = new PairBorders(0, 0);
        //numArr.length == ?  && key == ?

        // forall: i [1 ... args.length - 2]: arr[i] >= arr[i + 1]
        answer.leftBorder = binarySearchSpanIter(numArr, key, new LessOrEqual());
        //answer.leftBorder == i,  min i: key <= num[i]
        if (answer.leftBorder == length || numArr[answer.leftBorder] != key) {
            answer.size = 0;

        } else {
            //answer.leftBorder == i,  min i: key <= num[i]
            // numArr[numArr.length-1] <= key
            answer.size = binarySearchSpanRec(numArr, key, 0, length - 1, length, new Less()) - answer.leftBorder;
            //answer.leftBorder == i,  min i: key <= num[i] && answer.size = max j: numArr[j] < key
        }

        //(answer.leftBorder == i,  min i: key <= numArr[i]) && answer.leftBorder + answer.size == max j: numArr[j] < key
        System.out.println(answer.leftBorder + " " + answer.size);
    }


    //pre: forall i : [0, array.length - 2] :func.compare(array[i], key) <= func.compare(array[i + 1], key)
    //post: answer == (min: func.compare(arr[i], key) && key <= arr[arr.length - 1] || key > arr[arr.length - 1] && ) == true
    // && r < l &&
    public static int binarySearchSpanIter(int[] arr, int key, Compare func) {
        int l = 0;
        //l == 0
        int answer = arr.length;
        //l == 0 && answer == arr.length
        int r = arr.length - 1;
        // l == 0 && answer == arr.length && r == arr.length - 1(последний элемент массива arr)
        int mid;
        // l = 0 && answer = arr.length && r = arr.length - 1;
        // pre: r >= l
        // post: answer = i, где i  == min i : func.compare(arr[i], key) == true && r < l
        // inv: answer == min i : [0.. arr.length - 1]/[l..r]: func.compare(arr[i], key) == true
        while (r >= l) {
            mid = (l + r) / 2;
            // r >= l && mid == (l + r) / 2 && mid >= l && mid <= r
            if (func.compare(arr[mid], key)) {
                // func.compare(arr[mid], key) == true && r >= l
                r = mid - 1;
                // func.compare(arr[mid], key) == true && r >= l && r == mid - 1
                answer = mid;
                // func.compare(arr[mid], key) == true && r >= l && answer == (l + r) / 2 && r == mid - 1
            } else {

                l = mid + 1;
                // func.compare(arr[mid], key) == false && answer - прежний && l == mid - 1
            }
            // answer == min i: [0.. arr.length - 1]/[l..r]: func.compare(arr[i], key) == true
        }
        // r < l
        // answer == min i: [0.. arr.length - 1]: func.compare(arr[i], key) == true
        return answer;
    }

    //pre: forall i : [0, array.length - 2] :func.compare(array[i], key) <= func.compare(array[i + 1], key)
    //post (answer = i: min: func.compare(arr[i], key) == true) && r < l
    public static int binarySearchSpanRec(int[] arr, int key, int l, int r, int answer, Compare func) {
        int mid = (l + r) / 2;
        // forall : i == 0 ... arr.length - 2 : arr[i] >= arr[i + 1] &&
        // mid == (l + r) / 2 && answer == min i: [0.. arr.length - 1]/[l..r]: func.compare(arr[i], key) == true
        if (r < l) {
            // r < l : answer == min i: [0.. arr.length - 1]/[пустой отрезок]: func.compare(arr[i], key) == true
            return answer;

        }
        // arr.length > 0 && key <= arr[0] && для всех i == 0 ... arr.length - 2 : arr[i] >= arr[i + 1] &&
        // mid == (l + r) / 2 &&
        // r >= l
        if (func.compare(arr[mid], key)) {
            //func.compare(arr[mid], key) == true : mid == [0.. arr.length - 1]/[l.. mid - 1]: func.compare(arr[i], key) == true
            return binarySearchSpanRec(arr, key, l, mid - 1, mid, func);
        }
        //func.compare(arr[mid], key) == false : mid == [0.. arr.length - 1]/[mid + 1..r]: func.compare(arr[i], key) == true
        return binarySearchSpanRec(arr, key, mid + 1, r, answer, func);

    }


}
