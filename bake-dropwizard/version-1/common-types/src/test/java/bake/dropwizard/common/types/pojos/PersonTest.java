
package bake.dropwizard.common.types.pojos;

import bake.dropwizard.common.exceptions.dao.ExistenceException;
import bake.dropwizard.common.types.fields.Forename;
import bake.dropwizard.common.types.fields.PersonId;
import bake.dropwizard.common.types.fields.Surname;
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

public class PersonTest {

    static String resourceString;
    static ObjectMapper objectMapper;

    PersonId id;
    Forename forename;
    Surname surname;
    Inserted inserted;
    Updated updated;
    Deleted deleted;

    Person person0;
    Person person1;
    Person person2;
    Person creatablePerson;
    Person recreatablePerson;
    Person decreatablePerson;

    @BeforeClass
    public static void begin ()
    throws Exception {
        resourceString = Resources.toString (
            Resources.getResource ("pojos/person.json"),
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
        id = new PersonId (0);
        forename = new Forename ("Jimi");
        surname = new Surname ("Hendrix");
        inserted = new Inserted (DateTime.parse ("1970-01-01T00:00:00.000Z"));
        updated = new Updated (DateTime.parse ("1971-01-01T00:00:00.000Z"));
        deleted = new Deleted (DateTime.parse ("1972-01-01T00:00:00.000Z"));
        person0 = new Person (id, forename, surname, inserted, null, null);
        person1 = new Person (id, forename, surname, inserted, null, null);
        person2 = new Person (id, forename, surname, inserted, null, null);
        creatablePerson = new Person (null, forename, surname, null, null, null);
        recreatablePerson = new Person (id, forename, surname, inserted, null, null);
        decreatablePerson = new Person (id, forename, surname, inserted, updated, null);
    }

    @After
    public void tearDown ()
    throws Exception {}

    @Test
    public void testGetId ()
    throws Exception {
        PersonId expected = id;
        PersonId actual = person0.getId ();
        assert expected.equals (actual);
    }

    @Test
    public void testGetForename ()
    throws Exception {
        Forename expected = forename;
        Forename actual = person0.getForename ();
        assert expected.equals (actual);
    }

    @Test
    public void testGetSurname ()
    throws Exception {
        Surname expected = surname;
        Surname actual = person0.getSurname ();
        assert expected.equals (actual);
    }

    @Test
    public void testGetInserted ()
    throws Exception {
        Inserted expected = inserted;
        Inserted actual = recreatablePerson.getInserted ();
        assert expected.equals (actual);
    }

    @Test
    public void testGetUpdated ()
    throws ExistenceException {
        Updated expected = updated;
        Updated actual = decreatablePerson.getUpdated ();
        assert expected.equals (actual);
    }

    @Test
    public void testGetDeleted ()
    throws ExistenceException {
        Deleted expected = null;
        Deleted actual = decreatablePerson.getDeleted ();
        assert expected == actual;
    }

    @Test
    public void testEquals ()
    throws Exception {
        assert person0.equals (person0);
        assert person0.equals (person1);
        assert person1.equals (person0);
        assert person0.equals (person1);
        assert person1.equals (person2);
        assert person0.equals (person2);
    }

    @Test
    public void testHashCode ()
    throws Exception {
        int expected = person0.hashCode ();
        int actual = person1.hashCode ();
        assert expected == actual;
    }

    @Test
    public void testToString ()
    throws Exception {
        String expected = "Person{id:0;forename:'Jimi';surname:'Hendrix';inserted:1970-01-01T00:00:00.000Z;updated:null;deleted:null}";
        String actual = person0.toString ();
        //System.out.println (actual);
        assert expected.equals (actual);
    }

    @Test
    public void testJsonSerialisation ()
    throws Exception {
        String expected = resourceString.trim ().replaceAll ("[\\s\\n]", "");
        String actual = objectMapper.writeValueAsString (person0).replaceAll ("[\\s\\n]", "");
        //System.out.println (actual);
        assert expected.equals (actual);
    }

    @Test
    public void testJsonDeserialisation ()
    throws Exception {
        Person expected = person0;
        Person actual = objectMapper.readValue (resourceString, Person.class);
        assert expected.equals (actual);
    }

    @Test
    public void testCanBeCreated () {
        assert creatablePerson.canBeCreated ();
    }

    @Test
    public void testCanBeRecreated () {
        assert recreatablePerson.canBeRecreated ();
    }

    @Test
    public void testCanBeDecreated () {
        assert decreatablePerson.canBeDecreated ();
    }

    @Test
    public void testQueriesById () {
        assert Person.person (0, null, null, null, null, null).queriesById ();
    }

    @Test
    public void testQueriesByForenameAndSurname () {
        assert Person.person (null, "", "", null, null, null).queriesByForenameAndSurname ();
    }

    @Test
    public void testQueriesByForename () {
        assert Person.person (null, "", null, null, null, null).queriesByForename ();
    }

    @Test
    public void testQueriesBySurname () {
        assert Person.person (null, null, "", null, null, null).queriesBySurname ();
    }

}
