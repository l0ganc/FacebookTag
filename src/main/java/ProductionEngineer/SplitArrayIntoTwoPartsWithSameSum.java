package ProductionEngineer;

public class SplitArrayIntoTwoPartsWithSameSum {
    /**
     * Given an array of integers greater than zero, find if it is possible to split it in two subarrays (without reordering the elements), such that the sum of the two subarrays is the same. Print the two subarrays.
     *
     * Examples :
     *
     * Input : Arr[] = { 1 , 2 , 3 , 4 , 5 , 5  }
     * Output :  { 1 2 3 4 }
     *           { 5 , 5 }
     *
     * Input : Arr[] = { 4, 1, 2, 3 }
     * Output : {4 1}
     *          {2 3}
     *
     * Input : Arr[] = { 4, 3, 2, 1}
     * Output : Not Possible
     */

    public static int findSplitPoint(int arr[], int n) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }

        int preSum = 0;
        for (int i = 0; i < arr.length; i++) {
            preSum += arr[i];
            if (preSum * 2 == sum) {
                return i;
            }
        }
        return -1;
    }

    public static void printTwoParts(int arr[], int n) {
        int point = findSplitPoint(arr, n);
        if (point == -1 || point == arr.length - 1) {
            return;
        }

        for (int i = 0; i < n; i++) {
            if (i == point + 1) {
                System.out.println();
            }
            System.out.print(arr[i] + " ");
        }
    }

    public static void main(String[] args) {
        int arr[] = {1 , 2 , 3 , 4 , 5 , 5 };
        int n = arr.length;

        printTwoParts(arr, n);
    }
}
