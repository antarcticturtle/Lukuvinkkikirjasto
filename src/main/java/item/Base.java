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

	public boolean isRead() {
		return read;
	}

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("(id: ").append(this.id).append(") ");
        sb.append(this.getClass().getSimpleName());
        sb.append(": ");
        sb.append(this.getTitle());
        if (!this.getAuthor().equals("")) {
            sb.append(" by ");
            sb.append(this.getAuthor());
        }

        return sb.toString();
    }
	
	@Override
	public String detailedToString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getLineOfAsterisks());
		sb.append(this.toString()).append("\n");
		sb.append(getLineOfAsterisks());
				
		sb.append("Type:\t\t").append(this.getClass().getSimpleName()).append("\n");
		
        sb.append("Title:\t\t").append(this.getTitle()).append("\n");		
		sb.append("Author:\t\t").append(this.getAuthor()).append("\n");
		sb.append("URL:\t\t").append(this.getUrl()).append("\n");
		sb.append("Description:\t").append(this.getDescription()).append("\n");
		sb.append("Read:\t\t").append(this.isRead()).append("\n");	
		
		sb.append(getLineOfAsterisks());
		
		return sb.toString();
	}
	
	/**
	 * 
	 * @return A line of asterisk characters to use as a divider 
	 *         when printing item information
	 */
	protected String getLineOfAsterisks() {
		return "******************************************************\n";
	}
    
}
