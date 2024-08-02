import java.util.Random;
import java.util.Scanner;

public class Game {
     int number;
     int inputNumber;
     int noOfGuesses = 0;
     int range;

    public int getNoOfGuesses() {
        return noOfGuesses;
    }

    public void setNoOfGuesses(int noOfGuesses) {
        this.noOfGuesses = noOfGuesses;
    }

    Game(int range) {
        Random rand = new Random();
        this.number = rand.nextInt(range) + 1;
        this.range = range;
    }

    void takeUserInput() {
        Scanner sc = new Scanner(System.in);
        boolean validInput = false;

        while (! validInput) {
            System.out.print("Enter your guessed number (1-" + range + "): ");
            if (sc.hasNextInt()) {
                inputNumber = sc.nextInt();
                if (inputNumber >= 1 && inputNumber <= range) {
                    validInput = true;
                } else {
                    System.out.println("Please enter a number within the range!");
                }
            } else {
                System.out.println("Invalid input! Please enter a number.");
                sc.next();
            }
        }
    }

    boolean isCorrectNumber() {
        noOfGuesses++;
        if (inputNumber == number) {
            System.out.format("Congratulations, You guessed it right! It was %d\nYou guessed it in %d attempts.\n", number, noOfGuesses);
            return true;
        } else if (inputNumber < number) {
            System.out.println("Too Low...");
        } else {
            System.out.println("Too High...");
        }
        return false;
    }
}

public class Number_Guessing_game {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean playAgain = true;

        while (playAgain) {
            System.out.println("Welcome to the Number Guessing Game!");
            System.out.println("Select Difficulty Level:");
            System.out.println("1. Easy (1-50)");
            System.out.println("2. Medium (1-100)");
            System.out.println("3. Hard (1-200)");

            int choice = sc.nextInt();
            int range = 100; // Default range

            switch (choice) {
                case 1:
                    range = 50;
                    break;
                case 2:
                    range = 100;
                    break;
                case 3:
                    range = 200;
                    break;
                default:
                    System.out.println("Invalid choice! Defaulting to Medium (1-100).");
            }

            Game g = new Game(range);
            boolean correct = false;

            while (!correct) {
                g.takeUserInput();
                correct = g.isCorrectNumber();
            }

            System.out.print("Do you want to play again? (yes/no): ");
            String response = sc.next();
            playAgain = response.equalsIgnoreCase("yes");
        }

        System.out.println("Thank you for playing! Goodbye.");
    }
}


