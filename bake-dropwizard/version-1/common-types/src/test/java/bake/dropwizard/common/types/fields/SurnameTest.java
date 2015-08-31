
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

public class SurnameTest {

    static String resourceString;
    static ObjectMapper objectMapper;

    String string;

    Surname surname0;
    Surname surname1;
    Surname surname2;

    @BeforeClass
    public static void begin ()
    throws Exception {
        resourceString = Resources.toString (
            Resources.getResource ("fields/surname.json"),
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
        string = "Hendrix";
        surname0 = new Surname (string);
        surname1 = new Surname (string);
        surname2 = new Surname (string);
    }

    @After
    public void tearDown ()
    throws Exception {}

    @Test
    public void testEquals ()
    throws Exception {
        // reflexive
        assert surname0.equals (surname0);
        // symmetric
        assert surname0.equals (surname1);
        assert surname1.equals (surname0);
        // transitive
        assert surname0.equals (surname1);
        assert surname1.equals (surname2);
        assert surname0.equals (surname2);
    }

    @Test
    public void testHashCode ()
    throws Exception {
        int expected = surname0.hashCode ();
        int actual = surname1.hashCode ();
        assert expected == actual;
    }

    @Test
    public void testToString ()
    throws Exception {
        String expected = string;
        String actual = surname0.toString ();
        assert expected.equals (actual);
    }

    @Test
    public void testString ()
    throws Exception {
        String expected = string;
        String actual = surname0.string ();
        assert expected.equals (actual);
    }

    @Test
    public void testJsonSerialisation ()
    throws Exception {
        String expected = resourceString.trim ().replaceAll ("[\\s\\n]", "");
        String actual = objectMapper.writeValueAsString (surname0);
        assert expected.equals (actual);
    }

    @Test
    public void testJsonDeserialisation ()
    throws Exception {
        Surname expected = surname0;
        Surname actual = objectMapper.readValue (resourceString, Surname.class);
        assert expected.equals (actual);
    }

}
