package ProductionEngineer;

import java.util.Arrays;
import java.util.Random;

public class MineSweeper {

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
