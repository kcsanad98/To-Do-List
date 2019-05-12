package View;

import Controller.MainApp;
import Model.Note;
import Util.DateUtil;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.TextAlignment;

public class HomepageController implements Initializable {

    @FXML
    private Label titleLabel;

    @FXML
    private Label dateLabel;

    @FXML
    private Label contentLabel;
    
    @FXML
    private Label detailsLabel;

    @FXML
    private TableView<Note> noteTable;
    
    @FXML
    private TableColumn<Note, String> titleColumn;

    @FXML
    private Button deleteButton;
    
    @FXML
    private Button editButton;
    
    @FXML
    private Button doneButton;
    
    private MainApp mainApp;

    private int whichTable;
    @FXML
    private Button menuButtonStatistics;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        noteTable.setItems(mainApp.getToDoNotes());
        noteTable.setPlaceholder(new Label(""));
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
        showButtons(false, false, false);
    }
    
    public void showButtons (boolean delete, boolean edit, boolean done) {
        deleteButton.setVisible(delete);
        editButton.setVisible(edit);
        doneButton.setVisible(done);
    }
    
    public void showNoteDetails(Note note) {
        if (note == null) {
            titleLabel.setText("Please Select a Note");
            dateLabel.setText("");
            contentLabel.setText("");
            detailsLabel.setText("");
        } else {
            titleLabel.setText(note.getTitle());
            dateLabel.setText("Until: " + DateUtil.format(note.getDate()));
            contentLabel.setText(note.getContent());
            contentLabel.setTextAlignment(TextAlignment.JUSTIFY);
            detailsLabel.setText("Details");
            switch (whichTable) {
                case 1: {
                    showButtons(true, true, true);
                    break;
                }
                case 2: {
                    showButtons(true, false, false);
                    break;
                }
                case 3: {
                    showButtons(true, false, false);
                    break;
                }
                default: break;
            }
        }
    }

    @FXML
    private void handleDeleteNote() {
        int selectedIndex = noteTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            if (whichTable == 2 || whichTable == 3) {
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
        Note tempNote = new Note();
        boolean okClicked = mainApp.showaddNewLayout(tempNote);
        if(okClicked){
            mainApp.getToDoNotes().add(tempNote);
        }
    }
    
    @FXML
    private void handleEditNote(){
        Note selectedNote = noteTable.getSelectionModel().getSelectedItem();
        if(selectedNote != null){
            boolean okClicked = mainApp.showaddNewLayout(selectedNote);
            if(okClicked){
                showNoteDetails(selectedNote);
            }
        }else{
            //Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Note Selected");
            alert.setContentText("Please select a note in the table.");

        alert.showAndWait();
        }
    }
    
    @FXML
    private void handleDoneNote() {
        int selectedIndex = noteTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            mainApp.getDoneNotes().add(noteTable.getItems().get(selectedIndex));
            noteTable.getItems().remove(selectedIndex);
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
    private void handleShowToDo() {
        noteTable.setItems(mainApp.getToDoNotes());
        whichTable = 1;
        showButtons(false, false, false);
    }

    @FXML
    private void handleShowDeleted() {
        noteTable.setItems(mainApp.getDeletedNotes());
        whichTable = 2;
        showButtons(false, false, false);
    }

    @FXML
    private void handleShowDone() {
        noteTable.setItems(mainApp.getDoneNotes());
        whichTable = 3;
        showButtons(false, false, false);
    }
    
    @FXML
    private void handleExitButton() {
        //System.out.println("aaa");
        mainApp.saveNotesToFile();
        System.exit(0);
    }
    
    @FXML
    private void handleShowStatistics(){
        mainApp.showstatisticsLayout();
    }
}
