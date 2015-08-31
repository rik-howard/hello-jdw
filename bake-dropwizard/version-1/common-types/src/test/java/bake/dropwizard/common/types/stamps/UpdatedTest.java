
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

public class UpdatedTest {

    static String resourceString;
    static ObjectMapper objectMapper;

    DateTime updatedDateTime;

    Updated updated0;
    Updated updated1;
    Updated updated2;

    @BeforeClass
    public static void begin ()
    throws Exception {
        resourceString = Resources.toString (
            Resources.getResource ("stamps/updated.json"),
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
        updatedDateTime = new DateTime ("1970-01-01T00:00:00.000", DateTimeZone.UTC);
        updated0 = new Updated (updatedDateTime);
        updated1 = new Updated (DateTime.parse ("1970-01-01T00:00:00.000+0000"));
        updated2 = new Updated (DateTime.parse ("1970-01-01T00:00:00.000Z"));
    }

    @After
    public void tearDown ()
    throws Exception {}

    @Test
    public void testEquals ()
    throws Exception {
        assert updated0.equals (updated0);
        assert updated0.equals (updated1);
        assert updated1.equals (updated0);
        assert updated0.equals (updated1);
        assert updated1.equals (updated2);
        assert updated0.equals (updated2);
    }

    @Test
    public void testHashCode ()
    throws Exception {
        int expected = updated0.hashCode ();
        int actual = updated1.hashCode ();
        assert expected == actual;
    }

    @Test
    public void testToString ()
    throws Exception {
        String expected = "1970-01-01T00:00:00.000Z";
        String actual = updated0.toString ();
        assert expected.equals (actual);
    }

    @Test
    public void testDateTime ()
    throws Exception {
        DateTime expected = updatedDateTime;
        DateTime actual = updated0.dateTime ();
        assert expected.equals (actual);
    }

    @Test
    public void testJsonSerialisation ()
    throws Exception {
        String expected = resourceString.trim ().replaceAll ("[\\s\\n]", "");
        String actual = objectMapper.writeValueAsString (updated0);
        assert expected.equals (actual);
    }

    @Test
    public void testJsonDeserialisation ()
    throws Exception {
        Updated expected = updated0;
        Updated actual = objectMapper.readValue (resourceString, Updated.class);
        assert expected.equals (actual);
    }

}
