package commands;

import collection.StudyGroupCollection;

public class ExitCommand extends Command{
    public ExitCommand(StudyGroupCollection collection) {
        super(collection);
    }

    @Override
    public void execute() {
        System.out.println("Are you sure you want to exit without saving? (y/n)");
        String answer = getScanner().nextLine();
        if (answer.equals("y")) {
            System.exit(0);
        }
    }
}
