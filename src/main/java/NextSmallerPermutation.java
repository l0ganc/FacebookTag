public class NextSmallerPermutation {
    /**
     * 给一串只包含数字的字符串，要求返回一个比当前字符串表示的数小的最大的一个字符串
     *
     * 例如 "9263" ， return "9236"
     */

    public String nextPermutation(String a) {
        if (a == null || a.length() == 0) {
            return "";
        }

        char[] arr = a.toCharArray();

        int replace = arr.length - 2;
        while (replace >= 0) {
            if (arr[replace] > arr[replace + 1]) {
                break;
            }
            replace--;
        }

        if (replace < 0) {
            // corner case: "12345"
            swap(arr, 0, arr.length - 1);
            return new String(arr);
        }

        // 交换arr[replace] 与 arr[replace+1]
        char temp = arr[replace];
        arr[replace] = arr[replace + 1];
        arr[replace + 1] = temp;

        swap(arr, replace + 1, arr.length - 1);

        return new String(arr);
    }

    private static void swap(char[] nums, int i, int j) {
        while (i < j) {
            char temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
            i++;
            j--;
        }
    }

    public static void main(String[] args) {
        NextSmallerPermutation obj = new NextSmallerPermutation();
        System.out.println(obj.nextPermutation("921456"));
        System.out.println(obj.nextPermutation("9263"));
        System.out.println(obj.nextPermutation("12345"));
        System.out.println(obj.nextPermutation("92641"));
    }
}
