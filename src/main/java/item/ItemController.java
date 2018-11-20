package item;

import data_access.ItemDao;
import java.util.List;
import java.util.ArrayList;

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
	
	public List<String> addItem() {
		List<String> args = new ArrayList<String>();
		args.add(io.readLine("Title: "));
		args.add(io.readLine("Author: "));
		args.add(io.readLine("Url: "));
		return args;
	}
    
    public void addBook() {
		List<String> itemArgs = addItem();
		String isbn = io.readLine("ISBN (leave empty to skip):");
		Book book = new Book(itemArgs.get(0), itemArgs.get(1), itemArgs.get(2));
		if (!isbn.equals("")) {
			book.setIsbn(isbn);
		}
		itemDao.addItem(book);
    }

    public void addVideo() {
    
		String title = io.readLine("Title:");
		String url = io.readLine("Author:");
		String author = io.readLine("Url:");
		Item video = new Video(title, author, url);
		
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
		String author = io.readLine("Author:");
		String url = io.readLine("Url:");
		Item blogPost = new BlogPost(title, author, url);
		
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