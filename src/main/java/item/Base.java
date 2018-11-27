package item;

import java.util.ArrayList;
import java.util.List;

public class Base implements Item {

    private int id;
    private String url;
    private String title;
    private String author;
    private String description;
    private List<String> tags;
    private boolean read;

    public Base(int id, String title, String author, String url, String description) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.url = url;
        this.description = description;
        this.tags = new ArrayList<>();
        this.read = false;
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getAuthor() {
        return this.author;
    }

    @Override
    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getUrl() {
        return this.url;
    }

    @Override
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public List<String> getTags() {
        return this.tags;
    }

    @Override
    public void addTag(String tag) {
        this.tags.add(tag);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("(id: " + this.id+ ") ");
        sb.append(this.getClass().getSimpleName());
        sb.append(": ");
        sb.append(this.getTitle());
        if (!this.getAuthor().equals("")) {
            sb.append(" by ");
            sb.append(this.getAuthor());
        }

        if (!this.getUrl().equals("")) {
            sb.append(" ");
            sb.append("Url: ");
            sb.append(this.getUrl());
        }

        if (!this.getDescription().equals("")) {
            sb.append(" ");
            sb.append("Description: ");
            sb.append(this.getDescription());
        }
        return sb.toString();
    }
    
}
