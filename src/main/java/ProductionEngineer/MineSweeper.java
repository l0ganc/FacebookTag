package ProductionEngineer;

import java.util.Arrays;
import java.util.Random;

public class MineSweeper {
    /**
     * 比如不停的随机到有雷的地方然后就得跳过继续循环，虽然时间一久终究不会永远随机到已经有雷的地方（也就conflict）
     * 然后他提示我是不是有更好的方法，我说是有，不过得增加空间复杂度，用不放回抽取，用一个ArrayList把所有位置坐标放进去，
     * 每抽取一次就把这个位置从ArrayList里面删掉，那么下次就肯定不会再抽到了，
     * 保证worst case就是雷的个数，并且这个数永远< N/2. 然后他说对，这就是他要的答案
     */

    public static int[][] getBoard(int m, int n, int k) {
        Random rdm = new Random();
        int[][] res = new int[m][n];

        int count = 0;

        while (true) {
            int next = rdm.nextInt(m * n);
            int row = next / n;
            int col = next % n;
            if (res[row][col] == 0) {
                res[row][col] = 1;
                count++;
            }

            if (count == k) {
                break;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        int[][] res = getBoard(5, 3, 9);
        for (int[] row : res) {
            for (int num : row) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }
}
