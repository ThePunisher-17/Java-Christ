import java.util.Scanner;

abstract class Robber {
    // Abstract method
    public abstract void RobbingClass();

    // Abstract methods for different house types
    public abstract int RowHouses(int[] houses);
    public abstract int RoundHouses(int[] houses);
    public abstract int SquareHouse(int[] houses);
    public abstract int MultiHouseBuilding(int[] houses);

    // Default method
    public void MachineLearning() {
        System.out.println("I love MachineLearning.");
    }
}

class JAVAProfessionalRobber extends Robber {
    @Override
    public void RobbingClass() {
        System.out.println("MScAI&ML");
    }

    @Override
    public int RowHouses(int[] houses) {
        if (houses.length == 0) return 0;
        if (houses.length == 1) return houses[0];
        int prev1 = 0, prev2 = 0;
        for (int house : houses) {
            int temp = prev1;
            prev1 = Math.max(prev1, prev2 + house);
            prev2 = temp;
        }
        return prev1;
    }

    @Override
    public int RoundHouses(int[] houses) {
        if (houses.length == 0) return 0;
        if (houses.length == 1) return houses[0];
        return Math.max(robHelper(houses, 0, houses.length - 2), robHelper(houses, 1, houses.length - 1));
    }

    private int robHelper(int[] houses, int start, int end) {
        int prev1 = 0, prev2 = 0;
        for (int i = start; i <= end; i++) {
            int temp = prev1;
            prev1 = Math.max(prev1, prev2 + houses[i]);
            prev2 = temp;
        }
        return prev1;
    }

    @Override
    public int SquareHouse(int[] houses) {
        return RowHouses(houses);  // RowHouses logic applies as well
    }

    @Override
    public int MultiHouseBuilding(int[] houses) {
        return RowHouses(houses);  // RowHouses logic applies as well
    }
}

public class Rober {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        JAVAProfessionalRobber robber = new JAVAProfessionalRobber();

        boolean continueProgram = true;

        // Demonstrating default methods
        robber.RobbingClass();
        robber.MachineLearning();

        while (continueProgram) {
            System.out.println("\nSelect the type of house to calculate maximum rob amount:");
            System.out.println("1. Row Houses");
            System.out.println("2. Round Houses");
            System.out.println("3. Square House");
            System.out.println("4. Multi-House Building");
            System.out.println("5. Exit");

            int choice = scanner.nextInt();

            if (choice == 5) {
                System.out.println("Exiting program.");
                break;
            }

            System.out.println("Enter the number of houses:");
            int n = scanner.nextInt();
            int[] houses = new int[n];
            System.out.println("Enter the money in each house:");
            for (int i = 0; i < n; i++) {
                houses[i] = scanner.nextInt();
            }

            int maxMoney = 0;
            switch (choice) {
                case 1:
                    maxMoney = robber.RowHouses(houses);
                    System.out.println("Max money robbed from Row Houses: " + maxMoney);
                    break;
                case 2:
                    maxMoney = robber.RoundHouses(houses);
                    System.out.println("Max money robbed from Round Houses: " + maxMoney);
                    break;
                case 3:
                    maxMoney = robber.SquareHouse(houses);
                    System.out.println("Max money robbed from Square House: " + maxMoney);
                    break;
                case 4:
                    maxMoney = robber.MultiHouseBuilding(houses);
                    System.out.println("Max money robbed from Multi-House Building: " + maxMoney);
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }

            // Asking user if they want to choose another house type
            System.out.println("\nDo you want to choose another house type? (yes/no)");
            String response = scanner.next();
            if (!response.equalsIgnoreCase("yes")) {
                continueProgram = false;
            }
        }

        scanner.close();
    }
}
