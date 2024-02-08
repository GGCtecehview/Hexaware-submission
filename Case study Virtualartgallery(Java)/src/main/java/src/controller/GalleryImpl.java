package src.controller;
import src.dao.IGalleryDao;
import src.entity.Gallery;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implements the IGalleryDao interface to provide database operations for galleries.
 * This class handles the CRUD operations for gallery data in the Virtual Art Gallery application.
 */
public class GalleryImpl implements IGalleryDao {
    private Connection connection;

    /**
     * Constructs a GalleryImpl with a specific database connection.
     *
     * @param connection the database connection used for gallery data operations
     */

    public GalleryImpl(Connection connection) {
        this.connection = connection;
    }
    /**
     * Adds a new gallery to the database.
     *
     * @param gallery the Gallery object to be added
     * @return true if the gallery is successfully added; false otherwise
     */
    @Override
    public boolean addGallery(Gallery gallery) {
        String sql = "INSERT INTO gallery (galleryID, name, description, location, curator, Openinghours) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, gallery.getGalleryId());
            statement.setString(2, gallery.getName());
            statement.setString(3, gallery.getDescription());
            statement.setString(4, gallery.getLocation());
            statement.setInt(5, gallery.getCuratorId());
            statement.setTime(6, gallery.getOpeningTime());

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Failed to add gallery. The curator ID provided does not exist.");
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    
    /**
     * Updates an existing gallery in the database.
     *
     * @param gallery the Gallery object to be updated
     * @return true if the gallery is successfully updated; false otherwise
     */
  
    @Override
    public boolean updateGallery(Gallery gallery) {
        String sql = "UPDATE gallery SET name = ?, description = ?, location = ?, curator = ?, OpeningHours = ? WHERE galleryId = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, gallery.getName());
            statement.setString(2, gallery.getDescription());
            statement.setString(3, gallery.getLocation());
            statement.setInt(4, gallery.getCuratorId());
            statement.setTime(5, gallery.getOpeningTime());
            statement.setInt(6, gallery.getGalleryId());

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * Removes a gallery from the database.
     *
     * @param galleryId the ID of the gallery to be removed
     * @return true if the gallery is successfully removed; false otherwise
     */

    @Override
    public boolean removeGallery(int galleryId) {
        String sql = "DELETE FROM gallery WHERE galleryId = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, galleryId);

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Retrieves a gallery from the database by its ID.
     *
     * @param galleryId the ID of the gallery to retrieve
     * @return the Gallery object if found; null otherwise
     */

    @Override
    public Gallery getGalleryById(int galleryId) {
        String sql = "SELECT * FROM gallery WHERE GalleryId = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, galleryId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Gallery gallery = new Gallery();
                    gallery.setGalleryId(resultSet.getInt("galleryid"));
                    gallery.setName(resultSet.getString("name"));
                    gallery.setDescription(resultSet.getString("description"));
                    gallery.setLocation(resultSet.getString("location"));
                    gallery.setCuratorId(resultSet.getInt("curator"));
                  
                    return gallery;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * Searches for galleries in the database that match the given keyword in their name or description.
     *
     * @param keyword the keyword to search for in gallery names and descriptions
     * @return a list of Gallery objects that match the search criteria
     */

    @Override
    public List<Gallery> searchGalleries(String keyword) {
        String sql = "SELECT * FROM gallery WHERE name LIKE ? OR description LIKE ?";
        List<Gallery> galleries = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, "%" + keyword + "%");
            statement.setString(2, "%" + keyword + "%");

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Gallery gallery = new Gallery();
                    gallery.setGalleryId(resultSet.getInt("galleryid"));
                    gallery.setName(resultSet.getString("name"));
                    gallery.setDescription(resultSet.getString("description"));
                    gallery.setLocation(resultSet.getString("location"));
                    gallery.setCuratorId(resultSet.getInt("curator"));
                    galleries.add(gallery);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return galleries;
    }
}
