package test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import src.controller.VirtualArtGalleryImpl;
import src.entity.Artwork;

import java.sql.Date;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ArtworkManagerTest {

    @Mock
    private VirtualArtGalleryImpl artworkDAO;

    private Artwork artwork;

    @Before
    public void setUp() {
        artwork = new Artwork(1, "Artwork Title", "Description", new Date(System.currentTimeMillis()), "Medium", "image.jpg");
    }

    @Test
    public void testAddArtwork() {
        when(artworkDAO.addArtwork(artwork)).thenReturn(true);
        boolean result = artworkDAO.addArtwork(artwork);
        assertTrue(result);
        verify(artworkDAO, times(1)).addArtwork(artwork);
    }

    @Test
    public void testUpdateArtwork() {
        when(artworkDAO.updateArtwork(artwork)).thenReturn(true);
        boolean result = artworkDAO.updateArtwork(artwork);
        assertTrue(result);
        verify(artworkDAO, times(1)).updateArtwork(artwork);
    }

    @Test
    public void testRemoveArtwork() {
        when(artworkDAO.removeArtwork(artwork.getArtworkID())).thenReturn(true);
        boolean result = artworkDAO.removeArtwork(artwork.getArtworkID());
        assertTrue(result);
        verify(artworkDAO, times(1)).removeArtwork(artwork.getArtworkID());
    }

    @Test
    public void testSearchArtworks() {
        String keyword = "Title";
        when(artworkDAO.searchArtworks(keyword)).thenReturn(java.util.Collections.singletonList(artwork));
        java.util.List<Artwork> result = artworkDAO.searchArtworks(keyword);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(artwork, result.get(0));
        verify(artworkDAO, times(1)).searchArtworks(keyword);
    }
}
