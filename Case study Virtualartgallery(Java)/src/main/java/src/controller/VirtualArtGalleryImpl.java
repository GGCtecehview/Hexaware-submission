package src.controller;

import src.dao.IVirtualArtGallery;
import src.entity.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * Implements the IVirtualArtGallery interface to manage artwork-related operations.
 * This class handles database interactions for adding, updating, removing, querying, 
 * and managing user favorites for artworks.
 */
public class VirtualArtGalleryImpl implements IVirtualArtGallery {

	Scanner scanner = new Scanner(System.in);
    private Connection connection;
    /**
     * Constructs a VirtualArtGalleryImpl with a specific database connection.
     *
     * @param connection the database connection used for artwork data operations
     */
    public VirtualArtGalleryImpl(Connection connection) {
        this.connection = connection;
    }
    /**
     * Adds a new artwork to the database.
     *
     * @param artwork the Artwork object to be added
     * @return true if the artwork is successfully added; false otherwise
     */

    @Override
    public boolean addArtwork(Artwork artwork) {
        String sql = "INSERT INTO artwork (artworkID, title, description, creationDate, medium, imageURL, artistID) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, artwork.getArtworkID());
            statement.setString(2, artwork.getTitle());
            statement.setString(3, artwork.getDescription());
            statement.setDate(4, new java.sql.Date(artwork.getCreationDate().getTime()));
            statement.setString(5, artwork.getMedium());
            statement.setString(6, artwork.getImageURL());
            statement.setInt(7, artwork.getArtistID());

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLIntegrityConstraintViolationException e) {
            if (e.getMessage().contains("foreign key constraint")) {
                System.out.println("Failed to add artwork. The artist ID does not exist.");
            } else {
                System.out.println("Failed to add artwork due to a data integrity issue.");
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * Updates an existing artwork in the database.
     *
     * @param artwork the Artwork object to be updated
     * @return true if the artwork is successfully updated; false otherwise
     */

    @Override
    public boolean updateArtwork(Artwork artwork) {
        String sql = "UPDATE artwork SET title = ?, description = ?, creationDate = ?, medium = ?, imageURL = ?, artistID = ? WHERE artworkID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, artwork.getTitle());
            statement.setString(2, artwork.getDescription());
            statement.setDate(3, new java.sql.Date(artwork.getCreationDate().getTime()));
            statement.setString(4, artwork.getMedium());
            statement.setString(5, artwork.getImageURL());
            statement.setInt(6, artwork.getArtistID());
            statement.setInt(7, artwork.getArtworkID());

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLIntegrityConstraintViolationException e) {
            if (e.getMessage().contains("foreign key constraint")) {
                System.out.println("Failed to update artwork. The artist ID does not exist or is invalid.");
            } else {
                System.out.println("Failed to update artwork due to a data integrity issue.");
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * Removes an artwork from the database.
     *
     * @param artworkID the ID of the artwork to be removed
     * @return true if the artwork is successfully removed; false otherwise
     */

    @Override
    public boolean removeArtwork(int artworkID) {
        String checkSql = "SELECT COUNT(*) FROM artwork_gallery WHERE ArtworkID = ?";
        String deleteSql = "DELETE FROM artwork WHERE artworkID = ?";
        
        try (PreparedStatement checkStmt = connection.prepareStatement(checkSql)) {
            checkStmt.setInt(1, artworkID);
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                System.out.println("Cannot remove artwork as it is referenced in other tables.");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        try (PreparedStatement deleteStmt = connection.prepareStatement(deleteSql)) {
            deleteStmt.setInt(1, artworkID);
            int rowsAffected = deleteStmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    /**
     * Retrieves an artwork from the database by its ID.
     *
     * @param artworkID the ID of the artwork to retrieve
     * @return the Artwork object if found; null otherwise
     */


    @Override
    public Artwork getArtworkById(int artworkID)  {
        String sql = "SELECT * FROM artwork WHERE artworkID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, artworkID);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Artwork artwork = new Artwork();
                    artwork.setArtworkID(resultSet.getInt("artworkID"));
                    artwork.setTitle(resultSet.getString("title"));
                    artwork.setDescription(resultSet.getString("description"));
                    artwork.setMedium(resultSet.getString("medium"));               
                    return artwork;
                }
            }
        } catch (SQLException e) {
            // Handle exceptions
            e.printStackTrace();
        }
        return null;
    }
    /**
     * Searches for artworks in the database that match the given keyword in their title or description.
     *
     * @param keyword the keyword to search for in artwork titles and descriptions
     * @return a list of Artwork objects that match the search criteria
     */

    @Override
    public List<Artwork> searchArtworks(String keyword) {
        List<Artwork> artworks = new ArrayList<>();
        String sql = "SELECT * FROM artwork WHERE title LIKE ? OR description LIKE ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, "%" + keyword + "%");
            statement.setString(2, "%" + keyword + "%");

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Artwork artwork = new Artwork();
                    artwork.setArtworkID(resultSet.getInt("artworkID"));
                    artwork.setTitle(resultSet.getString("title"));
                    
                    artworks.add(artwork);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return artworks;
    }
    /**
     * Adds an artwork to a user's list of favorites.
     *
     * @param userId the ID of the user
     * @param artworkId the ID of the artwork to be added to favorites
     * @return true if the artwork is successfully added to favorites; false otherwise
     */

    @Override
    public boolean addArtworkToFavorite(int userId, int artworkId) {
        
        String artworkCheckSql = "SELECT COUNT(*) FROM artwork WHERE artworkID = ?";
        try (PreparedStatement artworkCheckStmt = connection.prepareStatement(artworkCheckSql)) {
            artworkCheckStmt.setInt(1, artworkId);
            ResultSet rsArtwork = artworkCheckStmt.executeQuery();
            if (rsArtwork.next() && rsArtwork.getInt(1) == 0) {
                System.out.println("Artwork does not exist.");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

       
        String userCheckSql = "SELECT COUNT(*) FROM user WHERE userID = ?";
        try (PreparedStatement userCheckStmt = connection.prepareStatement(userCheckSql)) {
            userCheckStmt.setInt(1, userId);
            ResultSet rsUser = userCheckStmt.executeQuery();
            if (rsUser.next() && rsUser.getInt(1) == 0) {
                System.out.println("User does not exist.");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        
        String sql = "INSERT INTO user_favorite_artwork (userID, artworkID) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId);
            statement.setInt(2, artworkId);

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            if (e instanceof SQLIntegrityConstraintViolationException) {
                System.out.println("This artwork is already in the user's favorites.");
            } else {
                e.printStackTrace();
            }
            return false;
        }
    }
    /**
     * Removes an artwork from a user's list of favorites.
     *
     * @param userId the ID of the user
     * @param artworkId the ID of the artwork to be removed from favorites
     * @return true if the artwork is successfully removed from favorites; false otherwise
     */



        @Override
        public boolean removeArtworkFromFavorite(int userId, int artworkId) {
            String sql = "DELETE FROM user_favorite_artwork WHERE userID = ? AND artworkID = ?";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, userId);
                statement.setInt(2, artworkId);

                int rowsAffected = statement.executeUpdate();
                return rowsAffected > 0;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return false;
        }
        /**
         * Retrieves a list of a user's favorite artworks.
         *
         * @param userId the ID of the user whose favorite artworks are to be retrieved
         * @return a list of Artwork objects that are in the user's list of favorites
         */

    @Override
    public List<Artwork> getUserFavoriteArtworks(int userId) {
        List<Artwork> favoriteArtworks = new ArrayList<>();
        String sql = "SELECT a.* FROM artwork a JOIN user_favorite_artwork uf ON a.artworkID = uf.artworkID WHERE uf.userID = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Artwork artwork = new Artwork();
                    artwork.setArtworkID(resultSet.getInt("artworkID"));
                    artwork.setTitle(resultSet.getString("title"));
                    artwork.setDescription(resultSet.getString("description"));
                    artwork.setMedium(resultSet.getString("medium"));
                    favoriteArtworks.add(artwork);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return favoriteArtworks;
    }

 
}
