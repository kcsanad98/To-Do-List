package Model;

import java.time.LocalDate;

public class TempNote implements java.io.Serializable{

    private String title;
    private String content;
    private LocalDate date;
    private String color;

    public TempNote(Note note) {
        this.title = note.getTitle();
        this.content = note.getContent();
        this.date = note.getDate();
        this.color = note.getColor();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
