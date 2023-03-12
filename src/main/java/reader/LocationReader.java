package reader;

import data.Location;
import exceptions.InvalidInputException;

import java.util.InputMismatchException;
import java.util.Scanner;

public class LocationReader {
    public static Double readX(Scanner scanner) throws InvalidInputException {
        double x;
        System.out.print("Enter location's x coordinate: ");
        try {
            String input = scanner.nextLine();
            x = Double.parseDouble(input);
        }
        catch (NumberFormatException e) {
            throw new InvalidInputException("x coordinate is a number, please try again\n");
        }
        return x;
    }

    public static Float readY(Scanner scanner) throws InvalidInputException {
        float y;
        System.out.print("Enter location's y coordinate: ");
        try {
            String input = scanner.nextLine();
            y = Float.parseFloat(input);
        }
        catch (NumberFormatException e) {
            throw new InvalidInputException("y coordinate is a number, please try again\n");
        }
        return y;
    }

    public static Long readZ(Scanner scanner) throws InvalidInputException {
        long z;
        System.out.print("Enter location's z coordinate: ");
        try {
            String input = scanner.nextLine();
            z = Long.parseLong(input);
        }
        catch (NumberFormatException e) {
            throw new InvalidInputException("z coordinate is a number, please try again\n");
        }
        return z;
    }

    public Location readLocation(Scanner scanner){
        Double x;
        while (true) {
            try {
                x = readX(scanner);
                break;
            } catch (InvalidInputException e) {
                System.out.print(e.getMessage());
            }
        }

        Float y;
        while (true) {
            try {
                y = readY(scanner);
                break;
            } catch (InvalidInputException e) {
                System.out.print(e.getMessage());
            }
        }


        long z;
        boolean answer = ConfirmationReader.checkTheDesireToEnter(scanner, "z coordinate");
        if (answer) {
            while (true) {
                try {
                    z = readZ(scanner);
                    break;
                } catch (InvalidInputException e) {
                    System.out.print(e.getMessage());
                }
            }
            return new Location(x, y, z);
        }
        return new Location(x, y);
    }
}
