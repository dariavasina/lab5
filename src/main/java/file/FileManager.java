package file;

import collection.StudyGroupCollection;
import data.*;
import org.json.JSONArray;
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

    public LinkedHashMap<Long, StudyGroup> readFromFile() throws IOException {
        LinkedHashMap<Long, StudyGroup> groups = new LinkedHashMap<>();
        String fileContent = Files.readString(Paths.get(fileInput));
        JSONObject json = new JSONObject(fileContent);
        for (String key : json.keySet()) {
            JSONObject groupJson = json.getJSONObject(key);
            StudyGroup group = fromJson(groupJson);
            groups.put(Long.parseLong(key), group);
        }
        return groups;
    }


    public static StudyGroup fromJson(JSONObject json) {
        String name = json.getString("name");

        JSONObject coordinatesJson = json.getJSONObject("coordinates");
        Coordinates coordinates = new Coordinates(
                coordinatesJson.getDouble("x"),
                coordinatesJson.getInt("y"));

        Integer studentsCount = json.optInt("studentsCount");
        Integer shouldBeExpelled = json.optInt("shouldBeExpelled");

        FormOfEducation formOfEducation = null;
        if (json.has("formOfEducation")) {
            formOfEducation = FormOfEducation.valueOf(json.getString("formOfEducation"));
        }

        Semester semester = null;
        if (json.has("semester")) {
            semester = Semester.valueOf(json.getString("semester"));
        }

        Person groupAdmin = null;
        if (json.has("groupAdmin")) {
            JSONObject groupAdminJson = json.getJSONObject("groupAdmin");
            String groupAdminName = groupAdminJson.getString("name");

            String passportID = null;
            if (groupAdminJson.has("passportID")) {
                passportID = groupAdminJson.getString("passportID");
            }

            Color hairColor = null;
            if (groupAdminJson.has("hairColor")) {
                hairColor = Color.valueOf(groupAdminJson.getString("hairColor"));
            }


            Country nationality = null;
            if (groupAdminJson.has("nationality")) {
                nationality = Country.valueOf(groupAdminJson.getString("nationality"));
            }

            JSONObject locationJson = groupAdminJson.getJSONObject("location");
            double x = locationJson.getDouble("x");
            float y = (float) locationJson.getDouble("y");

            Long z = null;
            Location location;

            if (locationJson.has("z")) {
                z = locationJson.getLong("z");
                location = new Location(x, y, z);
            }
            else {
                location = new Location(x, y);
            }

            groupAdmin = new Person(groupAdminName, null, hairColor, nationality, location);
        }


        StudyGroup studyGroup = new StudyGroup(name, coordinates, studentsCount, shouldBeExpelled, formOfEducation, semester, groupAdmin);

        return studyGroup;
    }

    public static JSONObject toJson(StudyGroup studyGroup) {
        JSONObject json = new JSONObject();

        json.put("id", studyGroup.getId());
        json.put("name", studyGroup.getName());

        JSONObject coordinatesJson = new JSONObject();
        Coordinates coordinates = studyGroup.getCoordinates();
        coordinatesJson.put("x", coordinates.getX());
        coordinatesJson.put("y", coordinates.getY());
        json.put("coordinates", coordinatesJson);

        Integer studentsCount = studyGroup.getStudentsCount();
        if (studentsCount != null) {
            json.put("studentsCount", studentsCount);
        }

        Integer shouldBeExpelled = studyGroup.getShouldBeExpelled();
        if (shouldBeExpelled != null) {
            json.put("shouldBeExpelled", shouldBeExpelled);
        }

        FormOfEducation formOfEducation = studyGroup.getFormOfEducation();
        if (formOfEducation != null) {
            json.put("formOfEducation", formOfEducation.toString());
        }

        Semester semester = studyGroup.getSemester();
        if (semester != null) {
            json.put("semester", semester.toString());
        }

        Person groupAdmin = studyGroup.getGroupAdmin();
        if (groupAdmin != null) {
            JSONObject groupAdminJson = new JSONObject();
            groupAdminJson.put("name", groupAdmin.getName());
            groupAdminJson.put("hairColor", groupAdmin.getHairColor());

            Location location = groupAdmin.getLocation();
            JSONObject locationJson = new JSONObject();
            locationJson.put("x", location.getX());
            locationJson.put("y", location.getY());

            Long z = location.getZ();
            if (!Objects.isNull(z)) {
                locationJson.put("z", z);
            }

            json.put("location", locationJson);

            String passportID = groupAdmin.getPassportID();
            if (passportID != null) {
                groupAdminJson.put("passportID", groupAdmin.getPassportID());
            }

            Country nationality = groupAdmin.getNationality();
            if (nationality != null) {
                groupAdminJson.put("nationality", groupAdmin.getNationality());
            }

            json.put("groupAdmin", groupAdminJson);
        }

        return json;
    }

    public void saveToJson(StudyGroupCollection sgc) throws IOException {
        JSONObject json = new JSONObject();
        HashMap<Long, StudyGroup> collection = sgc.getMap();

        for (Map.Entry<Long, StudyGroup> entry : collection.entrySet()) {
            JSONObject groupJson = toJson(entry.getValue());
            json.put(String.valueOf(entry.getKey()), groupJson);
        }

        try (PrintWriter writer = new PrintWriter(fileOutput)) {
            writer.write(json.toString(2));
        }
    }


}
