package src.entity;

import java.sql.Time;

public class Gallery {
    private int galleryId;
    private String name;
    private String description;
    private String location;
    private int curatorId; // Assuming curator is represented by an artist ID
    private Time openingTime;

    // Constructors
    public Gallery() {
    }

    public Gallery(int galleryId, String name, String description, String location, int curatorId, Time openingTime) {
        this.galleryId = galleryId;
        this.name = name;
        this.description = description;
        this.location = location;
        this.curatorId = curatorId;
        this.openingTime = openingTime;
    }
    public Gallery(String name, String description, String location, int curatorId, Time openingTime) {
        this.name = name;
        this.description = description;
        this.location = location;
        this.curatorId = curatorId;
        this.openingTime = openingTime;
    }

    // Getters and setters
    public int getGalleryId() {
        return galleryId;
    }

    public void setGalleryId(int galleryId) {
        this.galleryId = galleryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getCuratorId() {
        return curatorId;
    }

    public void setCuratorId(int curatorId) {
        this.curatorId = curatorId;
    }

    public Time getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(Time openingTime) {
        this.openingTime = openingTime;
    }

}
