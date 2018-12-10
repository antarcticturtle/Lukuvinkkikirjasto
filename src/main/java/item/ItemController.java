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

    public void listItems(String sortby) {
        List<Item> items = itemDao.getItems(sortby);
        if (items.isEmpty()) {
            io.print("No items added yet");
        } else {
            if (sortby.equals("type, title")) {
                this.printSeparatedByType(items);
            } else {
                this.printNormal(items);
            }
        }
    }

    public String lengthValidator(String searchMessage, String errorMessage, int minLength, int maxLength) {
        // Generic length validator for user input
        String entry = io.readLine(searchMessage);
        while (entry.trim().length() < minLength || entry.trim().length() > maxLength) {
            entry = io.readLine(errorMessage);
        }
        return entry;
    }

    public void searchItems() {
        String search = lengthValidator("Search the library", "Please enter a keyword", 1, 50);
        List<Item> items = itemDao.searchItems(search);
        if (items.isEmpty()) {
            io.print("No items found");
        } else {
            printNormal(items);
        }
    }

    public void deleteItem() {
        this.listItems("");
        String id = this.askUserForId();
        if (id.equals("")) {
            return;	//cancel if id is empty
        }        
        Item item = itemDao.deleteItemById(Integer.parseInt(id));
        io.print("Deleted item " + item.getTitle());        
    }

    public List<String> addItem() {
        List<String> args = new ArrayList<String>();
        String title = lengthValidator("Title: ",
                "Title must contain 1-50 characters. Try again: ",
                1,
                50);
        args.add(title);
        String author = lengthValidator("Author (leave empty to skip): ",
                "Maximum length for author is 50 characters. Try again: ",
                0,
                50);
        args.add(author);

        String url = lengthValidator("Url (leave empty to skip): ",
                "Maximum length for url is 500 characters. Try again: ",
                0,
                500);
        args.add(url);

        String description = lengthValidator("Description (leave empty to skip): ",
                "Maximum length for description is 500 characters. Try again: ",
                0,
                500);
        args.add(description);
        return args;
    }

    public void editItem() {
        listItems("");
		io.print("");

        String id = askUserForId();
        if (id.equals("")) {
            return;	//cancel edit if id is empty
        }
        this.editField("title", id);
        this.editField("author", id);
        this.editField("url", id);
        this.editField("description", id);
        Item item = itemDao.getItemById(Integer.parseInt(id));
        if (item.getClass() == Book.class) {
            this.editField("isbn", id);
        }

    }

    public void readItem() {
        listItems("");
        String id = askUserForId();
        if (id.equals("")) {
            return;
        } else {
            itemDao.editItem(Integer.parseInt(id), "read", "1");
        }
    }

    private void markItemAsRead(String id) {

    }

    private void editField(String field, String id) {
        String newValue = "";
        if (field.equals("title")) {
            newValue = lengthValidator("Enter a new title (leave empty to skip)", "Please enter a valid title (max. 50 characters)", 0, 50);
        } else if (field.equals("author")) {
            newValue = lengthValidator("Enter a new author (leave empty to skip)", "Please enter a valid author (max. 50 characters)", 0, 50);
        } else if (field.equals("url")) {
            newValue = lengthValidator("Enter a new url (leave empty to skip)", "Please enter a valid url (max. 500 characters)", 0, 500);
        } else if (field.equals("description")) {
            newValue = lengthValidator("Enter a new description (leave empty to skip)", "Please enter a valid description (max. 500 characters)", 0, 500);
        } else if (field.equals("isbn")) {
            newValue = lengthValidator("Enter a new isbn (leave empty to skip)", "Please enter a valid isbn (max. 20 characters)", 0, 20);
        } else {
            newValue = io.readLine("Enter a new value");
        }
        if (!newValue.equals("")) {
            itemDao.editItem(Integer.parseInt(id), field, newValue);
        }
    }

    private String askUserForId() {
        Item item = null;
        String id = "";
        while (item == null) {
            id = io.readLine("Enter the id of the item (leave empty to cancel)");
            if (id.equals("")) {
                return "";
            }
            item = this.findItem(id);
            if (item == null) {
                io.print("Please enter a valid id");
            }
        }
        return id;
    }

    private Item findItem(String id) {
        Item item = null;
        try {
            Integer int_id = Integer.parseInt(id.trim());
            item = itemDao.getItemById(int_id);
        } catch (Exception e) {
            io.print("Please enter a number");            
        }   
        return item;
    }

    public void addBook() {
        List<String> itemArgs = addItem();
        String isbn = lengthValidator("ISBN (leave empty to skip):",
                "Maximum length for isbn is 20 characters. Try again: ",
                0,
                20);
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

    public void sortItems() {
        String sortby = io.readLine("Select an option to sort the list (\"type and title\" or \"title)\"");
        if (sortby.equals("title")) {
            this.listItems("title");
        } else if (sortby.equals("type and title")) {
            this.listItems("type, title");
        } else {
            io.print("Invalid option");
        }
    }
	
	public void detailedItemInformation() {
		listItems("");
		io.print("");
		String id = askUserForId();
        if (id.equals("")) {
            return;	//cancel if id is empty
        }
        Item item = itemDao.getItemById(Integer.parseInt(id));
        io.print(item.detailedToString());
    }

    private void printNormal(List<Item> items) {
        for (Item item : items) {
            io.print(item.toString());
        }
    }

    private void printSeparatedByType(List<Item> items) {
        Class c = null;
        for (Item item : items) {
            if (item.getClass() != c) {
                io.print("\n" + item.getClass().getSimpleName() + "s");
                c = item.getClass();
            }
            io.print(item.toString());
        }
    }
}
