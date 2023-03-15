package commands;

import collection.StudyGroupCollectionManager;
import file.FileManager;

import java.io.IOException;

public class ExitCommand extends Command{
    public ExitCommand(StudyGroupCollectionManager collection) {
        super(collection);
    }

    @Override
    public void execute() {
        try {
            boolean isSaved = FileManager.filesAreEqual(".save.json", getCollectionFile());
            if (!isSaved) {
                System.out.println("Are you sure you want to exit without saving? (y/n)");
                String answer = getScanner().nextLine();
                if (answer.equals("y")) {
                    getFileManager().deleteTempFile();
                    System.exit(0);
                }
            }
        } catch (IOException e) {
            System.out.println("Something went wrong, please try again");
        }
    }
}
