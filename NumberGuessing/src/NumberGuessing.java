import java.util.Random;
import java.util.Scanner;

public class NumberGuessing {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();

        boolean playAgain = true;

        while (playAgain) {
            // Difficulty select karna
            System.out.println("\nChoose Difficulty Level:");
            System.out.println("1. Easy (1–50)");
            System.out.println("2. Medium (1–100)");
            System.out.println("3. Hard (1–500)");
            int choice = scanner.nextInt();

            int maxNumber = 100;
            int attempts = 7;

            if (choice == 1) {
                maxNumber = 50;
                attempts = 10;
            } else if (choice == 2) {
                maxNumber = 100;
                attempts = 7;
            } else if (choice == 3) {
                maxNumber = 500;
                attempts = 5;
            }

            int numberToGuess = rand.nextInt(maxNumber) + 1;

            System.out.println("\nGame Start! Guess the number between 1 and " + maxNumber);
            System.out.println("You have " + attempts + " attempts.");

            int tries = 0;
            boolean guessed = false;

            while (tries < attempts) {
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();
                tries++;

                if (userGuess == numberToGuess) {
                    System.out.println("🎉 Congratulations! You guessed it in " + tries + " tries.");
                    guessed = true;
                    break;
                } else if (userGuess < numberToGuess) {
                    System.out.println("Too low!");
                } else {
                    System.out.println("Too high!");
                }
            }

            if (!guessed) {
                System.out.println("❌ Game Over! The correct number was: " + numberToGuess);
            }

            // Play again option
            System.out.print("\nDo you want to play again? (yes/no): ");
            String response = scanner.next().toLowerCase();
            playAgain = response.equals("yes");
        }

        System.out.println("\nThanks for playing! Goodbye 👋");
        scanner.close();
    }
}
