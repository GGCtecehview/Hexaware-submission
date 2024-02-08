package src.entity;

import java.util.Date;

public class Artist {
protected static int artistID;
private String name;
private String biography;
private Date birthDate;
private String nationality;
private String website;
private String contactInformation;

// Default Constructor
public Artist() {
}

// Parameterized Constructor
public Artist(int artistID, String name, String biography, Date birthDate,
              String nationality, String website, String contactInformation) {
    Artist.artistID = artistID;
    this.name = name;
    this.biography = biography;
    this.birthDate = birthDate;
    this.nationality = nationality;
    this.website = website;
    this.contactInformation = contactInformation;
}

// Getters
public int getArtistID() {
    return artistID;
}

public String getName() {
    return name;
}

public String getBiography() {
    return biography;
}

public Date getBirthDate() {
    return birthDate;
}

public String getNationality() {
    return nationality;
}

public String getWebsite() {
    return website;
}

public String getContactInformation() {
    return contactInformation;
}

// Setters
public void setArtistID(int artistID) {
    this.artistID = artistID;
}

public void setName(String name) {
    this.name = name;
}

public void setBiography(String biography) {
    this.biography = biography;
}

public void setBirthDate(Date birthDate) {
    this.birthDate = birthDate;
}

public void setNationality(String nationality) {
    this.nationality = nationality;
}

public void setWebsite(String website) {
    this.website = website;
}

public void setContactInformation(String contactInformation) {
    this.contactInformation = contactInformation;
}



}