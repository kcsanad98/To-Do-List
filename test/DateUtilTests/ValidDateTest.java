package DateUtilTests;

import Util.DateUtil;
import java.util.Arrays;
import java.util.Collection;
import static junit.framework.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class ValidDateTest {

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
            {true, "08.06.1998"},
            {true, "01.01.2020"},
            {false, "1.1.2020"},
            {false, "01.1.2020"},
            {false, "1.01.2020"},
            {false, "2020.04.05"},
            {false, "2020.4.5"},
            {false, "2020.04.5"},
            {false, "2020.4.05"},
            {false, "04.05.2020."},
            {false, "04.05.20"}
            });
    }
    
    private boolean res;
    private String dateString;

    public ValidDateTest(boolean res, String dateString) {
        this.res = res;
        this.dateString = dateString;
    }
    
    @Test
    public void ValidDateTest(){
        assertEquals(res, DateUtil.validDate(dateString));
    }
}
