# Robber Simulation in Java

## Overview

This Java project simulates different types of robbing strategies implemented using an abstract class and inheritance. The project includes a generic `Robber` class with various methods to calculate the optimal looting strategy for different house configurations (e.g., row houses, round houses, square houses). 

The main implementation is provided in the `JAVAProfessionalRobber` class, which extends the abstract `Robber` class, providing specific implementations for each looting scenario using dynamic programming techniques.

## Classes

### 1. `Robber` (Abstract Class)
The `Robber` class defines an abstract template for different types of robbers. It includes the following methods:

- **`RobbingClass()`** - An abstract method specifying the type of robber class. 
- **`RowHouses(int[] houses)`** - An abstract method for calculating the maximum loot from a row of houses.
- **`RoundHouses(int[] houses)`** - An abstract method for looting from roundhouses where the first and last houses are adjacent.
- **`SquareHouse(int[] houses)`** - An abstract method for looting from square houses.
- **`MultiHouseBuilding(int[] houses)`** - An abstract method for looting in multi-house buildings.
- **`MachineLearning()`** - A default method displaying the robber's interest in Machine Learning. This method can be overridden in subclasses.

### 2. `JAVAProfessionalRobber` (Concrete Class)
The `JAVAProfessionalRobber` class is a concrete implementation of the `Robber` abstract class. It provides specific implementations for the abstract methods using dynamic programming approaches:

- **`RobbingClass()`** - Displays the type of education/robber ("MScAI&ML").
- **`RowHouses(int[] houses)`** - Uses a dynamic programming approach to calculate the maximum possible loot for a row of houses.
- **`RoundHouses(int[] houses)`** - Handles the challenge of looting round houses, where the first and last houses are adjacent.
- **`SquareHouse(int[] houses)`** - Implements looting for square houses; treated similarly to row houses.
- **`MultiHouseBuilding(int[] houses)`** - Handles looting scenarios in multi-house buildings, assuming constraints similar to row houses.

## Usage

To run the code, ensure you have a Java environment set up. Here’s a step-by-step guide:

1. **Clone the Repository:**
   ```bash
   git clone https://github.com/your-username/robber-simulation.git
   ```
   
2. **Compile the Code:**
   Navigate to the directory containing the `.java` files and compile them using:
   ```bash
   javac Rober.java
   ```
   
3. **Run the Program:**
   Execute the program using:
   ```bash
   java Rober
   ```

## Example

Below is an example of how you can use the `JAVAProfessionalRobber` class to calculate the maximum loot:

```java
public class Main {
    public static void main(String[] args) {
        int[] rowHouses = {2, 7, 9, 3, 1};
        int[] roundHouses = {2, 3, 2};
        
        JAVAProfessionalRobber robber = new JAVAProfessionalRobber();
        
        System.out.println("Maximum loot from row houses: " + robber.RowHouses(rowHouses));
        System.out.println("Maximum loot from round houses: " + robber.RoundHouses(roundHouses));
        
        robber.MachineLearning(); // Displays interest in Machine Learning
    }
}
```

## Dynamic Programming Explanation

The core looting strategy for each scenario uses a dynamic programming approach:
- For **row houses**, the algorithm iterates through each house, deciding whether to rob it or not based on previous loot.
- For **round houses**, the problem is split into two subproblems due to the circular nature, and the best result is chosen.
- For **square houses** and **multi-house buildings**, the current implementation treats them similarly to row houses for simplicity.
