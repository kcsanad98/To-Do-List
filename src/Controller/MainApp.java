package Controller;

import Model.Note;
import View.HomepageController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Modality;

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
        toDoNotes.add(new Note("Note to do"));
        doneNotes.add(new Note("Done note"));
        deletedNotes.add(new Note("Deleted note"));
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
            
            primaryStage.setScene(scene); 
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
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
