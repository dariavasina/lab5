package commands;

import collection.StudyGroupCollection;

public class HelpCommand extends Command{
    public HelpCommand(StudyGroupCollection collection) {
        super(collection);
    }
    @Override
    public void execute() {
        collection.help();
    }
}
