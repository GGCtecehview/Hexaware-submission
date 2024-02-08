package src.controller;
import src.dao.IGalleryDao;
import src.entity.Gallery;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GalleryImpl implements IGalleryDao {
    private Connection connection;

    public GalleryImpl(Connection connection) {
        this.connection = connection;
    }

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
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

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
                    gallery.setOpeningTime(resultSet.getTime("Openinghours"));
                    return gallery;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

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
