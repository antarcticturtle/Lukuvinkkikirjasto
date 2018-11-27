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
            for (Item item : items) {
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
            if (item != null) {
                io.print("Deleted item " + item.getTitle());
            } else {
                io.print("Invalid id");
            }
        } catch (Exception exception) {
            io.print("Invalid id");
        }
    }

    public List<String> addItem() {
        List<String> args = new ArrayList<String>();
        String title = io.readLine("Title: ");
        while (title.trim().equals("")) {
            title = io.readLine("Please enter a valid title");
        }
        while (title.length() > 50) {
            title = io.readLine("Maximum length for title is 50 characters. Try again: ");
        }
        args.add(title);
        String author = io.readLine("Author (leave empty to skip): ");
        while (author.length() > 50) {
            author = io.readLine("Maximum length for author is 50 characters. Try again: ");
        }
        args.add(author);
        String url = io.readLine("Url (leave empty to skip): ");
        while (url.length() > 500) {
            url = io.readLine("Maximum length for url is 500 characters. Try again: ");
        }
        args.add(url);
        String description = io.readLine("Description (leave empty to skip): ");
        while (description.length() > 500) {
            description = io.readLine("Maximum length for description is 500 characters. Try again: ");
        }
        args.add(description);
        return args;
    }

    public void editItem() {
        listItems();
		
        String id = askUserForId();
		if(id.equals("")) return;	//cancel edit if id is empty
		
		String field = askUserForField();		
        String newValue = io.readLine("Enter a new value");
       
        itemDao.editItem(Integer.parseInt(id), field, newValue);
    }
	
	private String askUserForId() {
		Item item = null;
		String id = "";
        while (item == null) {
			id = io.readLine("Select the id of the item you want to edit (leave empty to cancel)");
			
			if(id.equals("")) return "";
			
			try {
				Integer int_id = Integer.parseInt(id.trim());
				item = itemDao.getItemById(int_id);
			} catch (Exception e) {
				io.print("Please enter a number");
				continue;
			}

			if (item == null) {
				io.print("Please enter a valid id");
			}
        }
		return id;
	}
	
	private String askUserForField() {
		String field;
		while(true) {
			//TODO: Can't change isbn
			field = io.readLine("Select the field name you want to edit (title, author, url, description)");
			if(isValidField(field)) {
				break;
			} else {
				io.print("Please enter a valid field");
			}
		}
		return field;
	}
	
	private boolean isValidField(String field) {
		if(field.equals("title") ||
				field.equals("author") ||
				field.equals("url") ||
				field.equals("description")) {
			return true;
		}
		
		return false;
	}

    public void addBook() {
        List<String> itemArgs = addItem();
        String isbn = io.readLine("ISBN (leave empty to skip):");
        while (isbn.length() > 20) {
            isbn = io.readLine("Maximum length for isbn is 20 characters. Try again: ");
        } 
        Book book = new Book(-1, itemArgs.get(0), itemArgs.get(1), itemArgs.get(2), itemArgs.get(3));
        if (!isbn.equals("")) {
            book.setIsbn(isbn);
        }
        itemDao.addItem(book);
    }

    public void addVideo() {
        List<String> itemArgs = addItem();
        Video video = new Video(-1, itemArgs.get(0), itemArgs.get(1), itemArgs.get(2), itemArgs.get(3));
        itemDao.addItem(video);
    }

    public void addBlogPost() {
        List<String> itemArgs = addItem();
        BlogPost blogPost = new BlogPost(-1, itemArgs.get(0), itemArgs.get(1), itemArgs.get(2), itemArgs.get(3));
        itemDao.addItem(blogPost);
    }

    public void addPodcast() {
        List<String> itemArgs = addItem();
        Podcast podCast = new Podcast(-1, itemArgs.get(0), itemArgs.get(1), itemArgs.get(2), itemArgs.get(3));
        itemDao.addItem(podCast);
    }
}
