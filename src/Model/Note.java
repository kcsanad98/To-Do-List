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

    public Note() {
        this(null);
    }
   
    public Note(String title){
        this.title = new SimpleStringProperty(title);
        this.content = new SimpleStringProperty();
        this.date = new SimpleObjectProperty<>();
        this.color = new SimpleIntegerProperty();
    }
    
    private String getTitle() {
        return title.get();
    }

    private void setTitle(String value) {
        title.set(value);
    }

    private StringProperty titleProperty() {
        return title;
    }

    private String getContant() {
        return content.get();
    }

    private void setContant(String value) {
        content.set(value);
    }

    private StringProperty contantProperty() {
        return content;
    }

    private LocalDate getDate() {
        return date.get();
    }

    private void setDate(LocalDate value) {
        date.set(value);
    }

    private ObjectProperty dateProperty() {
        return date;
    }

    private int getColor() {
        return color.get();
    }

    private void setColor(int value) {
        color.set(value);
    }

    private IntegerProperty colorProperty() {
        return color;
    }

}
