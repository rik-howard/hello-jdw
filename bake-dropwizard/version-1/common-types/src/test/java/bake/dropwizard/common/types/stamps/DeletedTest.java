
package bake.dropwizard.common.types.stamps;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import io.dropwizard.jackson.Jackson;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class DeletedTest {

    static String resourceString;
    static ObjectMapper objectMapper;

    DateTime deletedDateTime;

    Deleted deleted0;
    Deleted deleted1;
    Deleted deleted2;

    @BeforeClass
    public static void begin ()
    throws Exception {
        resourceString = Resources.toString (
            Resources.getResource ("stamps/deleted.json"),
            Charsets.UTF_8
        );
        objectMapper = Jackson.newObjectMapper ();
        objectMapper.configure (SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.configure (DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @AfterClass
    public static void end ()
    throws Exception {}

    @Before
    public void setUp ()
    throws Exception {
        deletedDateTime = new DateTime ("1970-01-01T00:00:00.000", DateTimeZone.UTC);
        deleted0 = new Deleted (deletedDateTime);
        deleted1 = new Deleted (DateTime.parse ("1970-01-01T00:00:00.000+0000"));
        deleted2 = new Deleted (DateTime.parse ("1970-01-01T00:00:00.000Z"));
    }

    @After
    public void tearDown ()
    throws Exception {}

    @Test
    public void testEquals ()
    throws Exception {
        assert deleted0.equals (deleted0);
        assert deleted0.equals (deleted1);
        assert deleted1.equals (deleted0);
        assert deleted0.equals (deleted1);
        assert deleted1.equals (deleted2);
        assert deleted0.equals (deleted2);
    }

    @Test
    public void testHashCode ()
    throws Exception {
        int expected = deleted0.hashCode ();
        int actual = deleted1.hashCode ();
        assert expected == actual;
    }

    @Test
    public void testToString ()
    throws Exception {
        String expected = "1970-01-01T00:00:00.000Z";
        String actual = deleted0.toString ();
        assert expected.equals (actual);
    }

    @Test
    public void testDateTime ()
    throws Exception {
        DateTime expected = deletedDateTime;
        DateTime actual = deleted0.dateTime ();
        assert expected.equals (actual);
    }

    @Test
    public void testJsonSerialisation ()
    throws Exception {
        String expected = resourceString.trim ().replaceAll ("[\\s\\n]", "");
        String actual = objectMapper.writeValueAsString (deleted0);
        assert expected.equals (actual);
    }

    @Test
    public void testJsonDeserialisation ()
    throws Exception {
        Deleted expected = deleted0;
        Deleted actual = objectMapper.readValue (resourceString, Deleted.class);
        assert expected.equals (actual);
    }

}
