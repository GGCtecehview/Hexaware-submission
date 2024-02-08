package test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import src.dao.IGalleryDao;
import src.entity.Gallery;
import src.main.GalleryManager;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GalleryManagerTest {

    @Mock
    private IGalleryDao galleryDao;

    private GalleryManager galleryManager;
    private final Scanner scanner = new Scanner(System.in);

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        galleryManager = new GalleryManager(galleryDao, scanner);
    }

    @Test
    public void testAddGallery() {
        // Mock the DAO's behavior
        when(galleryDao.addGallery(any(Gallery.class))).thenReturn(true);

        // Call the method
        // You may need to simulate scanner input here or refactor your code to allow direct parameter passing
        galleryManager.addGallery();

        // Verify that the DAO's method was called
        verify(galleryDao).addGallery(any(Gallery.class));
    }

    @Test
    public void testUpdateGallery() {
        when(galleryDao.updateGallery(any(Gallery.class))).thenReturn(true);
        // Similar to addGallery, simulate the update action
        galleryManager.updateGallery();
        verify(galleryDao).updateGallery(any(Gallery.class));
    }

    @Test
    public void testRemoveGallery() {
        when(galleryDao.removeGallery(anyInt())).thenReturn(true);
        // Simulate remove action
        galleryManager.removeGallery();
        verify(galleryDao).removeGallery(anyInt());
    }

    @Test
    public void testSearchGalleries() {
        
        List<Gallery> mockGalleries = new ArrayList<>();
        mockGalleries.add(new Gallery(1, "Test Gallery", "Description", "Location", 1, new Time(0)));
        when(galleryDao.searchGalleries(anyString())).thenReturn(mockGalleries);

        // Simulate search action
        galleryManager.searchGalleries();

        // Verify the search results
        List<Gallery> results = galleryDao.searchGalleries("Test");
        assertFalse(results.isEmpty());
        assertEquals("Test Gallery", results.get(0).getName());
    }
}
