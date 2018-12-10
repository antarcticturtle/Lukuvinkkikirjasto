package item;

import io.Color;
import io.IO;
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
    
    public Boolean getRead() {
        return this.read;
    }
 
    public void setRead(Boolean read) {
        this.read = read;
    }

    @Override
    public List<String> getTags() {
        return this.tags;
    }

    @Override
    public void addTag(String tag) {
        this.tags.add(tag);
    }

	public String isRead() {
        return this.read ? "true" : "false";
        // if (this.read) {
        //     return "true";
        // } else {
        //     return "false";
        // }
	}

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
		
        sb.append("(id: ").append(Color.yellow(Integer.toString(this.id))).append(") ");
        sb.append(this.getClass().getSimpleName());
        sb.append(": ");
        sb.append(Color.cyan(this.getTitle()));
        if (!this.getAuthor().equals("")) {
            sb.append(" by ");
            sb.append(Color.cyan(this.getAuthor()));
        }

        return sb.toString();
    }
	
	@Override
	public String detailedToString() {
        StringBuilder sb = new StringBuilder();
		
		sb.append(this.toString()).append("\n");
		sb.append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		sb.append(String.format("%-15s", "Type:"));
		sb.append(this.getClass().getSimpleName());
		sb.append("\n");
		
		sb.append(String.format("%-15s", "Title:"));
		sb.append(this.getTitle());
		sb.append("\n");

		sb.append(String.format("%-15s", "Author:"));
		sb.append(this.getAuthor());
		sb.append("\n");

		sb.append(String.format("%-15s", "URL:"));
		sb.append(this.getUrl());
		sb.append("\n");

		sb.append(String.format("%-15s", "Description:"));
		sb.append(this.getDescription());
		sb.append("\n");

		sb.append(String.format("%-15s", "Read:"));
		sb.append(this.isRead());
		
		return sb.toString();
    }
    
}
