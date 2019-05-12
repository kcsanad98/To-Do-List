package DateUtilTests;

import Util.DateUtil;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class ParseTest {
    @Parameterized.Parameters
    public static Collection<Object[]> data(){
        return Arrays.asList(new Object[][]{
            {null, null},
            {null, "randomString"},
            {null, "1998.08.15"},
            {LocalDate.of(1848, 3, 15),"15.03.1848"},
        });
    }
    
    private LocalDate localDate;
    private String dateString;

    public ParseTest(LocalDate localDate, String dateString) {
        this.localDate = localDate;
        this.dateString = dateString;
    }
    
    @Test
    public void ParseTest(){
        Assert.assertEquals(localDate, DateUtil.parse(dateString));
    }
}
