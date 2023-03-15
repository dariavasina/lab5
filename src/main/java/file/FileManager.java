package file;

import collection.StudyGroupCollectionManager;
import data.*;
import exceptions.FileParseException;
import org.json.JSONObject;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


public class FileManager {
    private String fileInput;
    private File tempFile;

    public FileManager() {
        setTempFile();
    }

    public void setTempFile() {
        this.tempFile = new File(".save.json");
    }

    public String getFileInput() {
        return fileInput;
    }

    public void setFileInput(String fileInput) {
        this.fileInput = fileInput;
    }

    public static LinkedHashMap<Long, StudyGroup> readFromJson(String fileInput) throws IOException {
        LinkedHashMap<Long, StudyGroup> groups = new LinkedHashMap<>();
        String fileContent = Files.readString(Paths.get(fileInput));
        JSONObject json = new JSONObject(fileContent);
        for (String key : json.keySet()) {
            JSONObject groupJson = json.getJSONObject(key);
            try {
                StudyGroup group = StudyGroupJsonReader.readStudyGroup(groupJson);
                groups.put(Long.parseLong(key), group);
            } catch (FileParseException e) {
                //System.out.println(e.getMessage());
            }
        }
        return groups;
    }


    public void saveToJson(StudyGroupCollectionManager sgc, String fileName) throws IOException {
        JSONObject json = new JSONObject();
        HashMap<Long, StudyGroup> collection = sgc.getMap();

        for (Map.Entry<Long, StudyGroup> entry : collection.entrySet()) {
            JSONObject groupJson = StudyGroupJsonWriter.toJson(entry.getValue());
            json.put(String.valueOf(entry.getKey()), groupJson);
        }

        try (PrintWriter writer = new PrintWriter(fileName)) {
            writer.write(json.toString(2));
        }
    }

    public void deleteTempFile() {
        tempFile.delete();
    }

    public static boolean filesAreEqual(String fileName1, String fileName2) throws IOException {
        File file1 = new File(fileName1);
        File file2 = new File(fileName2);

        String f1 = file1.toString();
        String f2 = file2.toString();

        return f1.equals(f2);

    }


}
