package data;
import java.util.Date;

public class Person {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private String passportID; //Длина строки не должна быть больше 30, Длина строки должна быть не меньше 6, Значение этого поля должно быть уникальным, Поле может быть null
    private Color hairColor; //Поле не может быть null
    private Country nationality; //Поле может быть null
    private Location location; //Поле не может быть null

    public Person(String name, Color hairColor, Location location) {
        if (name == null || name.isEmpty() || hairColor == null || location == null) throw new IllegalArgumentException("Fields can't be null or empty sequences");
        this.name = name;
        this.hairColor = hairColor;
        this.location = location;
    }

    public Person(String name, String passportID, Color hairColor, Country nationality, Location location) {
        if (name == null || name.isEmpty() || passportID.length() > 30  || passportID.length() < 6 || hairColor == null || location == null) throw new IllegalArgumentException("Fields can't be null or empty sequences");
        this.name = name;
        this.passportID = passportID;
        this.hairColor = hairColor;
        this.nationality = nationality;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassportID() {
        return passportID;
    }

    public void setPassportID(String passportID) {
        this.passportID = passportID;
    }

    public Color getHairColor() {
        return hairColor;
    }

    public void setHairColor(Color hairColor) {
        this.hairColor = hairColor;
    }

    public Country getNationality() {
        return nationality;
    }

    public void setNationality(Country nationality) {
        this.nationality = nationality;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
