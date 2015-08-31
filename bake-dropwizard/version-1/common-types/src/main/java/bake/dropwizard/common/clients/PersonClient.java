
package bake.dropwizard.common.clients;

import bake.dropwizard.common.types.pojos.Person;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.util.List;

public class PersonClient {

    public static final String accession = "http://localhost:10000/persons";
    public static final String mutation = "http://localhost:10000/person";
    private Client client;

    public PersonClient (Client client) {
        this.client = client;
    }

    public List <Person> gottenPersons (Person person) {
        return
            person.queriesById ()?
                gottenPersons (queryById (person)):
            person.queriesByForenameAndSurname ()?
                gottenPersons (queryByForenameAndSurname (person)):
            person.queriesByForename ()?
                gottenPersons (queryByForename (person)):
            person.queriesBySurname ()?
                gottenPersons (queryBySurname (person)):
            gottenPersons ();
    }

    private List <Person> gottenPersons (String query) {
        String target = accession + query;
        return client
            .target (target)
            .request (MediaType.APPLICATION_JSON)
            .get (new GenericType <List <Person>> (Person.class));
    }

    private String queryById (Person person) {
        return String.format ("?id=%s", person.getId ());
    }

    private String queryByForenameAndSurname (Person person) {
        return String
            .format (
                "?forename=%s&surname=%s",
                person.getForename ().string ().replaceAll (" ", "+"),
                person.getSurname ().string ().replaceAll (" ", "+")
            );
    }

    private String queryByForename (Person person) {
        return String.format ("?forename=%s", person.getForename ().string ().replaceAll (" ", "+"));
    }

    private String queryBySurname (Person person) {
        return String.format ("?surname=%s", person.getSurname ().string ().replaceAll (" ", "+"));
    }

    private List <Person> gottenPersons () {
        String target = accession;
        return client
        .target (target)
        .request (MediaType.APPLICATION_JSON)
        .get (new GenericType<List<Person>> (Person.class));
    }

    public Person postedPerson (Person person) {
        return client
            .target (mutation)
            .request (MediaType.APPLICATION_JSON)
            .post (Entity.entity (person, MediaType.APPLICATION_JSON), Person.class);
    }

    public Person putPerson (Person person) {
        return client
            .target (mutation)
            .request (MediaType.APPLICATION_JSON)
            .put (Entity.entity (person, MediaType.APPLICATION_JSON), Person.class);
    }

    public Person deletedPerson (Integer id) {
        return client
            .target (mutation + "?id=" + id)
            .request (MediaType.APPLICATION_JSON)
            .delete (Person.class);
    }

}
