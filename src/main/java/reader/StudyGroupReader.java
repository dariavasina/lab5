package reader;

import data.*;
import exceptions.InvalidInputException;

import java.util.InputMismatchException;
import java.util.Scanner;

public class StudyGroupReader {

    public static String readName(Scanner scanner) throws InvalidInputException {
        System.out.print("Enter group's name: ");
        String name = scanner.nextLine().strip();
        if (name.isEmpty()) {
            throw new InvalidInputException("The name can't be empty, please enter it again");
        }
        return name;
    }

    public static Integer readStudentsCount(Scanner scanner) throws InvalidInputException {
        int studentsCount;
        System.out.print("Enter students' count: ");
        try {
            studentsCount = scanner.nextInt();
        }
        catch (InputMismatchException e) {
            throw new InvalidInputException("Students' count is a number, please try again");
        }
        return studentsCount;
    }

    public static Integer readShouldBeExpelled(Scanner scanner) throws InvalidInputException {
        int shouldBeExpelled;
        System.out.print("Enter should be expelled coefficient ");
        try {
            shouldBeExpelled = scanner.nextInt();
        }
        catch (InputMismatchException e) {
            throw new InvalidInputException("Should be expelled coefficient is a number, please try again");
        }
        return shouldBeExpelled;
    }

    public static FormOfEducation readFormOfEducation(Scanner scanner) throws InvalidInputException {
        FormOfEducation f;
        System.out.print("Enter one of the following forms of education: DISTANCE_EDUCATION, \nFULL_TIME_EDUCATION, \nEVENING_CLASSES");
        String input = scanner.nextLine().toLowerCase().strip();
        switch (input) {
            case "distance_education" -> f = FormOfEducation.DISTANCE_EDUCATION;
            case "full_time_education" -> f = FormOfEducation.FULL_TIME_EDUCATION;
            case "evening_classes" -> f = FormOfEducation.EVENING_CLASSES;
            default -> throw new InvalidInputException("This is not a form of education, please try again");
        }
        return f;
    }

    public static Semester readSemester(Scanner scanner) throws InvalidInputException {
        Semester s;
        System.out.print("Enter one of the following semesters: 3, \n4, \n8");
        int input = scanner.nextInt();
        switch (input) {
            case 3 -> s = Semester.THIRD;
            case 4 -> s = Semester.FOURTH;
            case 8 -> s = Semester.EIGHTH;
            default -> throw new InvalidInputException("Semester must be one of the listed numbers, please try again");
        }
        return s;
    }

    public StudyGroup readStudyGroup(Scanner scanner) {
        String name;
        while (true) {
            try {
                name = readName(scanner);
                break;
            } catch (InvalidInputException e) {
                System.out.print(e.getMessage());
            }
        }

        Coordinates coordinates;
        while (true) {
            try {
                CoordinatesReader reader = new CoordinatesReader();
                coordinates = reader.readCoordinates(scanner);
                break;
            } catch (InvalidInputException e) {
                System.out.print(e.getMessage());
            }
        }

        StudyGroup studyGroup = new StudyGroup(name, coordinates);

        boolean answer = ConfirmationReader.checkTheDesireToEnter(scanner, "studentsCount");
        if (answer) {
            Integer studentsCount;
            while (true) {
                try {
                    studentsCount = StudyGroupReader.readStudentsCount(scanner);
                    break;
                } catch (InvalidInputException e) {
                    System.out.print(e.getMessage());
                    ;
                }
            }
            studyGroup.setStudentsCount(studentsCount);
        }

        answer = ConfirmationReader.checkTheDesireToEnter(scanner, "shouldBeExpelled");
        if (answer) {
            Integer shouldBeExpelled;
            while (true) {
                try {
                    shouldBeExpelled = StudyGroupReader.readShouldBeExpelled(scanner);
                    break;
                } catch (InvalidInputException e) {
                    System.out.print(e.getMessage());
                }
            }
            studyGroup.setShouldBeExpelled(shouldBeExpelled);
        }

        answer = ConfirmationReader.checkTheDesireToEnter(scanner, "formOfEducation");
        if (answer) {
            FormOfEducation f;
            while (true) {
                try {
                    f = StudyGroupReader.readFormOfEducation(scanner);
                    break;
                } catch (InvalidInputException e) {
                    System.out.print(e.getMessage());
                }
            }
            studyGroup.setFormOfEducation(f);
        }

        answer = ConfirmationReader.checkTheDesireToEnter(scanner, "semester");
        if (answer) {
            Semester s;
            while (true) {
                try {
                    s = StudyGroupReader.readSemester(scanner);
                    break;
                } catch (InvalidInputException e) {
                    System.out.print(e.getMessage());
                }
            }
            studyGroup.setSemester(s);
        }

        answer = ConfirmationReader.checkTheDesireToEnter(scanner, "groupAdmin");
        if (answer) {
            Person admin;
            PersonReader pr = new PersonReader();
            while (true) {
                try {
                    admin = pr.readPerson(scanner);
                    break;
                } catch (InvalidInputException e) {
                    System.out.print(e.getMessage());
                }
            }
        }
        return studyGroup;
    }
}
