
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

public class NameTest {

    static String resourceString;
    static ObjectMapper objectMapper;

    String string;

    Name name0;
    Name name1;
    Name name2;

    @BeforeClass
    public static void begin ()
    throws Exception {
        resourceString = Resources.toString (
            Resources.getResource ("fields/name.json"),
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
        string = "the Experience";
        name0 = new Name (string);
        name1 = new Name (string);
        name2 = new Name (string);
    }

    @After
    public void tearDown ()
    throws Exception {}

    @Test
    public void testEquals ()
    throws Exception {
        // reflexive
        assert name0.equals (name0);
        // symmetric
        assert name0.equals (name1);
        assert name1.equals (name0);
        // transitive
        assert name0.equals (name1);
        assert name1.equals (name2);
        assert name0.equals (name2);
    }

    @Test
    public void testHashCode ()
    throws Exception {
        int expected = name0.hashCode ();
        int actual = name1.hashCode ();
        assert expected == actual;
    }

    @Test
    public void testToString ()
    throws Exception {
        String expected = string;
        String actual = name0.toString ();
        assert expected.equals (actual);
    }

    @Test
    public void testString ()
    throws Exception {
        String expected = string;
        String actual = name0.string ();
        assert expected.equals (actual);
    }

    @Test
    public void testJsonSerialisation ()
    throws Exception {
        String expected = resourceString.trim ().replaceAll ("[\\s\\n]", "");
        String actual = objectMapper.writeValueAsString (name0).replaceAll ("[\\s\\n]", "");
        assert expected.equals (actual);
    }

    @Test
    public void testJsonDeserialisation ()
    throws Exception {
        Name expected = name0;
        Name actual = objectMapper.readValue (resourceString, Name.class);
        assert expected.equals (actual);
    }

}
