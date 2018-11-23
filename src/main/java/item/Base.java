package item;

import java.util.ArrayList;
import java.util.List;

public class Base implements Item {
	private String url;
	private String title;
	private String author;
	private String description;
	private List<String> tags;	
	
	public Base(String title, String author, String url) {
		this.title = title;
        this.author = author;
        this.url = url;
		this.tags = new ArrayList<>();
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
		sb.append(this.getClass().getSimpleName());
		sb.append(": ");
		sb.append(this.getTitle());
		if (this.author != null) {
			sb.append(" by ");
			sb.append(this.getAuthor());
		}

		if (this.url != null) {
			sb.append(" ");
			sb.append("Url: ");
			sb.append(this.getUrl());
		}

        if (this.getDescription() != null) {
			sb.append(" ");
			sb.append("Description: ");
            sb.append(this.getDescription());
		}
		return sb.toString();
		// Book: Title by Author Url: url Description: New Description Isbn: isbn
		// Book: t by aa Url: u Description: d Isbn: i
		// Book: t by aa Url: u Description: New Description Isbn: i
	}
	
}
