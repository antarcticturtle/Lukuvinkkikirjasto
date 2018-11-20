package item;

import java.util.ArrayList;
import java.util.List;

public class Book extends Base{
    private String isbn;
    
    public Book(String title, String url) {
        super(title, url);
        this.setAuthor(url);
    }
    
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public String toString() {
        return "Book: " + this.getTitle() + " by " + this.getAuthor();
    }
    
}
