import java.util.*;

public class casestudy6 {

    // Activity Selection (Greedy)
    static void activitySelection(int[] start, int[] finish) {

        System.out.println("\n===== Activity Selection =====");

        int n = finish.length;

        System.out.print("Selected Activities: ");

        int i = 0;
        System.out.print("A" + (i + 1) + " ");

        for (int j = 1; j < n; j++) {

            if (start[j] >= finish[i]) {

                System.out.print("A" + (j + 1) + " ");
                i = j;
            }
        }

        System.out.println();
    }

    // 0/1 Knapsack
    static int knapsack(int W, int[] wt, int[] val, int n) {

        int[][] dp = new int[n + 1][W + 1];

        for (int i = 0; i <= n; i++) {

            for (int w = 0; w <= W; w++) {

                if (i == 0 || w == 0)
                    dp[i][w] = 0;

                else if (wt[i - 1] <= w)

                    dp[i][w] = Math.max(
                            val[i - 1] + dp[i - 1][w - wt[i - 1]],
                            dp[i - 1][w]);

                else
                    dp[i][w] = dp[i - 1][w];
            }
        }

        return dp[n][W];
    }

    // Longest Common Subsequence
    static String lcs(String X, String Y) {

        int m = X.length();
        int n = Y.length();

        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {

            for (int j = 1; j <= n; j++) {

                if (X.charAt(i - 1) == Y.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1] + 1;

                else
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }

        StringBuilder lcs = new StringBuilder();

        int i = m;
        int j = n;

        while (i > 0 && j > 0) {

            if (X.charAt(i - 1) == Y.charAt(j - 1)) {

                lcs.append(X.charAt(i - 1));
                i--;
                j--;
            }

            else if (dp[i - 1][j] > dp[i][j - 1])
                i--;

            else
                j--;
        }

        return lcs.reverse().toString();
    }

    // Matrix Chain Multiplication
    static int matrixChainMultiplication(int[] p) {

        int n = p.length;

        int[][] m = new int[n][n];

        for (int L = 2; L < n; L++) {

            for (int i = 1; i < n - L + 1; i++) {

                int j = i + L - 1;

                m[i][j] = Integer.MAX_VALUE;

                for (int k = i; k < j; k++) {

                    int q = m[i][k] + m[k + 1][j]
                            + p[i - 1] * p[k] * p[j];

                    if (q < m[i][j])
                        m[i][j] = q;
                }
            }
        }

        return m[1][n - 1];
    }

    public static void main(String[] args) {

        System.out.println("===== Meesho Resource Optimization System =====");

        // Activity Selection
        int[] start = {1, 3, 0, 5, 8, 5};
        int[] finish = {2, 4, 6, 7, 9, 9};

        activitySelection(start, finish);

        // Knapsack
        int[] profit = {60, 100, 120};
        int[] weight = {10, 20, 30};
        int capacity = 50;

        int maxProfit = knapsack(
                capacity,
                weight,
                profit,
                profit.length
        );

        System.out.println("\n===== 0/1 Knapsack =====");
        System.out.println("Maximum Profit = " + maxProfit);

        // LCS
        String s1 = "ABCDGH";
        String s2 = "AEDFHR";

        String result = lcs(s1, s2);

        System.out.println("\n===== Longest Common Subsequence =====");
        System.out.println("Customer Pattern 1: " + s1);
        System.out.println("Customer Pattern 2: " + s2);
        System.out.println("LCS = " + result);

        // Matrix Chain Multiplication
        int[] dimensions = {40, 20, 30, 10, 30};

        int minCost =
                matrixChainMultiplication(dimensions);

        System.out.println("\n===== Matrix Chain Multiplication =====");
        System.out.println(
                "Minimum Multiplications Required = "
                        + minCost);

        System.out.println(
                "\nDynamic Programming Optimization Completed Successfully.");
    }
}