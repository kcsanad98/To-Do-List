package Controller;

import Model.Note;
import Model.TempNote;
import View.AddNewLayoutController;
import View.HomepageController;
import View.StatisticsLayoutController;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.StageStyle;

public class MainApp extends Application {

    private Stage primaryStage;

    private ObservableList<Note> toDoNotes = FXCollections.observableArrayList();
    private ObservableList<Note> doneNotes = FXCollections.observableArrayList();
    private ObservableList<Note> deletedNotes = FXCollections.observableArrayList();

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Sticky Notes");

        initHomepageLayout();

    }

    public MainApp() {
    }

    public void initHomepageLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/View/homepageLayout.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            Scene scene = new Scene(personOverview);
            scene.getStylesheets().add("/View/style.css");

            HomepageController controller = loader.getController();
            controller.setMainApp(this);

            primaryStage.initStyle(StageStyle.UNDECORATED);
            primaryStage.setScene(scene);
            primaryStage.show();
            
            scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {
                    if(event.isControlDown() && event.getCode() == KeyCode.S){
                        saveNotesToFile();
                    }         
                }
            });
            
            loadNotesFromFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean showaddNewLayout(Note note) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/View/addNewLayout.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Notes");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            AddNewLayoutController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setNote(note);

            dialogStage.showAndWait();

            return controller.isokClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public void showstatisticsLayout(){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/View/statisticsLayout.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Statistics");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            dialogStage.initStyle(StageStyle.UNDECORATED);
            Scene scene = new Scene(page);
            scene.getStylesheets().add("/View/style.css");
            dialogStage.setScene(scene);

            StatisticsLayoutController controller = loader.getController();
            controller.setMainApp(this);
            controller.setDialogStage(dialogStage);
            controller.start(dialogStage);
            
            
            dialogStage.show();

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void loadNotesFromFile() {
        toDoNotes.clear();
        doneNotes.clear();
        deletedNotes.clear();
        
        TempNote tmp;
        
        try {
            /*Loading toDoNotes from Appdata/ToDoData.ser*/
            FileInputStream fIn = new FileInputStream("AppData/ToDoData.ser");
            ObjectInputStream in = new ObjectInputStream(fIn);
            
            while(fIn.available() > 0){
                tmp = (TempNote)in.readObject();
                if(tmp != null)
                    toDoNotes.add(new Note(tmp));
            }
            
            fIn.close();
            in.close();
            
            /*Loading doneNotes from Appdata/DoneData.ser*/
            fIn = new FileInputStream("AppData/DoneData.ser");
            in = new ObjectInputStream(fIn);
            
            while(fIn.available() > 0){
                tmp = (TempNote)in.readObject();
                if(tmp != null)
                    doneNotes.add(new Note(tmp));
            }
            
            fIn.close();
            in.close();
            
            /*Loading deletedNotes from Appdata/DeletedData.ser*/
            fIn = new FileInputStream("AppData/DeletedData.ser");
            in = new ObjectInputStream(fIn);
            
            while(fIn.available() > 0){
                tmp = (TempNote)in.readObject();
                if(tmp != null)
                    deletedNotes.add(new Note(tmp));
            }
            
            fIn.close();
            in.close();

        } catch (IOException|ClassNotFoundException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not load data");
            alert.setContentText("Could not load data from file. No data is loaded.");

            alert.showAndWait();
        }
    }

    public void saveNotesToFile() {
        try {
            /*Saving toDoNotes to Appdata/ToDoData.ser*/
            FileOutputStream fOut = new FileOutputStream("AppData/ToDoData.ser");
            ObjectOutputStream out = new ObjectOutputStream(fOut);

            for (Note note : toDoNotes) {
                out.writeObject(new TempNote(note));
            }

            out.close();
            fOut.close();

            /*Saving doneNotes to Appdata/DoneData.ser*/
            fOut = new FileOutputStream("AppData/DoneData.ser");
            out = new ObjectOutputStream(fOut);

            for (Note note : doneNotes) {
                out.writeObject(new TempNote(note));
            }

            out.close();
            fOut.close();

            /*Saving deletedNotes to Appdata/DeletedData.ser*/
            fOut = new FileOutputStream("AppData/DeletedData.ser");
            out = new ObjectOutputStream(fOut);

            for (Note note : deletedNotes) {
                out.writeObject(new TempNote(note));
            }

            out.close();
            fOut.close();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not save data");
            alert.setContentText("Could not save data to file");

            alert.showAndWait();
        }
    }

    public ObservableList<Note> getToDoNotes() {
        return toDoNotes;
    }

    public ObservableList<Note> getDoneNotes() {
        return doneNotes;
    }

    public ObservableList<Note> getDeletedNotes() {
        return deletedNotes;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
