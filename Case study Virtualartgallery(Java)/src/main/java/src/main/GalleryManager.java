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

    public void addGallery() {
        System.out.println("\n--- Add New Gallery ---");
        try {
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
            Time openingTime = Time.valueOf(scanner.nextLine());

            Gallery gallery = new Gallery(galleryId, name, description, location, curatorId, openingTime);

            if (galleryDao.addGallery(gallery)) {
                System.out.println("Gallery added successfully.");
            } else {
                System.out.println("Failed to add gallery.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void updateGallery() {
        System.out.println("\n--- Update Gallery Details ---");
        try {
            System.out.print("Enter Gallery ID: ");
            int galleryId = Integer.parseInt(scanner.nextLine());

            System.out.print("New Name: ");
            String name = scanner.nextLine();

            System.out.print("New Description: ");
            String description = scanner.nextLine();

            System.out.print("New Location: ");
            String location = scanner.nextLine();

            System.out.print("New Curator ID: ");
            int curatorId = Integer.parseInt(scanner.nextLine());

            System.out.print("New Opening Time (HH:MM:SS): ");
            Time openingTime = Time.valueOf(scanner.nextLine());

            Gallery gallery = new Gallery(galleryId, name, description, location, curatorId, openingTime);

            if (galleryDao.updateGallery(gallery)) {
                System.out.println("Gallery updated successfully.");
            } else {
                System.out.println("Failed to update gallery.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void removeGallery() {
        System.out.println("\n--- Remove Gallery ---");
        try {
            System.out.print("Enter the ID of the gallery to remove: ");
            int galleryId = Integer.parseInt(scanner.nextLine());

            if (galleryDao.removeGallery(galleryId)) {
                System.out.println("Gallery removed successfully.");
            } else {
                System.out.println("Failed to remove gallery.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void getGalleryById() {
        System.out.println("\n--- Get Gallery By ID ---");
        try {
            System.out.print("Enter Gallery ID: ");
            int galleryId = Integer.parseInt(scanner.nextLine());

            Gallery gallery = galleryDao.getGalleryById(galleryId);
            if (gallery != null) {
                System.out.println("Gallery Found:");
                System.out.println("ID: " + gallery.getGalleryId());
                System.out.println("Name: " + gallery.getName());
                System.out.println("Description: " + gallery.getDescription());
                System.out.println("Location: " + gallery.getLocation());
            } else {
                System.out.println("Gallery not found.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void searchGalleries() {
        System.out.println("\n--- Search Galleries ---");
        System.out.print("Enter keyword to search for galleries: ");
        String keyword = scanner.nextLine();

        List<Gallery> galleries = galleryDao.searchGalleries(keyword);
        if (galleries.isEmpty()) {
            System.out.println("No galleries found.");
        } else {
            System.out.println("Galleries found:");
            for (Gallery gallery : galleries) {
                System.out.println("ID: " + gallery.getGalleryId() + ", Name: " + gallery.getName());
            }
        }
    }
}
