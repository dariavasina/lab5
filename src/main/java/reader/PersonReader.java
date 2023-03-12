package reader;

import commands.InfoCommand;
import data.*;
import exceptions.InvalidInputException;

import java.util.Scanner;

public class PersonReader {
    public static String readName(Scanner scanner) throws InvalidInputException {
        System.out.print("Enter person's name: ");
        String name = scanner.nextLine().strip();
        if (name.isEmpty()) {
            throw new InvalidInputException("The name can't be empty, please enter it again\n");
        }
        return name;
    }

    public static String readPassportId(Scanner scanner) throws InvalidInputException {
        System.out.print("Enter passport id: ");
        String passportID = scanner.nextLine().strip();
        if (passportID.length() > 30 || passportID.length() < 6) {
            throw new InvalidInputException("Passport ID's length is in between 6 and 30, please enter a correct passport ID\n");
        }
        return passportID;
    }

    public static Color readHairColor(Scanner scanner) throws InvalidInputException {
        System.out.println("Enter one of the following hair colors: \nRED, \nYELLOW, \nORANGE");
        Color color;
        String input = scanner.nextLine().strip().toLowerCase();
        switch (input) {
            case "red" -> color = Color.RED;
            case "yellow" -> color = Color.YELLOW;
            case "orange" -> color = Color.ORANGE;
            default -> throw new InvalidInputException("This is not one of the listed colors, please try again\n");
        }
        return color;
    }

    public static Country readNationality(Scanner scanner) throws InvalidInputException {
        System.out.println("Enter one of the following countries: \nRUSSIA, \nFRANCE, \nINDIA, \nVATICAN, \nNORTH_KOREA");
        Country country;
        String input = scanner.nextLine().strip().toLowerCase();
        switch (input) {
            case "russia" -> country = Country.RUSSIA;
            case "france" -> country = Country.FRANCE;
            case "india" -> country = Country.INDIA;
            case "vatican" -> country = Country.VATICAN;
            case "north_korea" -> country = Country.NORTH_KOREA;
            default -> throw new InvalidInputException("This is not one of the listed countries, please try again\n");
        }
        return country;
    }


    public Person readPerson(Scanner scanner){
        String name;
        while (true) {
            try {
                name = readName(scanner);
                break;
            } catch (InvalidInputException e) {
                System.out.print(e.getMessage());
            }
        }

        Color color;
        while (true) {
            try {
                color = readHairColor(scanner);
                break;
            } catch (InvalidInputException e) {
                System.out.print(e.getMessage());
            }
        }

        LocationReader lr = new LocationReader();
        Location location = lr.readLocation(scanner);

        Person person = new Person(name, color, location);

        boolean answer = ConfirmationReader.checkTheDesireToEnter(scanner, "passportID");
        if (answer) {
            String passportID;
            while (true) {
                try {
                    passportID = readPassportId(scanner);
                    break;
                } catch (InvalidInputException e) {
                    System.out.print(e.getMessage());
                }
            }
            person.setPassportID(passportID);
        }

        answer = ConfirmationReader.checkTheDesireToEnter(scanner, "nationality");
        if (answer) {
            Country country;
            while (true) {
                try {
                    country = readNationality(scanner);
                    break;
                } catch (InvalidInputException e) {
                    System.out.print(e.getMessage());
                }
            }
            person.setNationality(country);
        }
        return person;
    }
}
