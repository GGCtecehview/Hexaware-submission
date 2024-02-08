package src.main;

import src.dao.IGalleryDao;
import src.entity.Gallery;

import java.sql.Time;
import java.util.List;
import java.util.Scanner;

public class GalleryManager {
    private IGalleryDao galleryDao;
    private Scanner scanner;

    public GalleryManager(IGalleryDao galleryDao, Scanner scanner) {
        this.galleryDao = galleryDao;
        this.scanner = scanner;
    }
    

 // Method to add a new gallery
    public void addGallery() {
        System.out.println("Enter Gallery Details:");
        
        System.out.print("Gallery ID: ");
        int galleryId = Integer.parseInt(scanner.nextLine());

        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Description: ");
        String description = scanner.nextLine();

        System.out.print("Location: ");
        String location = scanner.nextLine();

        System.out.print("Curator ID: ");
        int curatorId = Integer.parseInt(scanner.nextLine());

        System.out.print("Opening Time (HH:MM:SS): ");
        String openingTimeStr = scanner.nextLine();
        Time openingTime;
        try {
            openingTime = Time.valueOf(openingTimeStr); // "HH:MM:SS"
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid time format. Please use HH:MM:SS.");
            return;
        }

        Gallery gallery = new Gallery(galleryId ,name, description, location, curatorId, openingTime);

        boolean isSuccess = galleryDao.addGallery(gallery);
        if (isSuccess) {
            System.out.println("Gallery added successfully.");
        } else {
            System.out.println("Failed to add gallery. Please check the details and try again.");
        }
    }

    // Method to update an existing gallery
    public void updateGallery() {
        System.out.println("Update Gallery Details:");

        System.out.print("Enter Gallery ID: ");
        int galleryId;
        try {
            galleryId = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid numeric ID.");
            return;
        }

        System.out.print("New Name: ");
        String name = scanner.nextLine();

        System.out.print("New Description: ");
        String description = scanner.nextLine();

        System.out.print("New Location: ");
        String location = scanner.nextLine();

        System.out.print("New Curator ID: ");
        int curatorId;
        try {
            curatorId = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid numeric ID.");
            return;
        }

        System.out.print("New Opening Time (HH:MM:SS): ");
        String openingTimeStr = scanner.nextLine();
        Time openingTime;
        try {
            openingTime = Time.valueOf(openingTimeStr); // "HH:MM:SS"
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid time format. Please use HH:MM:SS.");
            return;
        }

        Gallery gallery = new Gallery(galleryId, name, description, location, curatorId, openingTime);

        boolean isSuccess = galleryDao.updateGallery(gallery);
        if (isSuccess) {
            System.out.println("Gallery updated successfully.");
        } else {
            System.out.println("Failed to update gallery. Please check the details and try again.");
        }
    }



    // Method to remove an existing gallery
    public void removeGallery() {
        System.out.print("Enter the ID of the gallery to remove: ");
        int galleryId = Integer.parseInt(scanner.nextLine());

        boolean isSuccess = galleryDao.removeGallery(galleryId);
        if (isSuccess) {
            System.out.println("Gallery removed successfully.");
        } else {
            System.out.println("Failed to remove gallery. It might not exist or an error occurred.");
        }
    }

    public void getGalleryById() {
        System.out.print("Enter Gallery ID: ");
        int galleryId = Integer.parseInt(scanner.nextLine());

        Gallery gallery = galleryDao.getGalleryById(galleryId);
        if (gallery != null) {
            System.out.println("Gallery Found: ");
            System.out.println("Name: " + gallery.getName());
            System.out.println("Desc: " + gallery.getDescription());
            System.out.println("Location: " + gallery.getLocation());
           
        } else {
            System.out.println("Gallery not found.");
        }
    }

    public void searchGalleries() {
        System.out.print("Enter keyword to search for galleries: ");
        String keyword = scanner.nextLine();

        List<Gallery> galleries = galleryDao.searchGalleries(keyword);
        if (galleries.isEmpty()) {
            System.out.println("No galleries found with the given keyword.");
        } else {
            System.out.println("Galleries found:");
            for (Gallery gallery : galleries) {
                // Display gallery details
                System.out.println("ID: " + gallery.getGalleryId() + ", Name: " + gallery.getName() +" ,Description "+ gallery.getDescription());
                
            }
        }
    }
}
