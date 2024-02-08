package src.dao;

import src.entity.Gallery;

import java.util.List;

public interface IGalleryDao {
    boolean addGallery(Gallery gallery);
    boolean updateGallery(Gallery gallery);
    boolean removeGallery(int galleryId);
    Gallery getGalleryById(int galleryId);
    List<Gallery> searchGalleries(String keyword);
}
