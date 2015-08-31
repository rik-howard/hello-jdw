
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

public class InsertedTest {

    static String resourceString;
    static ObjectMapper objectMapper;

    DateTime insertedDateTime;

    Inserted inserted0;
    Inserted inserted1;
    Inserted inserted2;

    @BeforeClass
    public static void begin ()
    throws Exception {
        resourceString = Resources.toString (
            Resources.getResource ("stamps/inserted.json"),
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
        insertedDateTime = new DateTime ("1970-01-01T00:00:00.000", DateTimeZone.UTC);
        inserted0 = new Inserted (insertedDateTime);
        inserted1 = new Inserted (DateTime.parse ("1970-01-01T00:00:00.000+0000"));
        inserted2 = new Inserted (DateTime.parse ("1970-01-01T00:00:00.000Z"));
    }

    @After
    public void tearDown ()
    throws Exception {}

    @Test
    public void testEquals ()
    throws Exception {
        assert inserted0.equals (inserted0);
        assert inserted0.equals (inserted1);
        assert inserted1.equals (inserted0);
        assert inserted0.equals (inserted1);
        assert inserted1.equals (inserted2);
        assert inserted0.equals (inserted2);
    }

    @Test
    public void testHashCode ()
    throws Exception {
        int expected = inserted0.hashCode ();
        int actual = inserted1.hashCode ();
        assert expected == actual;
    }

    @Test
    public void testToString ()
    throws Exception {
        String expected = "1970-01-01T00:00:00.000Z";
        String actual = inserted0.toString ();
        assert expected.equals (actual);
    }

    @Test
    public void testDateTime ()
    throws Exception {
        DateTime expected = insertedDateTime;
        DateTime actual = inserted0.dateTime ();
        assert expected.equals (actual);
    }

    @Test
    public void testJsonSerialisation ()
    throws Exception {
        String expected = resourceString.trim ().replaceAll ("[\\s\\n]", "");
        String actual = objectMapper.writeValueAsString (inserted0);
        assert expected.equals (actual);
    }

    @Test
    public void testJsonDeserialisation ()
    throws Exception {
        Inserted expected = inserted0;
        Inserted actual = objectMapper.readValue (resourceString, Inserted.class);
        assert expected.equals (actual);
    }

}
