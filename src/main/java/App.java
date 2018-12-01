
import io.*;
import data_access.*;
import item.*;
import item.Video;

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
            String command = askForCommand();
            io.print("");

            switch (command) {
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

                case "list by":
                    sortItems();
                    break;

                case "edit":
                    editItem();
                    break;

                case "delete":
                    deleteItem();
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
                + "list = list items\n"
                + "list by = sort and list items\n"
                + "edit = edit item\n"
                + "delete = delete item");
    }

    private void printGreeting() {
        io.print("Welcome to the CS literature recommendation system!");
    }

    private void addItem() {
        String type = io.readLine("Type: (book, video, blog post, podcast)");

        switch (type) {
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

    private void editItem() {
        itemController.editItem();
    }

    private void deleteItem() {
        itemController.deleteItem();
    }

    private void addBook() {
        itemController.addBook();
    }

    private void addVideo() {
        itemController.addVideo();
    }

    private void addBlogPost() {
        itemController.addBlogPost();
    }

    private void addPodcast() {
        itemController.addPodcast();
    }

    private void listItems() {
        this.itemController.listItems("");
    }

    private void sortItems() {
        this.itemController.sortItems();
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
