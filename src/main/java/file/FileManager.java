package file;

import collection.StudyGroupCollectionManager;
import data.*;
import exceptions.FileParseException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


public class FileManager {
    private String fileInput;
    private String fileOutput;

    public FileManager(String filePath, String fileOutput) {
        this.fileInput = filePath;
        this.fileOutput = fileOutput;
    }

    public String getFileInput() {
        return fileInput;
    }

    public void setFileInput(String fileInput) {
        this.fileInput = fileInput;
    }

    public LinkedHashMap<Long, StudyGroup> readFromJson() throws IOException {
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


    public void saveToJson(StudyGroupCollectionManager sgc) throws IOException {
        JSONObject json = new JSONObject();
        HashMap<Long, StudyGroup> collection = sgc.getMap();

        for (Map.Entry<Long, StudyGroup> entry : collection.entrySet()) {
            JSONObject groupJson = StudyGroupJsonWriter.toJson(entry.getValue());
            json.put(String.valueOf(entry.getKey()), groupJson);
        }

        try (PrintWriter writer = new PrintWriter(fileOutput)) {
            writer.write(json.toString(2));
        }
    }


}
