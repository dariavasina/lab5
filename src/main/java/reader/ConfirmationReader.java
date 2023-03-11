package reader;

import java.util.Scanner;

public class ConfirmationReader {
    public boolean checkTheDesireToEnter(Scanner scanner, String valueName) {
        System.out.printf("Do you want to enter %s? (y/n): ", valueName);
        String input = scanner.nextLine().strip();
        System.out.println(input);
        return input.equals("y");
    }
}
