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
            throw new InvalidInputException("The name can't be empty, please enter it again\n");
        }
        return name;
    }

    public static Integer readStudentsCount(Scanner scanner) throws InvalidInputException {
        int studentsCount;
        System.out.print("Enter students' count: ");
        try {
            String input = scanner.nextLine();
            studentsCount = Integer.parseInt(input);
        }
        catch (NumberFormatException nfe) {
            throw new InvalidInputException("Students' count is a number, please try again\n");
        }
        return studentsCount;
    }

    public static Integer readShouldBeExpelled(Scanner scanner) throws InvalidInputException {
        int shouldBeExpelled;
        System.out.print("Enter shouldBeExpelled: ");
        try {
            String input = scanner.nextLine();
            shouldBeExpelled = Integer.parseInt(input);
        }
        catch (NumberFormatException nfe) {
            throw new InvalidInputException("ShouldBeExpelled is a number, please try again\n");
        }
        return shouldBeExpelled;
    }

    public static FormOfEducation readFormOfEducation(Scanner scanner) throws InvalidInputException {
        FormOfEducation f;
        System.out.println("Enter one of the following forms of education: \nDISTANCE_EDUCATION, \nFULL_TIME_EDUCATION, \nEVENING_CLASSES");
        String input = scanner.nextLine().toLowerCase().strip();
        switch (input) {
            case "distance_education" -> f = FormOfEducation.DISTANCE_EDUCATION;
            case "full_time_education" -> f = FormOfEducation.FULL_TIME_EDUCATION;
            case "evening_classes" -> f = FormOfEducation.EVENING_CLASSES;
            default -> throw new InvalidInputException("This is not a form of education, please try again\n");
        }
        return f;
    }

    public static Semester readSemester(Scanner scanner) throws InvalidInputException {
        Semester s;
        System.out.println("Enter one of the following semesters: \n3, \n4, \n8");
        String input = scanner.nextLine();
        try {
            int semester = Integer.parseInt(input);
            switch (semester) {
                case 3 -> s = Semester.THIRD;
                case 4 -> s = Semester.FOURTH;
                case 8 -> s = Semester.EIGHTH;
                default -> throw new InvalidInputException("Semester must be one of the listed numbers, please try again\n");
            }
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Please enter one of the listed numbers");
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

        CoordinatesReader reader = new CoordinatesReader();
        Coordinates coordinates = reader.readCoordinates(scanner);


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
                }
            }
            studyGroup.setStudentsCount(studentsCount);
        }
        else {
            studyGroup.setStudentsCount(0);
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
            PersonReader pr = new PersonReader();
            Person admin = pr.readPerson(scanner);
            studyGroup.setGroupAdmin(admin);
        }
        return studyGroup;
    }
}
