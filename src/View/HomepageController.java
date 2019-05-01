package View;

import Controller.MainApp;
import Model.Note;
import Util.DateUtil;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class HomepageController implements Initializable {

    @FXML
    private Label titleLabel;

    @FXML
    private Label dateLabel;

    @FXML
    private Label contentLabel;

    @FXML
    private TableView<Note> noteTable;
    @FXML
    private TableColumn<Note, String> titleColumn;

    private MainApp mainApp;

    private int whichTable;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        noteTable.setItems(mainApp.getToDoNotes());
    }

    public HomepageController() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        titleColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());

        /*Clearing person details*/
        showNoteDetails(null);

        /*Adding Listener*/
        noteTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showNoteDetails(newValue));
        whichTable = 1;
    }

    public void showNoteDetails(Note note) {
        if (note == null) {
            titleLabel.setText("");
            dateLabel.setText("");
            contentLabel.setText("");
        } else {
            titleLabel.setText(note.getTitle());
            dateLabel.setText("Until: " + DateUtil.format(note.getDate()));
            contentLabel.setText(note.getContent());
        }
    }

    @FXML
    private void handleDeleteNote() {
        int selectedIndex = noteTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            if (whichTable == 2) {
                noteTable.getItems().remove(selectedIndex);
            } else {
                mainApp.getDeletedNotes().add(noteTable.getItems().get(selectedIndex));
                noteTable.getItems().remove(selectedIndex);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No selection");
            alert.setHeaderText("No Note Selected");
            alert.setContentText("Please select a note in the table.");

            alert.showAndWait();
        }
    }

    @FXML
    private void handleNewNote() {
        //To be completed
    }

    @FXML
    private void handleShowToDo() {
        noteTable.setItems(mainApp.getToDoNotes());
        whichTable = 1;
    }

    @FXML
    private void handleShowDeleted() {
        noteTable.setItems(mainApp.getDeletedNotes());
        whichTable = 2;
    }

    @FXML
    private void handleShowDone() {
        noteTable.setItems(mainApp.getDoneNotes());
        whichTable = 3;
    }
}
