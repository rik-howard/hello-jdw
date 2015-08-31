
package bake.dropwizard.person;

import bake.dropwizard.common.types.exceptions.dao.ExistenceException;
import bake.dropwizard.common.types.exceptions.dao.NonexistenceException;
import bake.dropwizard.common.types.exceptions.dao.NonuniquenessException;
import bake.dropwizard.common.types.fields.Forename;
import bake.dropwizard.common.types.fields.PersonId;
import bake.dropwizard.common.types.fields.Surname;
import bake.dropwizard.common.types.pojos.Person;

import java.util.List;
import java.util.stream.Collectors;

public class Worker {

    private DAO dao;

    public Worker (DAO dao) {
        this.dao = dao;
    }

    List <Person> selectedPersons () {
        return dao.selectedPersons ();
    }

    List <Person> selectedPersons (PersonId id) {
        return dao
            .selectedPersons ()
            .stream ()
            .filter (person -> person.getId ().equals (id))
            .collect (Collectors.toList ());
    }

    List <Person> selectedPersons (Forename forename) {
        return dao
            .selectedPersons ()
            .stream ()
            .filter (person -> person.getForename ().equals (forename))
            .collect (Collectors.toList ());
    }

    List <Person> selectedPersons (Surname surname) {
        return dao
            .selectedPersons ()
            .stream ()
            .filter (person -> person.getSurname ().equals (surname))
            .collect (Collectors.toList ());
    }

    List <Person> selectedPersons (Forename forename, Surname surname) {
        return dao
            .selectedPersons ()
            .stream ()
            .filter (person -> person.getForename ().equals (forename))
            .filter (person -> person.getSurname ().equals (surname))
            .collect (Collectors.toList ());
    }

    Person insertedPerson (Person person)
    throws ExistenceException {
        List <Person> found = selectedPersons (person.getForename (), person.getSurname ())
            .stream ()
            .filter (p -> p.getDeleted () == null)
            .collect (Collectors.toList ());
        if (found.size () > 0)
            throw new ExistenceException ();
        dao.insertPerson (person);
        return selectedPersons (person.getForename (), person.getSurname ()).get (0);
    }

    Person updatedPerson (Person person)
    throws NonexistenceException, NonuniquenessException {
        List <Person> found = selectedPersons (person.getId ());
        found = found
            .stream ()
            .filter (p -> p.getInserted ().dateTime ().equals (person.getInserted ().dateTime ()))
            .collect (Collectors.toList ());
        if (found.isEmpty ())
            throw new NonexistenceException ();
        if (found.size () > 1)
            throw new NonuniquenessException ();
        dao.updatePerson (person);
        found = selectedPersons (person.getId ());
        return found.get (0);
    }

    Person deletedPerson (Person person) {
        List <Person> found = selectedPersons (person.getId ())
            .stream ()
            .filter (p -> p.getDeleted () == null)
            .collect (Collectors.toList ());
        if (found.isEmpty ())
            throw new NonexistenceException ();
        if (found.size () > 1)
            throw new NonuniquenessException ();
        dao.deletePerson (person);
        found = selectedPersons (person.getId ());
        return found.get (0);
    }

}
