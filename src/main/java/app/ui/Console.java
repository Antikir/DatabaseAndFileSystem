package app.ui;

import java.util.Scanner;

public class Console {
    public static int sc() {

        return new Scanner(System.in).nextInt();
    }

    public static String scString() {

        return new Scanner(System.in).nextLine();
    }
}
