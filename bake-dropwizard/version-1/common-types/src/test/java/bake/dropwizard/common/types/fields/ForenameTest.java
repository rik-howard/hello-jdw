
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

public class ForenameTest {

    static String resourceString;
    static ObjectMapper objectMapper;

    String string;

    Forename forename0;
    Forename forename1;
    Forename forename2;

    @BeforeClass
    public static void begin ()
    throws Exception {
        resourceString = Resources.toString (
            Resources.getResource ("fields/forename.json"),
            Charsets.UTF_8
        );
        objectMapper = Jackson.newObjectMapper ();
    }

    @AfterClass
    public static void end ()
    throws Exception {}

    @Before
    public void setUp ()
    throws Exception {
        string = "Jimi";
        forename0 = new Forename (string);
        forename1 = new Forename (string);
        forename2 = new Forename (string);
    }

    @After
    public void tearDown ()
    throws Exception {}

    @Test
    public void testEquals ()
    throws Exception {
        // reflexive
        assert forename0.equals (forename0);
        // symmetric
        assert forename0.equals (forename1);
        assert forename1.equals (forename0);
        // transitive
        assert forename0.equals (forename1);
        assert forename1.equals (forename2);
        assert forename0.equals (forename2);
    }

    @Test
    public void testHashCode ()
    throws Exception {
        int expected = forename0.hashCode ();
        int actual = forename1.hashCode ();
        assert expected == actual;
    }

    @Test
    public void testToString ()
    throws Exception {
        String expected = string;
        String actual = forename0.toString ();
        assert expected.equals (actual);
    }

    @Test
    public void testString ()
    throws Exception {
        String expected = string;
        String actual = forename0.string ();
        assert expected.equals (actual);
    }

    @Test
    public void testJsonSerialisation ()
    throws Exception {
        String expected = resourceString.trim ().replaceAll ("[\\s\\n]", "");
        String actual = objectMapper.writeValueAsString (forename0);
        assert expected.equals (actual);
    }

    @Test
    public void testJsonDeserialisation ()
    throws Exception {
        Forename expected = forename0;
        Forename actual = objectMapper.readValue (resourceString, Forename.class);
        assert expected.equals (actual);
    }

}
