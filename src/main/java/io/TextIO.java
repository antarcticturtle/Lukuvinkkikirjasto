package io;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class TextIO implements IO {
    private Scanner scanner = new Scanner(System.in);
	
	@Override
    public void print(String toPrint) {
        System.out.println(toPrint);
    }
	
	@Override
    public int readInt(String prompt) {
        System.out.println(prompt);
        return Integer.parseInt(scanner.nextLine());
    }
	
	@Override
    public String readLine(String prompt) {
        System.out.println(prompt);
        return scanner.nextLine();
    }
}
