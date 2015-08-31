
package bake.dropwizard.common.types.pojos;

import bake.dropwizard.common.exceptions.dao.ExistenceException;
import bake.dropwizard.common.types.fields.Name;
import bake.dropwizard.common.types.fields.CompanyId;
import bake.dropwizard.common.types.stamps.Deleted;
import bake.dropwizard.common.types.stamps.Inserted;
import bake.dropwizard.common.types.stamps.Updated;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import io.dropwizard.jackson.Jackson;
import org.joda.time.DateTime;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CompanyTest {

    static String resourceString;
    static ObjectMapper objectMapper;

    CompanyId id;
    Name name;
    Inserted inserted;
    Updated updated;
    Deleted deleted;

    Company company0;
    Company company1;
    Company company2;
    Company creatableCompany;
    Company recreatableCompany;
    Company decreatableCompany;

    @BeforeClass
    public static void begin ()
    throws Exception {
        resourceString = Resources.toString (
            Resources.getResource ("pojos/company.json"),
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
        id = new CompanyId (0);
        name = new Name ("the Experience");
        inserted = new Inserted (DateTime.parse ("1970-01-01T00:00:00.000Z"));
        updated = new Updated (DateTime.parse ("1971-01-01T00:00:00.000Z"));
        deleted = new Deleted (DateTime.parse ("1972-01-01T00:00:00.000Z"));
        company0 = new Company (id, name, inserted, null, null);
        company1 = new Company (id, name, inserted, null, null);
        company2 = new Company (id, name, inserted, null, null);
        creatableCompany = new Company (null, name, null, null, null);
        recreatableCompany = new Company (id, name, inserted, null, null);
        decreatableCompany = new Company (id, name, inserted, updated, null);
    }

    @After
    public void tearDown ()
    throws Exception {}

    @Test
    public void testGetId ()
    throws Exception {
        CompanyId expected = id;
        CompanyId actual = company0.getId ();
        assert expected.equals (actual);
    }

    @Test
    public void testGetName ()
    throws Exception {
        Name expected = name;
        Name actual = company0.getName ();
        assert expected.equals (actual);
    }

    @Test
    public void testGetInserted ()
    throws Exception {
        Inserted expected = inserted;
        Inserted actual = recreatableCompany.getInserted ();
        assert expected.equals (actual);
    }

    @Test
    public void testGetUpdated ()
    throws ExistenceException {
        Updated expected = updated;
        Updated actual = decreatableCompany.getUpdated ();
        assert expected.equals (actual);
    }

    @Test
    public void testGetDeleted ()
    throws ExistenceException {
        Deleted expected = null;
        Deleted actual = creatableCompany.getDeleted ();
        assert expected == actual;
    }

    @Test
    public void testEquals ()
    throws Exception {
        assert company0.equals (company0);
        assert company0.equals (company1);
        assert company1.equals (company0);
        assert company0.equals (company1);
        assert company1.equals (company2);
        assert company0.equals (company2);
    }

    @Test
    public void testHashCode ()
    throws Exception {
        int expected = company0.hashCode ();
        int actual = company1.hashCode ();
        assert expected == actual;
    }

    @Test
    public void testToString ()
    throws Exception {
        String expected = "Company{id:0;name:'the Experience';inserted:1970-01-01T00:00:00.000Z;updated:null;deleted:null}";
        String actual = company0.toString ();
        //System.out.println (actual);
        assert expected.equals (actual);
    }

    @Test
    public void testJsonSerialisation ()
    throws Exception {
        String expected = resourceString.trim ().replaceAll ("[\\s\\n]", "");
        String actual = objectMapper.writeValueAsString (company0).replaceAll ("[\\s\\n]", "");
        //System.out.println (actual);
        assert expected.equals (actual);
    }

    @Test
    public void testJsonDeserialisation ()
    throws Exception {
        Company expected = company0;
        Company actual = objectMapper.readValue (resourceString, Company.class);
        assert expected.equals (actual);
    }

    @Test
    public void testCanBeCreated () {
        assert creatableCompany.canBeCreated ();
    }

    @Test
    public void testCanBeRecreated () {
        assert recreatableCompany.canBeRecreated ();
    }

    @Test
    public void testCanBeDecreated () {
        assert decreatableCompany.canBeDecreated ();
    }

    @Test
    public void testQueriesById () {
        assert Company.company (0, null, null, null, null).queriesById ();
    }

    @Test
    public void testQueriesByName () {
        assert Company.company (null, "", null, null, null).queriesByName ();
    }

}
