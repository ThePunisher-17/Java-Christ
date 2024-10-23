public class ShareTrade {
    static int maxProfit = 0;

    static int findMaxProfit(int[] prices) 
    {
        if (prices == null || prices.length < 2) 
        {
            return 0;
        }

        int n = prices.length;
        int[] first_transaction_profit = new int[n];
        int[] second_transaction_profit = new int[n];

        int min_price = prices[0];
        for (int i = 1; i < n; i++) 
        {
            first_transaction_profit[i] = Math.max(first_transaction_profit[i - 1], prices[i] - min_price);
            min_price = Math.min(min_price, prices[i]);
        }
        // System.out.println(min_price);

        int max_price = prices[n - 1];
        for (int i = n - 2; i >= 0; i--) 
        {
            second_transaction_profit[i] = Math.max(second_transaction_profit[i + 1], max_price - prices[i]);
            max_price = Math.max(max_price, prices[i]);
        }
        // System.out.println(max_price);

        for (int i = 0; i < n; i++) 
        {
            maxProfit = Math.max(maxProfit, first_transaction_profit[i] + second_transaction_profit[i]);
        }

        return maxProfit;
    }

    public static void main(String[] args) 
    {
        int[] prices1 = { 10, 22, 5, 75, 65, 80 };
        System.out.println("Max Profit: " + ShareTrade.findMaxProfit(prices1));

        int[] prices2 = { 2, 30, 15, 10, 8, 25, 80 };
        System.out.println("Max Profit: " + ShareTrade.findMaxProfit(prices2));
    }
}