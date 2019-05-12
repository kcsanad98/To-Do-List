/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.MainApp;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Virág
 */
public class StatisticsLayoutController extends Application implements Initializable{

    /**
     * Initializes the controller class.
     */
    private MainApp mainApp;
    
    private Stage dialogStage;
    
    @FXML
    private PieChart pieChart;
    
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
        
    public void setDialogStage(Stage dialogStage){
        this.dialogStage = dialogStage;
    }
    
    @Override public void start(Stage dialogstage) {
        ObservableList<PieChart.Data> pieChartData =
            FXCollections.observableArrayList(
            new PieChart.Data("Doing", mainApp.getToDoNotes().size()),
            new PieChart.Data("Deleted", mainApp.getDeletedNotes().size()),
            new PieChart.Data("Done", mainApp.getDoneNotes().size())
        );
        pieChart.setData(pieChartData);
    }

    @FXML
    private void handleStatisticsBack(MouseEvent event) {
        dialogStage.close();
    }
}
