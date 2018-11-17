
import java.util.Scanner;
import io.*;
import data_access.*;

/*
 * This Java source file was generated by the Gradle 'init' task.
 */
public class App {

    private IO io;
    private ItemDao itemDao;

    public App(IO io, ItemDao itemDao) {
        this.io = io;
        this.itemDao = itemDao;
    }

    public void run() {
        io.print(getGreeting());
        while (true) {
            String command = io.readLine("quit = quit the application\nnew = add a new book\nlist = list items");
            
            if (command.isEmpty()) {
                break;
            }

            if (command.equals("quit")) {
                break;
            } else if (command.equals("new")) {
                io.print("Here you can add a book (not implemented yet)");
            } else if (command.equals("list")) {
                if (itemDao.getItems().size() == 0) {
                    io.print("No items added yet");
                } else {
                    for (int x = 0; x < itemDao.getItems().size(); x++) {
                        io.print(itemDao.getItemById(x).toString());
                    }
                }
            } else {
                io.print("unknown option");
            }
        }
    }

    public static void main(String[] args) {
        ItemDao itemDao = new InMemoryItemDao();
        IO io = new TextIO();
        new App(io, itemDao).run();
    }

    // public static void main(String[] args) {
    //     System.out.println(getGreeting());
    //     Scanner scanner = new Scanner(System.in);
    //     TextIO ui = new TextIO(scanner);
    //     ui.launch();
    // }
    
    public static String getGreeting(){
        return "Welcome to the CS literature recommendation system!";
    }
}
