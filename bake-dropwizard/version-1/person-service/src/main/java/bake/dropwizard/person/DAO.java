
package bake.dropwizard.person;

import bake.dropwizard.common.types.pojos.Person;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Collections;
import java.util.stream.Collectors;

public class DAO {

    private static String resourceContentString (String resourcePathString)
    throws IOException {
        return Resources.toString (
            Resources.getResource (resourcePathString),
            Charsets.UTF_8
        );
    }

    private static String selectionStatement;
    private static String insertionTemplate;
    private static String updatingTemplate;
    private static String deletionTemplate;
    private Session session;

    public DAO (Session session)
    throws IOException {
        this.session = session;
        selectionStatement = resourceContentString ("dao/selection-statement.cql");
        insertionTemplate = resourceContentString ("dao/insertion-template.cql");
        updatingTemplate = resourceContentString ("dao/updating-template.cql");
        deletionTemplate = resourceContentString ("dao/deletion-template.cql");
    }

    private Integer nextId () {
        List <Integer> ids = session
            .execute ("select id from persons;")
            .all ()
            .stream ()
            .map (row -> new Integer (row.getInt ("id")))
            .collect (Collectors.toList ());
        Collections.sort (ids);
        return ids.get (ids.size () - 1) + 1;
    }

    List <Person> selectedPersons () {
        return session
            .execute (selectionStatement)
            .all ()
            .stream ()
            .map (row -> person (row))
            .collect (Collectors.toList ());
    }

    private static Person person (Row row) {
        Integer idInteger = row.getInt ("id");
        String forenameString = row.getString ("forename");
        String surnameString = row.getString ("surname");
        Date insertedDate = row.getDate ("inserted");
        Date updatedDate = row.getDate ("updated");
        Date deletedDate = row.getDate ("deleted");
        DateTime insertedDateTime = insertedDate == null? null: new DateTime (insertedDate.getTime (), DateTimeZone.UTC);
        DateTime updatedDateTime = updatedDate == null? null: new DateTime (updatedDate.getTime (), DateTimeZone.UTC);
        DateTime deletedDateTime = deletedDate == null? null: new DateTime (deletedDate.getTime (), DateTimeZone.UTC);
        return Person.person (
            idInteger,
            forenameString,
            surnameString,
            insertedDateTime,
            updatedDateTime,
            deletedDateTime
        );
    }

    void insertPerson (Person person) {
        String statement = String
            .format (
                insertionTemplate,
                nextId (),
                person.getForename (),
                person.getSurname (),
                DateTime.now ()
            );
        ResultSet resultSet = session.execute (statement);
    }

    void updatePerson (Person person) {
        String statement = String
            .format (
            updatingTemplate,
                person.getForename (),
                person.getSurname (),
                DateTime.now (),
                person.getId ()
            );
        ResultSet resultSet = session.execute (statement);
    }

    void deletePerson (Person person) {
        String statement = String
            .format (
                deletionTemplate,
                DateTime.now (),
                person.getId ()
            );
        ResultSet resultSet = session.execute (statement);
    }

}
