
import java.util.Scanner;
import io.*;

/*
 * This Java source file was generated by the Gradle 'init' task.
 */
public class App {

    private IO io;

    public App(IO io) {
        this.io = io;
    }

    public void run() {
        io.print(getGreeting());
        while (true) {
            String command = io.readLine("quit = quit the application\nnew = add a new book");
            
            if (command.isEmpty()) {
                break;
            }

            if (command.equals("quit")) {
                break;
            } else if (command.equals("new")) {
                io.print("Here you can add a book (not implemented yet)");
            } else {
                io.print("unknown option");
            }
        }
    }

    public static void main(String[] args) {
        IO io = new TextIO();
        new App(io).run();
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
