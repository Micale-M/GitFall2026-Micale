/**	
  *Creates a GUI that allows the user to select a file, allow it to be anlayzed, and select which folder the analysis should go to.
  */

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.FileChooser;
import javafx.stage.DirectoryChooser;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.geometry.Insets;
import java.util.ArrayList;
import java.io.File;

public class SpireGUI extends Application {

    private File selectedDeckFile;
    private File selectedOutputFolder;

    @Override
    public void start(Stage stage) {
        stage.setTitle("Slay the Spire Deck Analyzer"); //Name of GUI

        Label fileLabel = new Label("No deck file selected."); //Label of file to analyzed
        Label folderLabel = new Label("No output folder selected."); //Label of which folder the user wants the report to go to
        Label statusLabel = new Label(""); //The status of a an analysis

        Button chooseFileBtn = new Button("Select Deck File"); //The first of three buttons, allows the user to select what file they want to be analyzed
        chooseFileBtn.setOnAction(e -> {
            FileChooser chooser = new FileChooser();
            chooser.setTitle("Choose Deck File");
            chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("TXT Files", "*.txt")); //Ensures only txt files can be chosen
            File file = chooser.showOpenDialog(stage);

            if (file != null) {
                selectedDeckFile = file;
                fileLabel.setText("Deck File: " + file.getName()); //Updates the label of file to be analyzed if a file is selected
            }
        });

        Button chooseFolderBtn = new Button("Select Output Folder"); //Second button allows user to choose which folder they want the report be sent to
        chooseFolderBtn.setOnAction(e -> {
            DirectoryChooser chooser = new DirectoryChooser();
            chooser.setTitle("Choose Folder for Report");
            File folder = chooser.showDialog(stage);

            if (folder != null) {
                selectedOutputFolder = folder;
                folderLabel.setText("Output Folder: " + folder.getAbsolutePath()); //Updates the label of the file the report goes to if a folder is chosen
            }
        });

        Button runBtn = new Button("Run Analysis"); //Third and final button, runs the analysis if  a file is selcted for analysis and a folder for the report is selected as well
        runBtn.setOnAction(e -> {  //Based on what is and isn't selected updated the label of what is missing or if a an analysis was successful or not.
            if (selectedDeckFile == null) {
                statusLabel.setText("Error: No deck file selected.");
                return;
            }
            if (selectedOutputFolder == null) {
                statusLabel.setText("Error: No output folder selected.");
                return;
            }

            try {
   		SpireMain.processDeck(selectedDeckFile.getAbsolutePath(), selectedOutputFolder.getAbsolutePath());
   	 	statusLabel.setText("Report generated successfully!");
	} catch (Exception ex) {
  	  	statusLabel.setText("Error: " + ex.getMessage());
	}
    	});

        VBox root = new VBox(15, chooseFileBtn, fileLabel, chooseFolderBtn, folderLabel, runBtn, statusLabel); //Creates the boxes for the buttons
        root.setPadding(new Insets(20)); //Creates margins evenly so words aren't crammed on one side

        stage.setScene(new Scene(root, 400, 300)); //The intial size of the GUI
        stage.show();
	}

    public static void main(String[] args) { //Launches GUI to allow the user to interact with the code
        launch(args);
    }
}
