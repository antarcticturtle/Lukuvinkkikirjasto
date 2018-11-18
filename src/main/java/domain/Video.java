
package domain;

import java.util.ArrayList;
import java.util.List;


public class Video implements Item {
	private String url;
	private String title;
	private String description;
	private List<String> tags;
	private String author;
	
	public Video(String title, String url) {
		this.title = title;
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
		return this.title + " by " + this.author;
	}
	
}
