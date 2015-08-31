
package bake.dropwizard.company.person;

import bake.dropwizard.common.pojo.CompanyPerson;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;

public class DAO {

    private static Integer id = 0;
    private List <CompanyPerson> companyPersons = new ArrayList <CompanyPerson> ();

    public Boolean isConnected () {
        return true;
    }

    List <CompanyPerson> companyPersons () {
        return companyPersons;
    }

    List <CompanyPerson> companyPersonsById (Integer id) {
        return companyPersons
            .stream ()
            .filter (cp -> cp.getId ().equals (id))
            .collect (Collectors.toList ());
    }

    List <CompanyPerson> companyPersonsByCompanyId (Integer companyId) {
        return companyPersons
            .stream ()
            .filter (cp -> cp.getCompanyId ().equals (companyId))
            .collect (Collectors.toList ());
    }

    List <CompanyPerson> companyPersonsByPersonId (Integer personId) {
        return companyPersons
            .stream ()
            .filter (cp -> cp.getPersonId ().equals (personId))
            .collect (Collectors.toList ());
    }

    List <CompanyPerson> companyPersonsByCompanyIdPersonId (Integer companyId, Integer personId) {
        return companyPersons
            .stream ()
            .filter (cp -> cp.getCompanyId ().equals (companyId))
            .filter (cp -> cp.getPersonId ().equals (personId))
            .collect (Collectors.toList ());
    }

    CompanyPerson companyPersonById (Integer id) {
        List <CompanyPerson> companyPersons = companyPersonsById (id);
        return singleton (companyPersons);
    }

    CompanyPerson companyPersonByCompanyIdPersonId (Integer companyId, Integer personId) {
        List <CompanyPerson> companyPersons = companyPersonsByCompanyIdPersonId (companyId, personId);
        return singleton (companyPersons);
    }

    private static CompanyPerson singleton (List <CompanyPerson> companyPersons) {
        if (companyPersons.isEmpty ())
            throw new WebApplicationException (Status.EXPECTATION_FAILED);
        else if (companyPersons.size () > 1)
            throw new WebApplicationException (Status.CONFLICT);
        else
            return companyPersons.get (0);
    }

    CompanyPerson insertedCompanyPerson (final CompanyPerson companyPerson) {
        Integer companyId = companyPerson.getCompanyId ();
        Integer personId = companyPerson.getPersonId ();
        List <CompanyPerson> companyPersonsByCompanyIdPersonId = companyPersonsByCompanyIdPersonId (companyId, personId);
        findCompanyPersonNot (companyPersonsByCompanyIdPersonId);
        CompanyPerson addee = new CompanyPerson (++id, companyId, personId);
        companyPersons.add (addee);
        return companyPersonById (addee.getId ());
    }

    private static void findCompanyPersonNot (List <CompanyPerson> companyPersons) {
        if (companyPersons.isEmpty ())
            return;
        else
            throw new WebApplicationException (Status.CONFLICT);
    }

}
