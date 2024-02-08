package src.controller;

import src.dao.IVirtualArtGallery;
import src.entity.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VirtualArtGalleryImpl implements IVirtualArtGallery {

	Scanner scanner = new Scanner(System.in);
    private Connection connection;

    public VirtualArtGalleryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean addArtwork(Artwork artwork) {
        String sql = "INSERT INTO artwork (artworkID ,title, description, creationDate, medium, imageURL, artistID) VALUES (? ,? ,?, ?, ?, ?, ?)";
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
        } catch (SQLException e) {
            // Handle exceptions
            e.printStackTrace();
        }
        return false;
    }

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
        } catch (SQLException e) {
            // Handle exceptions
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean removeArtwork(int artworkID) {
        String sql = "DELETE FROM artwork WHERE artworkID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, artworkID);

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            // Handle exceptions
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public Artwork getArtworkById(int artworkID) {
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


    @Override
    public boolean addArtworkToFavorite(int userId, int artworkId) {
        String sql = "INSERT INTO user_favorite_artwork (userID, artworkID) VALUES (?, ?)";

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
