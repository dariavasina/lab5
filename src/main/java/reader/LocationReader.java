package reader;

import data.Location;
import exceptions.InvalidInputException;

import java.util.InputMismatchException;
import java.util.Scanner;

public class LocationReader {
    public static Double readX(Scanner scanner) throws InvalidInputException {
        double x;
        try {
            x = scanner.nextDouble();
        }
        catch (InputMismatchException e) {
            throw new InvalidInputException("x coordinate is a number, please try again");
        }
        return x;
    }

    public static Float readY(Scanner scanner) throws InvalidInputException {
        float y;
        try {
            y = scanner.nextFloat();
        }
        catch (InputMismatchException e) {
            throw new InvalidInputException("y coordinate is a number, please try again");
        }
        return y;
    }

    public static Long readZ(Scanner scanner) throws InvalidInputException {
        long z;
        try {
            z = scanner.nextLong();
        }
        catch (InputMismatchException e) {
            throw new InvalidInputException("z coordinate is a number, please try again");
        }
        return z;
    }

    public Location readLocation(Scanner scanner) throws InvalidInputException {
        Double x;
        x = readX(scanner);

        Float y;
        y = readY(scanner);
        /*
        long z;
        boolean answer = ConfirmationReader.checkTheDesireToEnter(scanner, "z coordinate");
        if (answer) {
            z = readZ(scanner);
            return new Location(x, y, z);
        }
        */
        return new Location(x, y);
    }
}
