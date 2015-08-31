
package bake.dropwizard.licence;

import bake.dropwizard.common.pojo.Licence;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;

public class DAO {

    private static Integer id = 0;
    private List <Licence> licences = new ArrayList <Licence> ();

    public Boolean isConnected () {
        return true;
    }

    List <Licence> licences () {
        return licences;
    }

    List <Licence> licencesById (Integer id) {
        return licences
            .stream ()
            .filter (it -> it.getId ().equals (id))
            .collect (Collectors.toList ());
    }

    List <Licence> licencesByCompanyId (Integer companyId) {
        return licences
            .stream ()
            .filter (it -> it.getCompanyId ().equals (companyId))
            .collect (Collectors.toList ());
    }

    Licence licenceById (Integer id) {
        List <Licence> licences = licencesById (id);
        if (licences.isEmpty ())
            throw new WebApplicationException (Status.EXPECTATION_FAILED);
        else
            return licences.get (0);
    }

    Licence insertedLicence (final Licence licence) {
        Licence addee = new Licence (++id, licence.getCompanyId ());
        licences.add (addee);
        return licenceById (addee.getId ());
    }

}
