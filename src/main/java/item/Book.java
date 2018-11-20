package item;

import java.util.ArrayList;
import java.util.List;

public class Book implements Item{
    private String title;
    private String author;
    private String url;
    private String isbn;
    private List<String> tags;
    private String description;
    
    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.tags = new ArrayList<>();
    }    

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }    
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getTags() {
        return tags;
    }
    
    public void addTag(String tag) {
        this.tags.add(tag);
    }

    @Override
    public String toString() {
        return "Book: " + this.title + " by " + this.author;
    }
    
}
