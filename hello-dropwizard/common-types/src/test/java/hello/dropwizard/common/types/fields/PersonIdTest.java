
package hello.dropwizard.common.types.fields;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PersonIdTest {

    PersonId id0;
    PersonId id1;
    PersonId id2;

    @Before
    public void setUp ()
    throws Exception {
        id0 = new PersonId (0);
        id1 = new PersonId (0);
        id2 = new PersonId (0);
    }

    @After
    public void tearDown ()
    throws Exception {
        id0 = null;
        id1 = null;
        id2 = null;
    }

    @Test
    public void testToString ()
    throws Exception {
        String expected = "0";
        String actual = id0.toString ();
        assert expected.equals (actual);
    }

    @Test
    public void testEquals ()
    throws Exception {
        assert id0.equals (id0);
        assert id0.equals (id1);
        assert id1.equals (id0);
        assert id0.equals (id1);
        assert id1.equals (id2);
        assert id0.equals (id2);
    }

    @Test
    public void testHashCode ()
    throws Exception {
        int expected = id0.hashCode ();
        int actual = id1.hashCode ();
        assert expected == actual;
    }

    @Test
    public void testValue ()
    throws Exception {
        Integer expected = 0;
        Integer actual = id0.value ();
        assert expected.equals (actual);
    }

}
