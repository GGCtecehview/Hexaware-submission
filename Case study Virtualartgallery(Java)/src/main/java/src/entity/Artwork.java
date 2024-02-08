package src.entity;

import java.util.Date;

public class Artwork extends Artist {
private int artworkID;
private String title;
private String description;
private Date creationDate;
private String medium;
private String imageURL;


public Artwork() {
}

// Parameterized Constructor
public Artwork (int artworkID,String title, String description, Date creationDate, String medium, String imageURL) {
    super(artistID, imageURL, imageURL, creationDate, imageURL, imageURL, imageURL);
	this.artworkID = artworkID;
    this.title = title;
    this.description = description;
    this.creationDate = creationDate;
    this.medium = medium;
    this.imageURL = imageURL;
}

public int getArtistID() {
    return artistID;
}

public int getArtworkID() {
    return artworkID;
}

public String getTitle() {
    return title;
}

public String getDescription() {
    return description;
}

public Date getCreationDate() {
    return creationDate;
}

public String getMedium() {
    return medium;
}

public String getImageURL() {
    return imageURL;
}

// Setters
public void setArtworkID(int artworkID) {
    this.artworkID = artworkID;
}
public void setArtistID(int artistID) {
    this.artistID = artistID;
}

public void setTitle(String title) {
    this.title = title;
}

public void setDescription(String description) {
    this.description = description;
}

public void setCreationDate(Date creationDate) {
    this.creationDate = creationDate;
}

public void setMedium(String medium) {
    this.medium = medium;
}

public void setImageURL(String imageURL) {
    this.imageURL = imageURL;
}

}