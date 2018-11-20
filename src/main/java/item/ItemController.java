package item;

import data_access.ItemDao;
import java.util.List;

import data_access.*;
import io.*;

public class ItemController {

    private ItemDao itemDao;
    private IO io;

    public ItemController(ItemDao itemDao, IO io) {
        this.itemDao = itemDao;
        this.io = io;
    }

    public void listItems() {
        List<Item> items = itemDao.getItems();
		if (items.isEmpty()) {
			io.print("No items added yet");
		} else {
            for(Item item : items) {
                io.print(item.toString());
            }
		}
    }
    
    public void addBook() {
        String title = io.readLine("Title:");
		String author = io.readLine("Author:");
		Book book = new Book(title, author);
		
		String url = io.readLine("url link (leave empty to skip):");
		if (!url.equals("")) {
			book.setUrl(url);
		}
		
		String isbn = io.readLine("ISBN (leave empty to skip):");
		if (!isbn.equals("")) {
			book.setIsbn(isbn);
		}
		
		String description = io.readLine("Description (leave empty to skip):");
		if (!description.equals("")) {
			book.setDescription(description);
		}
		
		while (true) {
			String tag = io.readLine("add a tag (leave empty to stop):");
			if (tag.equals("")) {
				break;
			} else {
				book.addTag(tag);
			}
		}
		itemDao.addItem(book);
    }

    public void addVideo() {
    
		String title = io.readLine("Title:");
		String url = io.readLine("URL:");
		Item video = new Video(title, url);
		
		String author = io.readLine("Author (leave empty to skip):");
		if (!author.equals("")) {
			video.setAuthor(author);
		}
		
		String description = io.readLine("Description (leave empty to skip):");
		if (!description.equals("")) {
			video.setDescription(description);
		}
		
		while (true) {
			String tag = io.readLine("add a tag (leave empty to stop):");
			if (tag.equals("")) {
				break;
			} else {
				video.addTag(tag);
			}
		}
        itemDao.addItem(video);
    }

    public void addBlogPost() {
		String title = io.readLine("Title:");
		String url = io.readLine("URL:");
		Item blogPost = new BlogPost(title, url);
		
		String author = io.readLine("Author (leave empty to skip):");
		if (!author.equals("")) {
			blogPost.setAuthor(author);
		}
		
		String description = io.readLine("Description (leave empty to skip):");
		if (!description.equals("")) {
			blogPost.setDescription(description);
		}
		
		while (true) {
			String tag = io.readLine("add a tag (leave empty to stop):");
			if (tag.equals("")) {
				break;
			} else {
				blogPost.addTag(tag);
			}
		}
		itemDao.addItem(blogPost);
    }
    
    public void addPodcast() {
		String podcastName = io.readLine("Podcast name:");
		String title = io.readLine("Title:");
		String description = io.readLine("Description:");
		Item podcast = new Podcast(podcastName, title, description);
		
		String author = io.readLine("Author (leave empty to skip):");
		if (!author.equals("")) {
			podcast.setAuthor(author);
		}
		
		String url = io.readLine("url link (leave empty to skip):");
		if (!url.equals("")) {
			podcast.setUrl(url);
		}
		
		while (true) {
			String tag = io.readLine("add a tag (leave empty to stop):");
			if (tag.equals("")) {
				break;
			} else {
				podcast.addTag(tag);
			}
		}
		itemDao.addItem(podcast);
	}
}