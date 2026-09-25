/**
  *Breaks down the premade list of card name from csv file.
  */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

public class ValidCardNames {

    private static HashSet<String> validNames = new HashSet<>();

    public static void loadFromCSV(String filename) {
        try (Scanner sc = new Scanner(new File(filename))) {
            while (sc.hasNextLine()) {
                String name = sc.nextLine().trim();
                if (!name.isEmpty()) {
                    validNames.add(name);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: Could not load valid card names CSV.");
        }
    }

    public static boolean isValidName(String name) {
        return validNames.contains(name);
    }
}
