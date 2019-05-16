/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;


import Model.Note;
import Util.DateUtil;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Virág
 */
public class AddNewLayoutController implements Initializable {
    @FXML
    private TextField titleField;
    @FXML
    private TextField dateField;
    @FXML
    private TextArea contentField;
    @FXML
    private ColorPicker colorPicker;
    @FXML
    private Button addNewCancelButton;
    @FXML
    private Button addNewSaveButton;

    
     private Stage dialogStage;
     private Note note;
     private boolean okClicked = false;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    public void setDialogStage(Stage dialogStage){
        this.dialogStage = dialogStage;
    }
    
    public void setNote(Note note){
        this.note = note;
        
        titleField.setText(note.getTitle());
        dateField.setText(DateUtil.format(note.getDate()));
        contentField.setText(note.getContent());
        if (note.getColor() == null)
            colorPicker.setValue(Color.WHITE);
        else
            colorPicker.setValue(Color.valueOf(note.getColor()));
        //colorPicker.setValue(Color.BLACK);
    }
    
    public boolean isokClicked(){
        return okClicked;
    }
    
    @FXML
    private void handleCancel(ActionEvent event) {
        dialogStage.close();
    }

    @FXML
    private void handleSave(ActionEvent event) {
        if(isInputValid()){
            note.setTitle(titleField.getText());
            note.setDate(DateUtil.parse(dateField.getText()));
            note.setContent(contentField.getText());
            note.setColor(colorPicker.getValue().toString());
            
            okClicked = true;
            dialogStage.close();
        }
    }
    
    private boolean isInputValid(){
        String errorMessage = "";
        
        if(titleField.getText() == null || titleField.getText().length() == 0){
            errorMessage += "Not valid title!\n";
        }
        if(contentField.getText() == null || contentField.getText().length() == 0){
            errorMessage += "Not valid content!\n";
        }
        if(dateField.getText() == null || dateField.getText().length() == 0){
            errorMessage += "Not valid date!\n";
        }else{
            if(!DateUtil.validDate(dateField.getText())){
                errorMessage += "Not valid date! Use dd.mm.yyyy!\n";
            }
        }
        
        if(errorMessage.length() == 0)
            return true;
        else{
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid field");
            alert.setContentText(errorMessage);
            
            alert.showAndWait();
            
            return false;
        }
    }
    
}
