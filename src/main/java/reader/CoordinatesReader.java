package reader;

import data.Coordinates;
import exceptions.InvalidInputException;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CoordinatesReader {
    public static Double readX(Scanner scanner) throws InvalidInputException {
        double x;
        System.out.print("Enter x coordinate: ");
        try {
            String input = scanner.nextLine();
            x = Double.parseDouble(input);
        } catch (NumberFormatException e) {
            throw new InvalidInputException("x coordinate is a number, please try again\n");
        }
        return x;
    }

    public static int readY(Scanner scanner) throws InvalidInputException {
        int y;
        System.out.print("Enter y coordinate: ");
        try {
            String input = scanner.nextLine();
            y = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new InvalidInputException("y coordinate is a number, please try again\n");
        }
        return y;
    }

    public Coordinates readCoordinates(Scanner scanner) {
        Double x;
        while (true) {
            try {
                x = readX(scanner);
                break;
            } catch (InvalidInputException e) {
                System.out.print(e.getMessage());
            }
        }

        boolean answer = ConfirmationReader.checkTheDesireToEnter(scanner, "y coordinate");
        if (answer) {
            int y;
            while (true) {
                try {
                    y = readY(scanner);
                    return new Coordinates(x, y);
                } catch (InvalidInputException e) {
                    System.out.print(e.getMessage());
                }
            }
        }
        return new Coordinates(x);
    }
}
