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

	public void deleteItem() {
		for (Item item : itemDao.getItems()) {
			io.print(item.toString());
		}
		String id = io.readLine("Select the id of the item you want to delete");
		try {
			Item item = itemDao.deleteItemById(Integer.parseInt(id));
			io.print("Deleted item " + item.getTitle());
		} catch (IndexOutOfBoundsException exception) {
			io.print("Invalid id");
		}
	}
	
	public List<String> addItem() {
		List<String> args = new ArrayList<String>();
		args.add(io.readLine("Title: "));
		args.add(io.readLine("Author: "));
		args.add(io.readLine("Url: "));
		return args;
	}

	public void editItem() {
		for (Item item : itemDao.getItems()) {
			io.print(item.toString());
		}
		// needs to be converted to int
		String id = io.readLine("Select the id of the item you want to edit");
		String field = io.readLine("Select the field name you want to edit");
		String new_value = io.readLine("Enter a new value");

		Item item = itemDao.deleteItemById(Integer.parseInt(id));
		if (field.equals("title")) {
			item.setTitle(new_value);
		} else if (field.equals("author")) {
			item.setAuthor(new_value);
		} else if (field.equals("url")) {
			item.setUrl(new_value);
		} else if (field.equals("description")) {
			item.setDescription(new_value);
		}
		itemDao.addItem(item);
	}
    
    public void addBook() {
		List<String> itemArgs = addItem();
		String isbn = io.readLine("ISBN (leave empty to skip):");
		String description = io.readLine("Description (leave empty to skip):");
		Book book = new Book(itemArgs.get(0), itemArgs.get(1), itemArgs.get(2));
		if (!isbn.equals("")) {
			book.setIsbn(isbn);
		}
		if (!description.equals("")) {
			book.setDescription(description);
		}
		itemDao.addItem(book);
    }

    public void addVideo() {
		List<String> itemArgs = addItem();
		Video video = new Video(itemArgs.get(0), itemArgs.get(1), itemArgs.get(2));
		itemDao.addItem(video);
    }

    public void addBlogPost() {
		List<String> itemArgs = addItem();
		BlogPost blogPost = new BlogPost(itemArgs.get(0), itemArgs.get(1), itemArgs.get(2));
		itemDao.addItem(blogPost);
    }
    
    public void addPodcast() {
		List<String> itemArgs = addItem();
		Podcast podCast = new Podcast(itemArgs.get(0), itemArgs.get(1), itemArgs.get(2));
		itemDao.addItem(podCast);
	}
}