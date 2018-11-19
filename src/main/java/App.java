
import io.*;
import data_access.*;
import domain.*;
import domain.Video;


public class App {

    private IO io;
    private ItemDao itemDao;

    public App(IO io, ItemDao itemDao) {
        this.io = io;
        this.itemDao = itemDao;
    }

    public void run() {
        printGreeting();
        
		boolean goOn = true;
		while (goOn) {           
			String command = askForCommand();
			io.print("");
			
			switch(command) {
				case "quit": 
					goOn = false;
					break;
					
				case "":
					goOn = false;
					break;
					
				case "new":
					addItem();
					break;
					
				case "list":
					listItems();
					break;
					
				default:
					io.print("unknown option");
					break;
			}
			io.print("");
        }
		
		printGoodbye();
    }
	
	private String askForCommand() {
		return io.readLine("quit = quit the application\n"
				+ "new = add a new item\n"
				+ "list = list items");
	}
	
	private void printGreeting(){
		io.print("Welcome to the CS literature recommendation system!");
    }
	
	private void addItem() {
		String type = io.readLine("Type: (book, video, blog post, podcast)");
		
		switch(type) {
			case "book":
				addBook();
				break;
				
			case "video":
				addVideo();
				break;
				
			case "blog post":
				addBlogPost();
				break;
				
			case "podcast":
				addPodcast();
				break;
				
			default:
				io.print("unknown type");
		}
	}
	
	private void addBook() {
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
	
	private void addVideo() {
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
	
	private void addBlogPost() {
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
	
	private void addPodcast() {
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
	
	private void listItems() {
		if (itemDao.getItems().isEmpty()) {
			io.print("No items added yet");
		} else {
			for (Item item : itemDao.getItems()) {
				io.print(item.toString());
			}
		}
		
	}
	
	private void printGoodbye() {
		io.print("Thank you for using Lukuvinkkikirjasto, hope to see you soon!");
	}

    public static void main(String[] args) {
        ItemDao itemDao = new InMemoryItemDao();
        IO io = new TextIO();
        new App(io, itemDao).run();
    }    
   
}
