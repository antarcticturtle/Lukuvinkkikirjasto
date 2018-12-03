
import io.*;
import data_access.*;
import item.*;

public class App {

    private IO io;
    private ItemDao itemDao;
    private ItemController itemController;

    public App(IO io, ItemDao itemDao, ItemController itemController) {
        this.io = io;
        this.itemDao = itemDao;
        this.itemController = itemController;
    }

    public void run() {
        printGreeting();

        boolean goOn = true;
        while (goOn) {
			io.print("");
            String command = askForCommand();
            io.print("");

            switch (command) {
                case "quit":
                    goOn = false;
                    break;

                case "":
                    goOn = false;
                    break;
					
				case "help":
					printCommands();
					break;

                case "new":
                    addItem();
                    break;

                case "list":
                    this.itemController.listItems("");
                    break;

                case "list by":
                    this.itemController.sortItems();
                    break;
					
				case "details":
					this.itemController.detailedItemInformation();
					break;

                case "edit":
                    itemController.editItem();
                    break;

                case "search":
                    itemController.searchItems();
                    break;

                case "delete":
                    itemController.deleteItem();
                    break;

                default:
                    io.print("unknown option");
                    break;
            }
            //io.print("");
        }

        printGoodbye();
    }

    private String askForCommand() {
        return io.readLine("Next command (write 'help' to see the commands):");
    }
	
	private void printCommands() {
		io.print("quit = quit the application\n"
				+ "help = see these commands\n"
                + "new = add a new item\n"
                + "list = list items\n"
                + "list by = sort and list items\n"
				+ "details = see more detailed information of an item\n"
                + "edit = edit item\n"
                + "search = search items\n"
                + "delete = delete item");
	}

    private void printGreeting() {
        io.print("Welcome to the CS literature recommendation system!");
		io.print("Write 'help' to see the commands.");
    }

    private void addItem() {
        String type = io.readLine("Type: (book, video, blog post, podcast)");

        switch (type) {
            case "book":
                itemController.addBook();
                break;

            case "video":
                itemController.addVideo();
                break;

            case "blog post":
                itemController.addBlogPost();
                break;

            case "podcast":
                itemController.addPodcast();
                break;

            default:
                io.print("unknown type");
        }
    }

    private void printGoodbye() {
        io.print("Thank you for using Lukuvinkkikirjasto, hope to see you soon!");
    }

    public static void main(String[] args) throws Exception {
        Database database = new Database("jdbc:sqlite:items.db");
        database.init();
        ItemDao itemDao = new DatabaseItemDao(database);
        IO io = new TextIO();
        ItemController itemController = new ItemController(itemDao, io);
        new App(io, itemDao, itemController).run();
    }

}
