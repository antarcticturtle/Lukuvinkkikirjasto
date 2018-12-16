package data_access;

import io.Color;
import io.IO;
import item.*;
import java.io.File;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class DatabaseItemDaoTest {
    
    File testDatabase;
    DatabaseItemDao dao;
    
    @Before
    public void setUp() throws Exception {
        testDatabase = new File("testdatabase.db");
        Database database = new Database("jdbc:sqlite:testdatabase.db");
        database.init();
        this.dao = new DatabaseItemDao(database);
        dao.addItem(new Book(1, "Title", "Author", "url", "description"));
        dao.addItem(new Podcast(2, "Title2", "Author2", "url2", "description2"));
    }
    
    @Test
    public void allItemsAreFound() {
        assertEquals(2, dao.getItems("").size());
    }
    
    @Test
    public void wrongItemIdReturnsNoItem() {
        assertEquals(null, dao.getItemById(3));
    }
    
    @Test
    public void itemCanBeFoundById() {
        assertEquals("(id: " + Color.yellow("1") + ") Book: " + Color.cyan("Title") + " by " + Color.cyan("Author"), dao.getItemById(1).toString());
    }
    
    @Test
    public void itemCanBeAdded() {
        dao.addItem(new Video(3, "Title3", "Author3", "url3", "description3"));
        assertEquals("(id: " + Color.yellow("3") + ") Video: " + Color.cyan("Title3") + " by " + Color.cyan("Author3"), dao.getItemById(3).toString());
    }
    
    @Test
    public void itemCanBeDeleted() {
        dao.deleteItemById(2);
        assertEquals(null, dao.getItemById(2));
    }
    
    @Test
    public void nothingIsDeletedIfWrongIdIsEntered() {
        assertEquals(null, dao.deleteItemById(3));        
    }
    
    @After
    public void tearDown() {
        testDatabase.delete();
    }
    
    
}