
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
			printSeparator();
            String command = askForCommand();

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

                case "read":
                    itemController.readItem();
                    break;

                default:
                    io.print("unknown option");
                    break;
            }
        }

        printGoodbye();
    }

    private String askForCommand() {
        return io.readLine("Next command (write 'help' to see the commands):");
    }
	
	private void printCommands() {
		StringBuilder sb = new StringBuilder();
		
		sb.append(formatCommand("quit"));
		sb.append("= quit the application\n");
		sb.append(formatCommand("help"));
		sb.append("= see these commands\n");
                sb.append(formatCommand("new"));
                sb.append("= add a new item\n");
		sb.append(formatCommand("list"));
		sb.append("= list items\n");
		sb.append(formatCommand("list by"));
		sb.append("= sort and list items\n");
		sb.append(formatCommand("details"));
		sb.append("= see detailed information of an item\n");
		sb.append(formatCommand("edit"));
		sb.append("= edit item\n");
		sb.append(formatCommand("search"));
		sb.append("= search items\n");
		sb.append(formatCommand("delete"));
		sb.append("= delete item\n");
		sb.append(formatCommand("read"));
		sb.append("= mark items as read");
				
		io.print(sb.toString());
	}
	
	private String formatCommand(String command) {
		return String.format("%-10s", command);
	}

    private void printGreeting() {
        io.print("Welcome to the CS literature recommendation system!");
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
	
	private void printSeparator() {
		io.print("-----------------------------------------------");
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
