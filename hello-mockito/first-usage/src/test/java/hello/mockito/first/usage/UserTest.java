
package hello.mockito.first.usage;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UserTest {

    private User testee;

    @Before
    public void setUp ()
    throws Exception {
        testee = new User (0, "rik");
    }

    @After
    public void tearDown ()
    throws Exception {
        testee = null;
    }

    @Test
    public void testId ()
    throws Exception {
        Integer expected = 0;
        Integer actual = testee.id ();
        assert expected.equals (actual);
    }

    @Test
    public void testName ()
    throws Exception {
        String expected = "rik";
        String actual = testee.name ();
        assert expected.equals (actual);
    }

    @Test
    public void testEquals ()
    throws Exception {
        assert ! testee.equals (null);
        assert testee.equals (testee);
        //assert ! testee.equals (new User (1, testee.name ()));
        assert ! testee.equals (new Object ());
    }

    @Test
    public void testHashCode ()
    throws Exception {
        Integer expected = 112916;
        Integer actual = testee.hashCode ();
        //System.out.println (actual);
        assert expected.equals (actual);
    }

    @Test
    public void testToString ()
    throws Exception {
        String expected = "User {id: 0, name: 'rik'}";
        String actual = testee.toString ();
        //System.out.println (actual);
        assert expected.equals (actual);
    }

}
