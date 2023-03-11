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
            throw new InvalidInputException("The name can't be empty, please enter it again");
        }
        return name;
    }

    public static String readPassportId(Scanner scanner) throws InvalidInputException {
        System.out.print("Enter passport id: ");
        String passportID = scanner.nextLine().strip();
        if (passportID.length() > 30 || passportID.length() < 6) {
            throw new InvalidInputException("Passport ID's length is in between 6 and 30, please enter a correct passport ID");
        }
        return passportID;
    }

    public static Color readHairColor(Scanner scanner) throws InvalidInputException {
        System.out.print("Enter one of the following hair colors: RED, \nYELLOW, \nORANGE");
        Color color;
        String input = scanner.nextLine().strip();
        switch (input) {
            case "RED" -> color = Color.RED;
            case "YELLOW" -> color = Color.YELLOW;
            case "ORANGE" -> color = Color.ORANGE;
            default -> throw new InvalidInputException("This is not one of the listed colors, please try again");
        }
        return color;
    }

    public static Country readNationality(Scanner scanner) throws InvalidInputException {
        System.out.print("Enter one of the following countries: RUSSIA, \nFRANCE, \nINDIA, \nVATICAN, \nNORTH_KOREA");
        Country country;
        String input = scanner.nextLine().strip();
        switch (input) {
            case "RUSSIA" -> country = Country.RUSSIA;
            case "FRANCE" -> country = Country.FRANCE;
            case "INDIA" -> country = Country.INDIA;
            case "VATICAN" -> country = Country.VATICAN;
            case "NORTH_KOREA" -> country = Country.NORTH_KOREA;
            default -> throw new InvalidInputException("This is not one of the listed countries, please try again");
        }
        return country;
    }


    public Person readPerson(Scanner scanner) throws InvalidInputException{
        String name = PersonReader.readName(scanner);
        Color color = PersonReader.readHairColor(scanner);

        LocationReader lr = new LocationReader();
        Location location = lr.readLocation(scanner);

        Person person = new Person(name, color, location);


        /*boolean answer = ConfirmationReader.checkTheDesireToEnter(scanner, "passportID");
        if (answer) {
            String passportID = readPassportId(scanner);
            person.setPassportID(passportID);
        }

        answer = ConfirmationReader.checkTheDesireToEnter(scanner, "nationality");
        if (answer) {
            Country country = readNationality(scanner);
            person.setNationality(country);
        }
*/
        return person;
    }

}
