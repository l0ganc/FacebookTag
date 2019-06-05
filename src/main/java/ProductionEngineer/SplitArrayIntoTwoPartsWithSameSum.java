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

    // 暴力解
    public static int findSplitPoint2(int[] arr, int n) {
        int leftSum = 0;

        for (int i = 0; i < n; i++) {
            leftSum += arr[i];
            int rightSum = 0;
            for (int j = i + 1; j < n; j++) {
                rightSum += arr[j];
            }
            if (leftSum == rightSum) {
                return i + 1;
            }
        }
        return -1;
    }

    // one pass with sorted array and
    public static int canSplit(int[] nums) {
        int leftSum = 0, rightSum = 0, i, j;
        if(nums.length == 1)
            return -1;
        for(i=0, j=nums.length-1; i<=j ;){
            if(leftSum <= rightSum){
                leftSum+=nums[i];
                i++;
            }else{
                rightSum+=nums[j];
                j--;
            }
            if (leftSum == rightSum) {
                return i;
            }
        }
        return -1;
    }

    public static void printTwoParts(int arr[], int n) {
        int point = findSplitPoint2(arr, n);
        if (point == -1 || point == arr.length - 1) {
            return;
        }

        for (int i = 0; i < n; i++) {
            if (i == point) {
                System.out.println();
            }
            System.out.print(arr[i] + " ");
        }
    }


    public static void main(String[] args) {
        int arr[] = {1 , 2 , 3 , 4 , 5 , 5 };
        int arr1[] = {4, 1, 2, 3};
        int n = arr.length;

        //printTwoParts(arr, n);
        System.out.println();

        System.out.println(canSplit(arr));
        System.out.println(canSplit(new int[]{1, 2}));
        System.out.println(canSplit(new int[]{1, 4, 3}));
        System.out.println(canSplit(new int[]{4, 1, 2, 3}));
        System.out.println(canSplit(new int[]{4, 3, 2, 1}));
    }
}
