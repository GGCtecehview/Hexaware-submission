package src.entity;

import java.util.Date;
import java.util.List;

public class User {
private int userID;
private String username;
private String password;
private String email;
private String firstName;
private String lastName;
private Date dateOfBirth;
private String profilePicture;
private List<Integer> favoriteArtworks; // References to ArtworkIDs
public User() {
}

// Parameterized Constructor
public User(int userID, String username, String password, String email, String firstName,
            String lastName, Date dateOfBirth, String profilePicture, List<Integer> favoriteArtworks) {
    this.userID = userID;
    this.username = username;
    this.password = password;
    this.email = email;
    this.firstName = firstName;
    this.lastName = lastName;
    this.dateOfBirth = dateOfBirth;
    this.profilePicture = profilePicture;
    this.favoriteArtworks = favoriteArtworks;
}

// Getters
public int getUserID() {
    return userID;
}

public String getUsername() {
    return username;
}

public String getPassword() {
    return password;
}

public String getEmail() {
    return email;
}

public String getFirstName() {
    return firstName;
}

public String getLastName() {
    return lastName;
}

public Date getDateOfBirth() {
    return dateOfBirth;
}

public String getProfilePicture() {
    return profilePicture;
}

public List<Integer> getFavoriteArtworks() {
    return favoriteArtworks;
}

// Setters
public void setUserID(int userID) {
    this.userID = userID;
}

public void setUsername(String username) {
    this.username = username;
}

public void setPassword(String password) {
    this.password = password;
}

public void setEmail(String email) {
    this.email = email;
}

public void setFirstName(String firstName) {
    this.firstName = firstName;
}

public void setLastName(String lastName) {
    this.lastName = lastName;
}

public void setDateOfBirth(Date dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
}

public void setProfilePicture(String profilePicture) {
    this.profilePicture = profilePicture;
}

public void setFavoriteArtworks(List<Integer> favoriteArtworks) {
    this.favoriteArtworks = favoriteArtworks;
}
}
