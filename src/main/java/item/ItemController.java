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

    public void searchItems() {
        String search = io.readLine("Search the library");
        io.print("No items found");
    }

    public void deleteItem() {
        for (Item item : itemDao.getItems("")) {
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
        listItems("");

        String id = askUserForId();
        if (id.equals("")) {
            return;	//cancel edit if id is empty
        }
        String field = askUserForField(id);
        String newValue = io.readLine("Enter a new value");

        itemDao.editItem(Integer.parseInt(id), field, newValue);
    }

    private String askUserForId() {
        Item item = null;
        String id = "";
        while (item == null) {
            id = io.readLine("Select the id of the item you want to edit (leave empty to cancel)");

            if (id.equals("")) {
                return "";
            }

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

    private String askUserForField(String id) {
        Item item = itemDao.getItemById(Integer.parseInt(id));
        String field;
        while (true) {
            if (item.getClass() == Book.class) {
                field = io.readLine("Select the field name you want to edit (title, author, url, description, isbn)");
            } else {
                field = io.readLine("Select the field name you want to edit (title, author, url, description)");
            }
            if (isValidField(field, item)) {
                break;
            } else {
                io.print("Please enter a valid field");
            }
        }
        return field;
    }

    private boolean isValidField(String field, Item item) {
        if (field.equals("title")
                || field.equals("author")
                || field.equals("url")
                || field.equals("description")
                || (field.equals("isbn") && item.getClass() == Book.class)) {
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

    public void sortItems() {
        String sortby = io.readLine("Select an option to sort the list (title/type and title)");
        if (sortby.equals("title")) {
            this.listItems("title");
        } else if (sortby.equals("type and title")) {
            this.listItems("type, title");
        } else {
            io.print("Invalid option");
        }
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
