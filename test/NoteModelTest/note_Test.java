package NoteModelTest;

import Model.Note;
import java.time.LocalDate;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;

public class note_Test {
    private Note note = new Note();
    
    @Before
    public void initialize()
    {
     
      note.setTitle("New Note");
      note.setContent("New Content");
      note.setColor("0xb3b31aff");
      note.setDate(LocalDate.now());
    }
    
    @Test
    public void testNoteTitle()
    { 
        String expected_title = "New Note";
        String actual_title = note.getTitle();  
        assertEquals(expected_title, actual_title);
    }
    
    @Test
    public void testNoteContent()
    {
        String expected_Content = "New Content";
        String actual_Content = note.getContent();
        assertEquals(expected_Content, actual_Content);
    }
    
    @Test
    public void testNoteDate()
    {
        LocalDate expected_Date = LocalDate.now();
        LocalDate actual_Date = note.getDate();
        assertEquals(expected_Date, actual_Date);
    }
    
    @Test
    public void testNoteColor()
    {
        String excpected_Color = "0xb3b31aff";
        String actual_Color = note.getColor();
        
        if(!excpected_Color.equals(actual_Color))
            fail();
    }
}
