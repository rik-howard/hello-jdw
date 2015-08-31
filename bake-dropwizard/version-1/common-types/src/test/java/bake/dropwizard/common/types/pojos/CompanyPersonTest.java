
package bake.dropwizard.common.types.pojos;

import bake.dropwizard.common.exceptions.dao.ExistenceException;
import bake.dropwizard.common.types.fields.CompanyId;
import bake.dropwizard.common.types.fields.CompanyPersonId;
import bake.dropwizard.common.types.fields.PersonId;
import bake.dropwizard.common.types.stamps.Inserted;
import bake.dropwizard.common.types.stamps.Updated;
import bake.dropwizard.common.types.stamps.Deleted;
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

public class CompanyPersonTest {

    static String resourceString;
    static ObjectMapper objectMapper;

    CompanyPersonId id;
    CompanyId companyId;
    PersonId personId;
    Inserted inserted;
    Updated updated;
    Deleted deleted;

    CompanyPerson companyPerson0;
    CompanyPerson companyPerson1;
    CompanyPerson companyPerson2;
    CompanyPerson creatableCompanyPerson;
    CompanyPerson recreatableCompanyPerson;
    CompanyPerson decreatableCompanyPerson;

    @BeforeClass
    public static void begin ()
    throws Exception {
        resourceString = Resources.toString (
            Resources.getResource ("pojos/company-person.json"),
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
        id = new CompanyPersonId (0);
        companyId = new CompanyId (0);
        personId = new PersonId (0);
        inserted = new Inserted (DateTime.parse ("1970-01-01T00:00:00.000Z"));
        updated = new Updated (DateTime.parse ("1971-01-01T00:00:00.000Z"));
        deleted = new Deleted (DateTime.parse ("1972-01-01T00:00:00.000Z"));
        companyPerson0 = new CompanyPerson (id, companyId, personId, inserted, null, null);
        companyPerson1 = new CompanyPerson (id, companyId, personId, inserted, null, null);
        companyPerson2 = new CompanyPerson (id, companyId, personId, inserted, null, null);
        creatableCompanyPerson = new CompanyPerson (null, companyId, personId, null, null, null);
        recreatableCompanyPerson = new CompanyPerson (id, companyId, personId, inserted, null, null);
        decreatableCompanyPerson = new CompanyPerson (id, companyId, personId, inserted, updated, null);
    }

    @After
    public void tearDown ()
    throws Exception {}

    @Test
    public void testGetId ()
    throws Exception {
        CompanyPersonId expected = id;
        CompanyPersonId actual = companyPerson0.getId ();
        assert expected.equals (actual);
    }

    @Test
    public void testGetCompanyId ()
    throws Exception {
        CompanyId expected = companyId;
        CompanyId actual = companyPerson0.getCompanyId ();
        assert expected.equals (actual);
    }

    @Test
    public void testGetPersonId ()
    throws Exception {
        PersonId expected = personId;
        PersonId actual = companyPerson0.getPersonId ();
        assert expected.equals (actual);
    }

    @Test
    public void testGetInserted ()
    throws Exception {
        Inserted expected = inserted;
        Inserted actual = recreatableCompanyPerson.getInserted ();
        assert expected.equals (actual);
    }

    @Test
    public void testGetUpdated ()
    throws ExistenceException {
        Updated expected = updated;
        Updated actual = decreatableCompanyPerson.getUpdated ();
        assert expected.equals (actual);
    }

    @Test
    public void testGetDeleted ()
    throws ExistenceException {
        Deleted expected = null;
        Deleted actual = creatableCompanyPerson.getDeleted ();
        assert expected == actual;
    }

    @Test
    public void testEquals ()
    throws Exception {
        assert companyPerson0.equals (companyPerson0);
        assert companyPerson0.equals (companyPerson1);
        assert companyPerson1.equals (companyPerson0);
        assert companyPerson0.equals (companyPerson1);
        assert companyPerson1.equals (companyPerson2);
        assert companyPerson0.equals (companyPerson2);
    }

    @Test
    public void testHashCode ()
    throws Exception {
        int expected = companyPerson0.hashCode ();
        int actual = companyPerson1.hashCode ();
        assert expected == actual;
    }

    @Test
    public void testToString ()
    throws Exception {
        String expected = "CompanyPerson{id:0;companyId:0;personId:0;inserted:1970-01-01T00:00:00.000Z;updated:null;deleted:null}";
        String actual = companyPerson0.toString ();
        //System.out.println (actual);
        assert expected.equals (actual);
    }

    @Test
    public void testJsonSerialisation ()
    throws Exception {
        String expected = resourceString.trim ().replaceAll ("[\\s\\n]", "");
        //System.out.println (expected);
        String actual = objectMapper.writeValueAsString (companyPerson0).replaceAll ("[\\s\\n]", "");
        //System.out.println (actual);
        assert expected.equals (actual);
    }

    @Test
    public void testJsonDeserialisation ()
    throws Exception {
        CompanyPerson expected = companyPerson0;
        CompanyPerson actual = objectMapper.readValue (resourceString, CompanyPerson.class);
        assert expected.equals (actual);
    }

    @Test
    public void testCanBeCreated () {
        assert creatableCompanyPerson.canBeCreated ();
    }

    @Test
    public void testCanBeRecreated () {
        assert recreatableCompanyPerson.canBeRecreated ();
    }

    @Test
    public void testCanBeDecreated () {
        assert decreatableCompanyPerson.canBeDecreated ();
    }

    @Test
    public void testQueriesById () {
        assert CompanyPerson.companyPerson (0, null, null, null, null, null).queriesById ();
    }

    @Test
    public void testQueriesByCompanyIdAndPersonId () {
        assert CompanyPerson.companyPerson (null, 0, 0, null, null, null).queriesByCompanyIdAndPersonId ();
    }

    @Test
    public void testQueriesByCompanyId () {
        assert CompanyPerson.companyPerson (null, 0, null, null, null, null).queriesByCompanyId ();
    }

    @Test
    public void testQueriesByPersonId () {
        assert CompanyPerson.companyPerson (null, null, 0, null, null, null).queriesByPersonId ();
    }

}
