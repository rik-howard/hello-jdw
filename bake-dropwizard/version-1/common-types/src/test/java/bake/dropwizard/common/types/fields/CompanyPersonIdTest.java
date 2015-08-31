
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

public class CompanyPersonIdTest {

    static String resourceString;
    static ObjectMapper objectMapper;

    Integer integer;

    CompanyPersonId companyPersonId0;
    CompanyPersonId companyPersonId1;
    CompanyPersonId companyPersonId2;

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
        companyPersonId0 = new CompanyPersonId (integer);
        companyPersonId1 = new CompanyPersonId (integer);
        companyPersonId2 = new CompanyPersonId (integer);
    }

    @After
    public void tearDown ()
    throws Exception {
        companyPersonId0 = null;
        companyPersonId1 = null;
        companyPersonId2 = null;
    }

    @Test
    public void testEquals ()
    throws Exception {
        // reflexive
        assert companyPersonId0.equals (companyPersonId0);
        // symmetric
        assert companyPersonId0.equals (companyPersonId1);
        assert companyPersonId1.equals (companyPersonId0);
        // transitive
        assert companyPersonId0.equals (companyPersonId1);
        assert companyPersonId1.equals (companyPersonId2);
        assert companyPersonId0.equals (companyPersonId2);
    }

    @Test
    public void testHashCode ()
    throws Exception {
        int expected = companyPersonId0.hashCode ();
        int actual = companyPersonId1.hashCode ();
        assert expected == actual;
    }

    @Test
    public void testToString ()
    throws Exception {
        String expected = integer.toString ();
        String actual = companyPersonId0.toString ();
        assert expected.equals (actual);
    }

    @Test
    public void testInteger ()
    throws Exception {
        Integer expected = integer;
        Integer actual = companyPersonId0.integer ();
        assert expected.equals (actual);
    }

    @Test
    public void testJsonSerialisation ()
    throws Exception {
        String expected = resourceString.trim ().replaceAll ("[\\s\\n]", "");
        String actual = objectMapper.writeValueAsString (companyPersonId0);
        assert expected.equals (actual);
    }

    @Test
    public void testJsonDeserialisation ()
    throws Exception {
        CompanyPersonId expected = companyPersonId0;
        CompanyPersonId actual = objectMapper.readValue (resourceString, CompanyPersonId.class);
        assert expected.equals (actual);
    }

}
