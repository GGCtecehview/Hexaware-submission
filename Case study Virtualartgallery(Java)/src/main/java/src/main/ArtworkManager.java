package src.main;

import src.dao.IVirtualArtGallery;
import src.entity.Artwork;
import src.exception.ArtWorkNotFoundException;
import src.exception.UserNotFoundException;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

public class ArtworkManager {

    public ArtworkManager(IVirtualArtGallery galleryService, Scanner scanner) {
    }

    static void addArtwork(IVirtualArtGallery galleryService, Scanner scanner) {
        System.out.println("Enter Artwork Details:");

        System.out.print("artworkID: ");
        int artworkID = scanner.nextInt();
        scanner.nextLine();  // Consume newline left-over

        System.out.print("Title: ");
        String title = scanner.nextLine();

        System.out.print("Description: ");
        String description = scanner.nextLine();

        System.out.print("Creation Date (YYYY-MM-DD): ");
        String creationDateStr = scanner.nextLine();
        Date creationDate = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            creationDate = new Date(sdf.parse(creationDateStr).getTime());
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please use YYYY-MM-DD.");
            return;
        }

        System.out.print("Medium: ");
        String medium = scanner.nextLine();

        System.out.print("Image URL: ");
        String imageURL = scanner.nextLine();

        System.out.print("Artist ID: ");
        int artistID = Integer.parseInt(scanner.nextLine());

        Artwork artwork = new Artwork();
        artwork.setArtworkID(artworkID);
        artwork.setTitle(title);
        artwork.setDescription(description);
        artwork.setCreationDate(creationDate);
        artwork.setMedium(medium);
        artwork.setImageURL(imageURL);
        artwork.setArtistID(artistID);

        boolean isSuccess = galleryService.addArtwork(artwork);
        if (isSuccess) {
            System.out.println("Artwork added successfully.");
        } else {
            System.out.println("Failed to add artwork. Please check the artwork details and try again.");
        }
    }
    static void updateArtwork(IVirtualArtGallery galleryService, Scanner scanner) {
        System.out.println("Update Artwork Details:");

        System.out.print("Artwork ID: ");
        int artworkID = getIntegerInput(scanner);

        System.out.print("New Title: ");
        String title = scanner.nextLine();

        System.out.print("New Description: ");
        String description = scanner.nextLine();

        System.out.print("New Creation Date (YYYY-MM-DD): ");
        String creationDateStr = scanner.nextLine();
        Date creationDate = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            creationDate = new Date(sdf.parse(creationDateStr).getTime());
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please use YYYY-MM-DD.");
            return;
        }

        System.out.print("New Medium: ");
        String medium = scanner.nextLine();

        System.out.print("New Image URL: ");
        String imageURL = scanner.nextLine();

        System.out.print("New Artist ID: ");
        int artistID = getIntegerInput(scanner);

        Artwork artwork = new Artwork();
        artwork.setArtworkID(artworkID);
        artwork.setTitle(title);
        artwork.setDescription(description);
        artwork.setCreationDate(creationDate);
        artwork.setMedium(medium);
        artwork.setImageURL(imageURL);
        artwork.setArtistID(artistID);

        boolean isSuccess = galleryService.updateArtwork(artwork);
        if (isSuccess) {
            System.out.println("Artwork updated successfully.");
        } else {
            System.out.println("Failed to update artwork.");
        }
    }


    static void getArtworkById(IVirtualArtGallery galleryService, Scanner scanner) throws ArtWorkNotFoundException {
        System.out.print("Enter Artwork ID: ");
        int id = getIntegerInput(scanner);

        Artwork artwork = galleryService.getArtworkById(id);
        if (artwork != null) {
            System.out.println("Artwork Found: ");
            System.out.println("Title: " + artwork.getTitle());
            System.out.println("Description: " + artwork.getDescription());
            System.out.println("Medium: " + artwork.getMedium());
            
        } else {
            System.out.println("Artwork not found.");
        }
    }


    static void removeArtwork(IVirtualArtGallery galleryService, Scanner scanner) {
        System.out.print("Enter the ID of the artwork to remove: ");
        int artworkID = getIntegerInput(scanner);

        boolean isSuccess = galleryService.removeArtwork(artworkID);
        if (isSuccess) {
            System.out.println("Artwork removed successfully.");
        } else {
            System.out.println("Failed to remove artwork. It might not exist or an error occurred.");
        }
    }
    static void searchArtworks(IVirtualArtGallery galleryService, Scanner scanner) {
        System.out.print("Enter keyword to search for artworks: ");
        String keyword = scanner.nextLine();

        List<Artwork> artworks = galleryService.searchArtworks(keyword);
        if (artworks.isEmpty()) {
            System.out.println("No artworks found with the given keyword.");
        } else {
            System.out.println("Artworks found:");
            for (Artwork artwork : artworks) {
                // Display artwork details
                System.out.println("ID: " + artwork.getArtworkID() + ", Title: " + artwork.getTitle());
                // You can add more details to display as needed
            }
        }
    }
    static void addArtworkToFavorite(IVirtualArtGallery galleryService, Scanner scanner) {
        System.out.print("Enter your User ID: ");
        int userId = getIntegerInput(scanner);

        System.out.print("Enter the ID of the artwork to add to favorites: ");
        int artworkId = getIntegerInput(scanner);

        boolean isSuccess = galleryService.addArtworkToFavorite(userId, artworkId);
        if (isSuccess) {
            System.out.println("Artwork added to favorites successfully.");
        } else {
            System.out.println("Failed to add artwork to favorites. It might not exist, or an error occurred.");
        }
    }
    static void removeArtworkFromFavorite(IVirtualArtGallery galleryService, Scanner scanner) {
        System.out.print("Enter your User ID: ");
        int userId = getIntegerInput(scanner);

        System.out.print("Enter the ID of the artwork to remove from favorites: ");
        int artworkId = getIntegerInput(scanner);

        boolean isSuccess = galleryService.removeArtworkFromFavorite(userId, artworkId);
        if (isSuccess) {
            System.out.println("Artwork removed from favorites successfully.");
        } else {
            System.out.println("Failed to remove artwork from favorites. Please check the IDs and try again.");
        }
    }
    
    static void getUserFavoriteArtworks(IVirtualArtGallery galleryService, Scanner scanner) {
        System.out.print("Enter your User ID: ");
        int userId = getIntegerInput(scanner);

        try {
            List<Artwork> favoriteArtworks = galleryService.getUserFavoriteArtworks(userId);
            if (favoriteArtworks.isEmpty()) {
                System.out.println("No favorite artworks found for the user.");
            } else {
                System.out.println("Favorite Artworks:");
                for (Artwork artwork : favoriteArtworks) {
                    System.out.println("ID: " + artwork.getArtworkID() + ", Title: " + artwork.getTitle());
                }
            }
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }


    static int getIntegerInput(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next(); 
        }
        int number = scanner.nextInt();
        scanner.nextLine(); 
        return number;
    }
}