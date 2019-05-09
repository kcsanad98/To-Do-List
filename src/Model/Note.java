/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.time.LocalDate;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author strix
 */
public class Note {

    private  StringProperty title = new SimpleStringProperty();
    private  StringProperty content = new SimpleStringProperty();
    private  ObjectProperty<LocalDate> date = new SimpleObjectProperty<>();
    private  IntegerProperty color = new SimpleIntegerProperty();
    
    public Note(){
        this("");
    }

    public Note(TempNote tempNote) {
        this.title = new SimpleStringProperty(tempNote.getTitle());
        this.content = new SimpleStringProperty(tempNote.getContent());
        this.date = new SimpleObjectProperty<>(tempNote.getDate());
        this.color = new SimpleIntegerProperty(tempNote.getColor());
    }
   
    public Note(String title){
        this.title = new SimpleStringProperty(title);
        this.content = new SimpleStringProperty();
        this.date = new SimpleObjectProperty<>();
        this.color = new SimpleIntegerProperty();
    }
    
    public String getTitle() {
        return title.get();
    }

    public void setTitle(String value) {
        title.set(value);
    }

    public StringProperty titleProperty() {
        return title;
    }

    public String getContent() {
        return content.get();
    }

    public void setContent(String value) {
        content.set(value);
    }

    public StringProperty contentProperty() {
        return content;
    }

    public LocalDate getDate() {
        return date.get();
    }

    public void setDate(LocalDate value) {
        date.set(value);
        
    }

    public ObjectProperty<LocalDate> dateProperty() {
        return date;
    }

    public int getColor() {
        return color.get();
    }

    public void setColor(int value) {
        color.set(value);
    }

    public IntegerProperty colorProperty() {
        return color;
    }

}
