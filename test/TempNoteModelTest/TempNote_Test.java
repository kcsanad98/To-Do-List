/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TempNoteModelTest;

import Model.Note;
import Model.TempNote;
import java.time.LocalDate;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author strix
 */
public class TempNote_Test {
    Note note = new Note();
    
    TempNote tmpNote;
    
    @Before
    public void init()
    {
        note.setTitle("New note");
        note.setContent("New");
        note.setDate(LocalDate.MAX);
        note.setColor(2);
        
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
        int exp = note.getColor();
        int act = tmpNote.getColor();
        
        Assert.assertEquals(exp, act);
    }
    
   
    
    
    
}
