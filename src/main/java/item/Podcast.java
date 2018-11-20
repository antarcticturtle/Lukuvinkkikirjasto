package item;

import java.util.ArrayList;
import java.util.List;

public class Podcast implements Item {
	private String podcastName;
	private String title;
	private String description;
	private List<String> tags;
	private String url;
	private String author;

	public Podcast(String podcastName, String title, String description) {
		this.podcastName = podcastName;
		this.title = title;
		this.description = description;
		this.tags = new ArrayList<>();
	}
	
	@Override
	public String getTitle() {
		return title;
	}
	
	@Override
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Override
	public String getAuthor() {
		return author;
	}
	
	@Override
	public void setAuthor(String author) {
		this.author = author;
	}
	
	@Override
	public String getDescription() {
		return description;
	}
		
	@Override
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String getUrl() {
		return url;
	}
	
	@Override
	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public List<String> getTags() {
		return tags;
	}

	@Override
	public void addTag(String tag) {
		this.tags.add(tag);
	}
	
	@Override
	public String toString() {
		return "Podcast: " + this.title + " by " + this.podcastName;
	}
	
}
