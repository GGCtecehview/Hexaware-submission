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
 
        when(galleryDao.addGallery(any(Gallery.class))).thenReturn(true);


        galleryManager.addGallery();
        verify(galleryDao).addGallery(any(Gallery.class));
    }

    public void testAddGallery1() {
    	 
        when(galleryDao.addGallery(any(Gallery.class))).thenReturn(true);


        galleryManager.addGallery();
        verify(galleryDao).addGallery(any(Gallery.class));
    }
    
    @Test
    public void testUpdateGallery() {
        when(galleryDao.updateGallery(any(Gallery.class))).thenReturn(true);
        
        galleryManager.updateGallery();
        verify(galleryDao).updateGallery(any(Gallery.class));
    }

    @Test
    public void testRemoveGallery() {
        when(galleryDao.removeGallery(anyInt())).thenReturn(true);
        
        galleryManager.removeGallery();
        verify(galleryDao).removeGallery(anyInt());
    }

    @Test
    public void testSearchGalleries() {
        
        List<Gallery> mockGalleries = new ArrayList<>();
        mockGalleries.add(new Gallery(1, "Test Gallery", "Description", "Location", 1, new Time(0)));
        when(galleryDao.searchGalleries(anyString())).thenReturn(mockGalleries);

       
        galleryManager.searchGalleries();

     
        List<Gallery> results = galleryDao.searchGalleries("Test");
        assertFalse(results.isEmpty());
        assertEquals("Test Gallery", results.get(0).getName());
    }
}
