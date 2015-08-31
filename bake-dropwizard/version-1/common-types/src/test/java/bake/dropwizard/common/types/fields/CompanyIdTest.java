
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

public class CompanyIdTest {

    static String resourceString;
    static ObjectMapper objectMapper;

    Integer integer;

    CompanyId companyId0;
    CompanyId companyId1;
    CompanyId companyId2;

    @BeforeClass
    public static void begin ()
    throws Exception {
        resourceString = Resources.toString (
            Resources.getResource ("fields/company-id.json"),
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
        companyId0 = new CompanyId (integer);
        companyId1 = new CompanyId (integer);
        companyId2 = new CompanyId (integer);
    }

    @After
    public void tearDown ()
    throws Exception {
        companyId0 = null;
        companyId1 = null;
        companyId2 = null;
    }

    @Test
    public void testEquals ()
    throws Exception {
        // reflexive
        assert companyId0.equals (companyId0);
        // symmetric
        assert companyId0.equals (companyId1);
        assert companyId1.equals (companyId0);
        // transitive
        assert companyId0.equals (companyId1);
        assert companyId1.equals (companyId2);
        assert companyId0.equals (companyId2);
    }

    @Test
    public void testHashCode ()
    throws Exception {
        int expected = companyId0.hashCode ();
        int actual = companyId1.hashCode ();
        assert expected == actual;
    }

    @Test
    public void testToString ()
    throws Exception {
        String expected = integer.toString ();
        String actual = companyId0.toString ();
        assert expected.equals (actual);
    }

    @Test
    public void testInteger ()
    throws Exception {
        Integer expected = integer;
        Integer actual = companyId0.integer ();
        assert expected.equals (actual);
    }

    @Test
    public void testJsonSerialisation ()
    throws Exception {
        String expected = resourceString.trim ().replaceAll ("[\\s\\n]", "");
        String actual = objectMapper.writeValueAsString (companyId0);
        assert expected.equals (actual);
    }

    @Test
    public void testJsonDeserialisation ()
    throws Exception {
        CompanyId expected = companyId0;
        CompanyId actual = objectMapper.readValue (resourceString, CompanyId.class);
        assert expected.equals (actual);
    }

}
