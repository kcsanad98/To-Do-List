package DateUtilTests;

import Util.DateUtil;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class FormatTest {
    
    @Parameterized.Parameters
    public static Collection<Object[]> data(){
        return Arrays.asList(new Object[][]{
            {null, null},
            {"08.08.1998",LocalDate.of(1998, Month.AUGUST, 8)},
            {"08.08.1998", LocalDate.of(1998, 8, 8)},
            {"12.12.2056",LocalDate.of(2056, 12, 12)}
        });
    }
    
    private String dateString;
    private LocalDate localDate;
    
    public FormatTest(String expected, LocalDate date){
        this.dateString = expected;
        this.localDate = date;
    }
    
    @Test
    public void FormatTest(){
        assertEquals(dateString, DateUtil.format(localDate));
    }
}
