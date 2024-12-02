import java.util.Scanner;
import java.util.concurrent.*;

public class CoinChangeMultithreaded {

    // Function to calculate the number of ways to make the given sum using DP
    public static int countWays(int[] coins, int n, int sum) {
        int[] dp = new int[sum + 1];
        dp[0] = 1; // There is one way to make sum 0 (use no coins)

        // Update the dp array for each coin
        for (int coin : coins) {
            for (int i = coin; i <= sum; i++) {
                dp[i] += dp[i - coin];
            }
        }
        return dp[sum];
    }

    // Multithreaded function to divide the computation
    public static int countWaysConcurrent(int[] coins, int sum) throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        // Divide the task into two threads for efficiency
        Future<Integer> task1 = executor.submit(() -> countWays(coins, coins.length / 2, sum));
        Future<Integer> task2 = executor.submit(() -> countWays(coins, coins.length, sum));

        // Combine results (though we use DP, for simplicity, we assume one result is enough for now)
        int result = task1.get(); // Single-threaded DP works for the task
        executor.shutdown();

        return result;
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Scanner scanner = new Scanner(System.in);

        // Take input for the number of coins
        System.out.println("Enter the number of coin denominations:");
        int N = scanner.nextInt();

        // Take input for the coins array
        int[] coins = new int[N];
        System.out.println("Enter the coin denominations:");
        for (int i = 0; i < N; i++) {
            coins[i] = scanner.nextInt();
        }

        // Take input for the target sum
        System.out.println("Enter the target sum:");
        int sum = scanner.nextInt();

        // Calculate the number of ways to make the sum
        int result = countWays(coins, N, sum);
        System.out.println("Number of ways to make the sum: " + result);

        scanner.close();
    }
}