import collection.StudyGroupCollection;
import commands.Command;
import commands.InsertCommand;
import commands.ShowCommand;
import data.*;
import exceptions.CommandDoesNotExistException;
import exceptions.InvalidInputException;
import exceptions.KeyAlreadyExistsException;
import exceptions.KeyDoesNotExistException;
import org.w3c.dom.ls.LSOutput;
import reader.ConfirmationReader;
import reader.CoordinatesReader;

import java.util.Arrays;
import java.util.Scanner;

public class Test {
    public static void main(String[] args)  {
        Scanner scanner = new Scanner(System.in);
        StudyGroup sg1 = new StudyGroup("P3112", new Coordinates(3.0, 4), 20, 3, FormOfEducation.FULL_TIME_EDUCATION, Semester.THIRD ,new Person("Вася Пупкин", Color.RED, new Location(2.0, 3F, 4)));
        StudyGroup sg2 = new StudyGroup("P3113", new Coordinates(5.0, 6));
        StudyGroupCollection sgc = new StudyGroupCollection();

        /*
        try {
            sgc.insert(1L, sg1);
            sgc.getCollection();
            InsertCommand insertCommand = new InsertCommand(sgc, 1L, sg2);
            insertCommand.execute();
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }

        Command ShowCommand  = new ShowCommand(sgc);
        ShowCommand.execute();

         */

        CommandParser cp = new CommandParser(sgc);
        CoordinatesReader cr = new CoordinatesReader();
        ConfirmationReader confreader = new ConfirmationReader();
        //boolean answer = confreader.checkTheDesireToEnter(scanner, "t");
        //System.out.println(answer);

        try {
            cr.readCoordinates(scanner);
        } catch (InvalidInputException e) {
            System.out.print(e.getMessage());
        }

        /*try {
            cp.getCommand(scanner);
        } catch (CommandDoesNotExistException e) {
            System.out.println(e.getMessage());
        }
         */

    }



}
