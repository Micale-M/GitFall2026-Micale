 /**
   *Generates a unique 9-digit deck ID and ensures no ID is ever reused.
   *Input: None
   *Output: Unique 9-digit ID
   */
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;

public class DeckIDGenerator {

    private static final String LOG_FILE = "used_ids.txt";

    public int generateUniqueID() {

        HashSet<Integer> usedIDs = new HashSet<>();

        File file = new File(LOG_FILE);
        if (file.exists()) {   //Load existing IDs from file
            try (Scanner sc = new Scanner(file)) {
                while (sc.hasNextInt()) {
                    usedIDs.add(sc.nextInt());
                }
            } catch (Exception e) {
                System.out.println("Warning: Could not read ID log file.");
            }
        }

       
        int id;
        do { //Generates new ID until it's unique
            id = (int)(Math.random() * 1_000_000_000);  //Ensures the ID is always 9 digits
        } while (usedIDs.contains(id));

        
        try (FileWriter fw = new FileWriter(file, true)) { //Saves new ID to file so we don't end up with any duplicate IDs
            fw.write(id + "\n");
        } catch (IOException e) {
            System.out.println("Warning: Could not write ID to log file.");
        }

        return id;
    }
}
