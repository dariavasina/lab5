package reader;

import java.util.Scanner;

public class ConfirmationReader {
    public static boolean checkTheDesireToEnter(Scanner scanner, String valueName) {
        System.out.printf("Do you want to enter %s? (y/n): ", valueName);
        String input = scanner.nextLine().strip();
        return input.equals("y");
    }

    public static boolean checkTheDesireToClear(Scanner scanner) {
        System.out.println("Are you sure you want to delete the collection? (y/n): ");
        String input = scanner.nextLine().strip();
        return input.equals("y");
    }
}
