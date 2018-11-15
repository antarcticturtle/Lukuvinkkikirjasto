package ui;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class TextUI {

    private Scanner scanner;
    private Map<String, String> options;

    public TextUI(Scanner scanner) {
        this.scanner = scanner;
        options = new TreeMap<>();

        options.put("0", "0 quit");
        options.put("1", "1 add a book");
    }

    public void launch() {
        while (true) {
            System.out.println("");
            this.printInstructions();
            System.out.println("");
            System.out.println("option: ");
            String option = this.scanner.nextLine();
            if (option.equals("0")) {
                break;
            } else if (option.equals("1")) {
                System.out.println("Here you can add a book (not implemented yet)");
            } else {
                System.out.println("unknown option");
            }
        }
    }

    private void printInstructions() {
        this.options.values().forEach(v -> System.out.println(v));
    }

}
