
package bake.dropwizard.common.types.fields;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import io.dropwizard.jackson.Jackson;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PersonIdTest {

    static String resourceString;
    static ObjectMapper objectMapper;

    Integer integer;

    PersonId personId0;
    PersonId personId1;
    PersonId personId2;

    @BeforeClass
    public static void begin ()
    throws Exception {
        resourceString = Resources.toString (
            Resources.getResource ("fields/person-id.json"),
            Charsets.UTF_8
        );
        objectMapper = Jackson.newObjectMapper ();
    }

    @AfterClass
    public static void end ()
    throws Exception {
        resourceString = null;
        objectMapper = null;
    }

    @Before
    public void setUp ()
    throws Exception {
        integer = 0;
        personId0 = new PersonId (integer);
        personId1 = new PersonId (integer);
        personId2 = new PersonId (integer);
    }

    @After
    public void tearDown ()
    throws Exception {
        personId0 = null;
        personId1 = null;
        personId2 = null;
    }

    @Test
    public void testEquals ()
    throws Exception {
        // reflexive
        assert personId0.equals (personId0);
        // symmetric
        assert personId0.equals (personId1);
        assert personId1.equals (personId0);
        // transitive
        assert personId0.equals (personId1);
        assert personId1.equals (personId2);
        assert personId0.equals (personId2);
    }

    @Test
    public void testHashCode ()
    throws Exception {
        int expected = personId0.hashCode ();
        int actual = personId1.hashCode ();
        assert expected == actual;
    }

    @Test
    public void testToString ()
    throws Exception {
        String expected = integer.toString ();
        String actual = personId0.toString ();
        assert expected.equals (actual);
    }

    @Test
    public void testInteger ()
    throws Exception {
        Integer expected = integer;
        Integer actual = personId0.integer ();
        assert expected.equals (actual);
    }

    @Test
    public void testJsonSerialisation ()
    throws Exception {
        String expected = resourceString.trim ().replaceAll ("[\\s\\n]", "");
        String actual = objectMapper.writeValueAsString (personId0);
        assert expected.equals (actual);
    }

    @Test
    public void testJsonDeserialisation ()
    throws Exception {
        PersonId expected = personId0;
        PersonId actual = objectMapper.readValue (resourceString, PersonId.class);
        assert expected.equals (actual);
    }

}
