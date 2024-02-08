package src.main;

import src.controller.VirtualArtGalleryImpl;
import src.dao.IVirtualArtGallery;
import src.util.DBConnUtil;
import src.dao.IGalleryDao;
import src.controller.GalleryImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class VirtualArtGallery {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in);
             Connection conn = DBConnUtil.getConnection()) {
            IVirtualArtGallery galleryService = new VirtualArtGalleryImpl(conn);
            IGalleryDao galleryDao = new GalleryImpl(conn); // Assuming you have this implementation
            GalleryManager galleryManager = new GalleryManager(galleryDao, scanner);

            while (true) {
                System.out.println("\nVirtual Art Gallery System");
                System.out.println("1. Add Artwork");
                System.out.println("2. Update Artwork");
                System.out.println("3. Get Artwork By ID");
                System.out.println("4. Remove Artwork");
                System.out.println("5. Search Artworks");
                System.out.println("6. Add Artwork to Favorites");
                System.out.println("7. Remove Artwork from Favorites");
                System.out.println("8. User Favorite Artworks");
                System.out.println("9. Add Gallery");
                System.out.println("10. Update Gallery");
                System.out.println("11. Get Gallery By ID");
                System.out.println("12. Remove Gallery");
                System.out.println("13. Search Galleries");
                System.out.println("14. Exit");
                System.out.print("Enter your choice: ");


                int choice = ArtworkManager.getIntegerInput(scanner);

                switch (choice) {
                    case 1:
                        ArtworkManager.addArtwork(galleryService, scanner);
                        break;
                    case 2:
                        ArtworkManager.updateArtwork(galleryService,scanner);
                        break;
                    case 3:
                        ArtworkManager.getArtworkById(galleryService,scanner);
                        break;
                    case 4:
                        ArtworkManager.removeArtwork(galleryService,scanner);
                        break;
                    case 5:
                        ArtworkManager.searchArtworks(galleryService,scanner);
                        break;
                    case 6:
                        ArtworkManager.addArtworkToFavorite(galleryService,scanner);
                        break;
                    case 7:
                        ArtworkManager.removeArtworkFromFavorite(galleryService,scanner);
                        break;
                    case 8:
                        ArtworkManager.getUserFavoriteArtworks(galleryService,scanner);
                        break;
                    case 9:
                        galleryManager.addGallery();
                        break;
                    case 10:
                        galleryManager.updateGallery();
                        break;
                    case 11:
                        galleryManager.getGalleryById();
                        break;
                    case 12:
                        galleryManager.removeGallery();
                        break;
                    case 13:
                        galleryManager.searchGalleries();
                        break;
                    case 14:
                        System.out.println("Exiting...");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }

                if (choice == 14) {
                    break; // Exit the program
                }

                System.out.print("Press Enter to continue...");
                scanner.nextLine(); // Wait for Enter key press to continue
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}