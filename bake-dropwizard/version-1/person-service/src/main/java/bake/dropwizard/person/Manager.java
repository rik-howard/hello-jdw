
package bake.dropwizard.person;

import bake.dropwizard.common.exceptions.dao.ExistenceException;
import bake.dropwizard.common.exceptions.dao.NonexistenceException;
import bake.dropwizard.common.exceptions.dao.NonuniquenessException;
import bake.dropwizard.common.types.fields.Forename;
import bake.dropwizard.common.types.fields.PersonId;
import bake.dropwizard.common.types.fields.Surname;
import bake.dropwizard.common.types.pojos.Person;

import java.util.List;

public class Manager {

    private Worker worker;

    public Manager (Worker worker) {
        this.worker = worker;
    }

    List <Person> accessedPersons (PersonId personId, Forename forename, Surname surname) {
        Person query = new Person (personId, forename, surname, null, null, null);
        return
            query.queriesById ()?
                worker.selectedPersons (personId):
            query.queriesByForename ()?
                worker.selectedPersons (forename):
            query.queriesBySurname ()?
                worker.selectedPersons (surname):
            query.queriesByForenameAndSurname ()?
                worker.selectedPersons (forename, surname):
            worker.selectedPersons ();
    }

    Person createdPerson (Person person)
    throws ExistenceException {
        return worker.insertedPerson (person);
    }

    Person recreatedPerson (Person person)
    throws NonexistenceException, NonuniquenessException {
        return worker.updatedPerson (person);
    }

    Person decreatedPerson (Person person)
    throws NonexistenceException, NonuniquenessException {
        return worker.deletedPerson (person);
    }

}
