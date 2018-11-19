
package domain;

import java.util.ArrayList;
import java.util.List;

public class BlogPost implements Item {
	private String title;
	private String url;
	private String description;
	private String author;
	private List<String> tags;
	

	public BlogPost(String title, String url) {
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
		return "Blog post: " + this.title + " by " + this.author;
	}
	
}
