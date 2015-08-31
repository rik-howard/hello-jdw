
package bake.dropwizard.licence.person;

import bake.dropwizard.common.pojo.CompanyPerson;
import bake.dropwizard.common.pojo.Licence;
import bake.dropwizard.common.pojo.LicencePerson;
import bake.dropwizard.common.client.LicenceClient;
import bake.dropwizard.common.client.CompanyPersonClient;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.util.List;

public class Responder {

    private DAO dao;
    private LicenceClient licenceClient;
    private CompanyPersonClient companyPersonClient;

    public Responder (DAO dao, LicenceClient licenceClient, CompanyPersonClient companyPersonClient) {
        this.dao = dao;
        this.licenceClient = licenceClient;
        this.companyPersonClient = companyPersonClient;
    }

    Response licencePersonsResponse () {
        return Response
            .ok (dao.licencePersons ())
            .build ();
    }

    Response createdLicencePersonResponse (LicencePerson licencePerson) {
        assert licencePerson != null: "never seen";
        confirmCreatability (licencePerson);
        confirmInsertability (dao, licencePerson);
        Licence licence = confirmedLicence (licenceClient, licencePerson);
        CompanyPerson companyPerson = confirmedCompanyPerson (companyPersonClient, licence, licencePerson);
        assert areValid (licencePerson, licence, companyPerson): "never seen";
        return Response
            .ok (dao.insertedLicencePerson (licencePerson))
            .build ();
    }

    private static void confirmCreatability (LicencePerson licencePerson) {
        Integer id = licencePerson.getId ();
        Integer licenceId = licencePerson.getLicenceId ();
        Integer personId = licencePerson.getPersonId ();
        if (id == null)
            if (licenceId == null || personId == null)
                throw  new WebApplicationException (Status.NOT_ACCEPTABLE);
            else if (licenceId < 1 || personId < 1)
                throw  new WebApplicationException (Status.NOT_ACCEPTABLE);
            else
                return;
        else
            throw  new WebApplicationException (Status.NOT_ACCEPTABLE);
    }

    private static void confirmInsertability (DAO dao, LicencePerson licencePerson) {
        Integer licenceId = licencePerson.getLicenceId ();
        Integer personId = licencePerson.getPersonId ();
        List <LicencePerson> licencePersons = dao.licencePersonsByLicenceIdPersonId (licenceId, personId);
        if (licencePersons.isEmpty ())
            return;
        else
            throw new WebApplicationException (Status.CONFLICT);
    }

    private static Licence confirmedLicence (LicenceClient client, LicencePerson licencePerson) {
        Integer licenceId = licencePerson.getLicenceId ();
        try {
            return client.licence (licenceId);
        }
        catch (ProcessingException e) {
            throw new WebApplicationException (Status.SERVICE_UNAVAILABLE);
        }
        catch (ClientErrorException e) {
            throw new WebApplicationException (Status.BAD_REQUEST);
        }
        catch (Exception e) {
            throw new WebApplicationException (Status.INTERNAL_SERVER_ERROR);
        }
    }

    private static CompanyPerson confirmedCompanyPerson (CompanyPersonClient client, Licence licence, LicencePerson licencePerson) {
        Integer companyId = licence.getCompanyId ();
        Integer personId = licencePerson.getPersonId ();
        try {
            return client.companyPerson (companyId, personId);
        }
        catch (ProcessingException e) {
            throw new WebApplicationException (Status.SERVICE_UNAVAILABLE);
        }
        catch (ClientErrorException e) {
            throw new WebApplicationException (Status.BAD_REQUEST);
        }
        catch (Exception e) {
            throw new WebApplicationException (Status.INTERNAL_SERVER_ERROR);
        }
    }

    private static Boolean areValid (LicencePerson licencePerson, Licence licence, CompanyPerson companyPerson) {
        return
            licence.getId ().equals (licencePerson.getLicenceId ())
            && companyPerson.getPersonId ().equals (licencePerson.getPersonId ())
            && companyPerson.getCompanyId ().equals (licence.getCompanyId ());
    }

    Response recreatedLicencePersonResponse (LicencePerson licencePerson) {
        assert licencePerson != null: "never seen";
        confirmRecreatability (licencePerson);
        throw  new WebApplicationException (Status.NOT_IMPLEMENTED);
    }

    private static void confirmRecreatability (LicencePerson licencePerson) {
        Integer id = licencePerson.getId ();
        Integer licenceId = licencePerson.getLicenceId ();
        Integer personId = licencePerson.getPersonId ();
        if (id == null || licenceId == null || personId == null)
            throw new WebApplicationException (Status.NOT_ACCEPTABLE);
        else if (licenceId < 1 || personId < 1)
            throw  new WebApplicationException (Status.NOT_ACCEPTABLE);
        else
            return;
    }

    private static void confirmUpdateability (DAO dao, LicencePerson licencePerson) {
        Integer id = licencePerson.getId ();
        Integer licenceId = licencePerson.getLicenceId ();
        Integer personId = licencePerson.getPersonId ();
        throw new WebApplicationException (Status.NOT_MODIFIED);
    }

    Response decreatedLicencePersonResponse (Integer id) {
        assert id != null: "never seen";
        confirmDecreatability (id);
        confirmDeleteability (dao, id);
        return Response
            .ok (dao.deletedLicencePerson (id))
            .build ();
    }

    private static void confirmDecreatability (Integer id) {
        if (id < 1)
            throw new WebApplicationException (Status.NOT_ACCEPTABLE);
        else
            return;
    }

    private static void confirmDeleteability (DAO dao, Integer id) {
        List <LicencePerson> licencePersons = dao.licencePersonsById (id);
        if (licencePersons.isEmpty ()) {
            throw  new WebApplicationException (Status.EXPECTATION_FAILED);
        }
        else if (licencePersons.size () > 1) {
            throw  new WebApplicationException (Status.CONFLICT);
        }
        else {
            return;
        }
    }

}
