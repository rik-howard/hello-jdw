
package bake.dropwizard.common.clients;

import bake.dropwizard.common.types.pojos.Person;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PersonClientTest {

    PersonClient testee;
    List <Person> persons;
    Person person;
    Client client;
    WebTarget webTarget;
    Builder builder;

    @Before
    public void setUp ()
    throws Exception {
        builder = mock (Builder.class);
        webTarget = mock (WebTarget.class);
        client = mock (Client.class);
        person = mock (Person.class);
        persons = new ArrayList <Person> () {{add (person);}};
        testee = new PersonClient (client);
        when (webTarget.request (MediaType.APPLICATION_JSON)).thenReturn (builder);
        when (builder.get (new GenericType<List<Person>> (Person.class))).thenReturn (persons);
    }

    @After
    public void tearDown ()
    throws Exception {}

    @Test
    public void testGottenPersons ()
    throws Exception {
        when (client.target (PersonClient.accession)).thenReturn (webTarget);
        List <Person> expected = persons;
        List <Person> actual = testee.gottenPersons (Person.person (null, null, null, null, null, null));
        assert expected.equals (actual);
    }

    @Test
    public void testGottenPersonsById ()
    throws Exception {
        when (client.target (PersonClient.accession + "?id=1")).thenReturn (webTarget);
        List <Person> expected = persons;
        List <Person> actual = testee.gottenPersons (Person.person (1, null, null, null, null, null));
        assert expected.equals (actual);
    }

    @Test
    public void testGottenPersonsByForename ()
    throws Exception {
        when (client.target (PersonClient.accession + "?forename=Jimi")).thenReturn (webTarget);
        List <Person> expected = persons;
        List <Person> actual = testee.gottenPersons (Person.person (null, "Jimi", null, null, null, null));
        assert expected.equals (actual);
    }

    @Test
    public void testGottenPersonsBySurname ()
    throws Exception {
        when (client.target (PersonClient.accession + "?surname=Hendrix")).thenReturn (webTarget);
        List <Person> expected = persons;
        List <Person> actual = testee.gottenPersons (Person.person (null, null, "Hendrix", null, null, null));
        assert expected.equals (actual);
    }

    @Test
    public void testGottenPersonsByForenameAndSurname ()
    throws Exception {
        when (client.target (PersonClient.accession + "?forename=Jimi&surname=Hendrix")).thenReturn (webTarget);
        List <Person> expected = persons;
        List <Person> actual = testee.gottenPersons (Person.person (null, "Jimi", "Hendrix", null, null, null));
        assert expected.equals (actual);
    }

    @Test
    public void testPostedPerson ()
    throws Exception {
        when (builder.post (Entity.entity (person, MediaType.APPLICATION_JSON), Person.class)).thenReturn (person);
        when (client.target (PersonClient.mutation)).thenReturn (webTarget);
        Person expected = person;
        Person actual = testee.postedPerson (person);
        assert expected.equals (actual);
    }

    @Test
    public void testPutPerson ()
    throws Exception {
        when (builder.put (Entity.entity (person, MediaType.APPLICATION_JSON), Person.class)).thenReturn (person);
        when (client.target (PersonClient.mutation)).thenReturn (webTarget);
        Person expected = person;
        Person actual = testee.putPerson (person);
        assert expected.equals (actual);
    }

    @Test
    public void testDeletedPerson ()
    throws Exception {
        when (builder.delete (Person.class)).thenReturn (person);
        when (client.target (PersonClient.mutation + "?id=1")).thenReturn (webTarget);
        Person expected = person;
        Person actual = testee.deletedPerson (1);
        assert expected.equals (actual);
    }

}
