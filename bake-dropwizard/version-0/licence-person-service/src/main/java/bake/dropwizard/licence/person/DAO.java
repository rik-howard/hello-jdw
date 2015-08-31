
package bake.dropwizard.licence.person;

import bake.dropwizard.common.pojo.LicencePerson;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DAO {

    private static Integer id = 0;
    private List <LicencePerson> licencePersons = new ArrayList <LicencePerson> ();

    public Boolean isConnected () {
        return true;
    }

    List <LicencePerson> licencePersons () {
        return licencePersons;
    }

    List <LicencePerson> licencePersonsById (Integer id) {
        return licencePersons
            .stream ()
            .filter (cp -> cp.getId ().equals (id))
            .collect (Collectors.toList ());
    }

    List <LicencePerson> licencePersonsByLicenceId (Integer licenceId) {
        return licencePersons
            .stream ()
            .filter (cp -> cp.getLicenceId ().equals (licenceId))
            .collect (Collectors.toList ());
    }

    List <LicencePerson> licencePersonsByPersonId (Integer personId) {
        return licencePersons
            .stream ()
            .filter (cp -> cp.getPersonId ().equals (personId))
            .collect (Collectors.toList ());
    }

    List <LicencePerson> licencePersonsByLicenceIdPersonId (Integer licenceId, Integer personId) {
        return licencePersons
            .stream ()
            .filter (cp -> cp.getLicenceId ().equals (licenceId))
            .filter (cp -> cp.getPersonId ().equals (personId))
            .collect (Collectors.toList ());
    }

    LicencePerson licencePersonById (Integer id) {
        List <LicencePerson> licencePersons = licencePersonsById (id);
        return singleton (licencePersons);
    }

    LicencePerson licencePersonByLicenceIdPersonId (Integer licenceId, Integer personId) {
        List <LicencePerson> licencePersons = licencePersonsByLicenceIdPersonId (licenceId, personId);
        return singleton (licencePersons);
    }

    private static LicencePerson singleton (List <LicencePerson> licencePersons) {
        if (licencePersons.isEmpty ())
            throw new RuntimeException ("licence-person not found");
        else if (licencePersons.size () > 1)
            throw new RuntimeException ("licence-person not unique");
        else
            return licencePersons.get (0);
    }

    LicencePerson insertedLicencePerson (LicencePerson licencePerson) {
        Integer licenceId = licencePerson.getLicenceId ();
        Integer personId = licencePerson.getPersonId ();
        LicencePerson addee = new LicencePerson (++id, licenceId, personId);
        licencePersons.add (addee);
        return licencePersonById (addee.getId ());
    }

    LicencePerson updatedLicencePerson (LicencePerson licencePerson) {
        Integer id = licencePerson.getId ();
        Integer licenceId = licencePerson.getLicenceId ();
        Integer personId = licencePerson.getPersonId ();
        LicencePerson deletee = deletedLicencePerson (id);
        LicencePerson addee = new LicencePerson (id, licenceId, personId);
        licencePersons.remove (deletee);
        licencePersons.add (addee);
        return deletee;
    }

    LicencePerson deletedLicencePerson (Integer id) {
        LicencePerson deletee = licencePersonById (id);
        licencePersons.remove (deletee);
        return deletee;
    }

}
