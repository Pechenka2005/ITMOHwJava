package search;

public class BinarySearch {
    public static void main(String[] args) {

        int key = Integer.parseInt(args[0]);
        int numArr[] = new int[args.length - 1];
        for (int i = 1; i < args.length; i++) {
            numArr[i-1] = Integer.parseInt(args[i]);
        }
        int answer;
        if(numArr.length == 0 || key > numArr[0]) {
            answer = 0;
        }
        else {
            if(key < numArr[numArr.length - 1]) {
                answer = numArr.length;
            }
            else{
                answer = BinarySearchRec(numArr, key, 0, numArr.length-1, numArr.length);
            }

        }

        System.out.println(answer);
    }


    public static int BinarySearchIter(int[] arr, int key, int l, int r) {
        int answer = arr.length;
        int mid;
        while (r >= l) {
            mid = (l + r) / 2;
            if (arr[mid] <= key) {
                answer = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }

        }
        return answer;
    }
    public static int BinarySearchRec(int [] arr, int key, int l, int r, int answer) {
        int mid = (l + r) / 2;
        if(r < l) {
            return answer;
        }
        if(arr[mid] <= key) {
            return BinarySearchRec(arr, key, l, mid -1, mid);
        }
        return BinarySearchRec(arr, key, mid +1, r,  answer);
    }

}