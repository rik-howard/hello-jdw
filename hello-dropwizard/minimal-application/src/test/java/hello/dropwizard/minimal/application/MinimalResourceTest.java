
package hello.dropwizard.minimal.application;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class MinimalResourceTest {

    @Test
    public void testValue () throws Exception {
        String expected = "the value string from the minimal application";
        String actual = new MinimalResource ().value ();
        assertEquals (expected, actual);
    }

}
