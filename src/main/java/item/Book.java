package item;

import java.util.ArrayList;
import java.util.List;

public class Book extends Base implements Item{
    private String isbn;
    
    public Book(String title, String author, String url) {
        super(title, author, url);
    }
    
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Book: ");
        sb.append(this.getTitle());
        sb.append(" by ");
        sb.append(this.getAuthor());
        sb.append(" ");
        sb.append(this.getUrl());
        if (isbn != null) {
            sb.append(" Isbn: ");
            sb.append(this.getIsbn());
        }
        return sb.toString();
    }
    
}
