package reader;

import data.Coordinates;
import exceptions.InvalidInputException;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CoordinatesReader {
    public static Double readX(Scanner scanner) throws InvalidInputException {
        double x;
        try {
            String input = scanner.nextLine();
            x = Double.parseDouble(input);
        }
        catch (InputMismatchException e) {
            throw new InvalidInputException("x coordinate is a number, please try again");
        }
        return x;
    }

    public static int readY(Scanner scanner) throws InvalidInputException {
        int y;
        try {
            String input = scanner.nextLine();
            y = Integer.parseInt(input);
        }
        catch (InputMismatchException e) {
            throw new InvalidInputException("y coordinate is a number, please try again");
        }
        return y;
    }

    public Coordinates readCoordinates(Scanner scanner) throws InvalidInputException{
        Double x;
        x = readX(scanner);
        int y;
        boolean answer = ConfirmationReader.checkTheDesireToEnter(scanner, "y coordinate");
        if (answer) {
            y = readY(scanner);
            return new Coordinates(x, y);
        }
        return new Coordinates(x);
    }
}
