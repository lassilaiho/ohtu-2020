package ohtu.kivipaperisakset;

import java.util.Scanner;

public class ConsoleIO implements IO {
    private static final Scanner scanner = new Scanner(System.in);

    @Override
    public String getLine() {
        return scanner.nextLine();
    }

    @Override
    public void putLine(String message) {
        System.out.println(message);
    }
}
