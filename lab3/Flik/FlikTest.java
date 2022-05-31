import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class FlikTest {
    @Test
    public void testLibrary(){
        assertTrue("Flik library is fine", Flik.isSameNumber(4, 4));
        assertTrue("Flik library is not fine", Flik.isSameNumber(new Integer(128), new Integer(128)));
    }
}
