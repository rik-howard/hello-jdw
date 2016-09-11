
package bake.dropwizard.person;

import bake.dropwizard.common.exceptions.dao.ExistenceException;
import bake.dropwizard.common.exceptions.dao.NonexistenceException;
import bake.dropwizard.common.exceptions.dao.NonuniquenessException;
import bake.dropwizard.common.types.fields.Forename;
import bake.dropwizard.common.types.fields.PersonId;
import bake.dropwizard.common.types.fields.Surname;
import bake.dropwizard.common.types.pojos.Person;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;
import java.util.List;

public class Director {

    private Manager manager;

    public Director (Manager manager) {
        this.manager = manager;
    }

    List <Person> gottenPersons (
        PersonId id,
        Forename forename,
        Surname surname
    ) {
        return manager.accessedPersons (
            id,
            forename,
            surname
        );
    }

    Person postedPerson (Person person) {
        if (person.canBeCreated ())
            try {
                return manager.createdPerson (person);
            }
            catch (ExistenceException e) {
                throw new WebApplicationException (Status.CONFLICT);
            }
            catch (Exception e) {
                System.out.println (e.toString ());
                throw new WebApplicationException (Status.INTERNAL_SERVER_ERROR);
            }
        throw new WebApplicationException (Status.NOT_ACCEPTABLE);
    }

    Person putPerson (Person person) {
        if (person.canBeRecreated ())
            try {
                return manager.recreatedPerson (person);
            }
            catch (NonexistenceException e) {
                throw new WebApplicationException (Status.EXPECTATION_FAILED);
            }
            catch (NonuniquenessException e) {
                throw new WebApplicationException (Status.CONFLICT);
            }
            catch (Exception e) {
                System.out.println (e.toString ());
                throw new WebApplicationException (Status.INTERNAL_SERVER_ERROR);
            }
        throw new WebApplicationException (Status.NOT_ACCEPTABLE);
    }

    Person deletedPerson (PersonId id) {
        Person deletee = new Person (id, null, null, null, null, null);
        if (deletee.canBeDecreated ())
            try {
                return manager.decreatedPerson (deletee);
            }
            catch (NonexistenceException e) {
                throw new WebApplicationException (Status.EXPECTATION_FAILED);
            }
            catch (NonuniquenessException e) {
                throw new WebApplicationException (Status.CONFLICT);
            }
            catch (Exception e) {
                System.out.println (e.toString ());
                throw new WebApplicationException (Status.INTERNAL_SERVER_ERROR);
            }
        throw new WebApplicationException (Status.NOT_ACCEPTABLE);
    }

}
