package TempNoteModelTest;

import Model.Note;
import Model.TempNote;
import java.time.LocalDate;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

public class TempNote_Test {
    Note note = new Note();
    
    TempNote tmpNote;
    
    @Before
    public void init()
    {
        note.setTitle("New note");
        note.setContent("New");
        note.setDate(LocalDate.MAX);
        note.setColor("0xb3b31aff");
        
        tmpNote = new TempNote(note);
    }
    
    @Test
    public void testTitle()
    {
        String exp = note.getTitle();
        String act = tmpNote.getTitle();
        
        Assert.assertEquals(exp, act);
    }
    
    @Test
    public void testContent()
    {
        String exp = note.getContent();
        String act = tmpNote.getContent();
        
        Assert.assertEquals(exp, act);
    }
    
    @Test
    public void testDate()
    {
        LocalDate exp = note.getDate();
        LocalDate act = tmpNote.getDate();
        
        Assert.assertEquals(exp, act);
    }
    
    @Test
    public void testColor()
    {
        String exp = note.getColor();
        String act = tmpNote.getColor();
        
        Assert.assertEquals(exp, act);
    }
    
   
    
    
    
}
