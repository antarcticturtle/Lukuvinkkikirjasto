package domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class BookTest {
    
    Book book;
    
    @Before
    public void setUp() {
        book = new Book("Name", "Author");
    }
    
    @Test 
    public void bookIsCreatedWithNameAndAuthor() {
        Book book = new Book("Name", "Author");
        assertEquals("Name by Author", book.toString());
    }
    
    @Test 
    public void aTagCanBeAdded() {
        book.addTag("tag1");
        assertEquals("tag1", book.getTags().get(0));
    }
    

}
