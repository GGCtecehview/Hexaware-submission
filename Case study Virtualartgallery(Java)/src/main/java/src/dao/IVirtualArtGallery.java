package src.dao;
import src.entity.*;
import src.exception.ArtWorkNotFoundException;
import src.exception.UserNotFoundException;

import java.util.List;

public interface IVirtualArtGallery {
    // Artwork Management
    boolean addArtwork(Artwork artwork);
    boolean updateArtwork(Artwork artwork);
    boolean removeArtwork(int artworkID);
    List<Artwork> searchArtworks(String keyword);

    // User Favorites
    boolean addArtworkToFavorite(int userId, int artworkId);
    boolean removeArtworkFromFavorite(int userId, int artworkId);
    Artwork getArtworkById(int artworkID) throws ArtWorkNotFoundException;
    List<Artwork> getUserFavoriteArtworks(int userId) throws UserNotFoundException;

}
