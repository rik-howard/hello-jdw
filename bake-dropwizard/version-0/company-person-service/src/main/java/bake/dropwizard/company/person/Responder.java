
package bake.dropwizard.company.person;

import bake.dropwizard.common.pojo.CompanyPerson;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.util.List;

public class Responder {

    @Valid @NotNull private DAO dao;

    public Responder (DAO dao) {
        this.dao = dao;
    }

    Response accessedCompanyPersons (CompanyPerson companyPerson) {
        if (accessesAll (companyPerson))
            return Response
                .ok (dao.companyPersons ())
                .build ();
        else if (accessesById (companyPerson))
            return Response
                .ok (dao.companyPersonsById (companyPerson.getId ()))
                .build ();
        else if (accessesByCompanyId (companyPerson))
            return Response
                .ok (dao.companyPersonsByCompanyId (companyPerson.getCompanyId ()))
                .build ();
        else if (accessesByPersonId (companyPerson))
            return Response
                .ok (dao.companyPersonsByPersonId (companyPerson.getPersonId ()))
                .build ();
        else if (accessesByCompanyIdPersonId (companyPerson))
            return Response
                .ok (dao.companyPersonsByCompanyIdPersonId (companyPerson.getCompanyId (), companyPerson.getPersonId ()))
                .build ();
        else
            throw new WebApplicationException (Status.NOT_ACCEPTABLE);
    }

    Response accessedCompanyPerson (CompanyPerson companyPerson) {
        if (accessesById (companyPerson))
            return Response
                .ok (dao.companyPersonById (companyPerson.getId ()))
                .build ();
        else if (accessesByCompanyIdPersonId (companyPerson))
            return Response
                .ok (dao.companyPersonsByCompanyIdPersonId (companyPerson.getCompanyId (), companyPerson.getPersonId ()))
                .build ();
        else
            throw new WebApplicationException (Status.NOT_ACCEPTABLE);
    }

    private static Boolean accessesAll (CompanyPerson companyPerson) {
        return companyPerson.getId () == null
        && companyPerson.getCompanyId () == null
        && companyPerson.getPersonId () == null;
    }

    private static Boolean accessesById (CompanyPerson companyPerson) {
        return companyPerson.getId () != null && companyPerson.getId () > 0;
    }

    private static Boolean accessesByCompanyId (CompanyPerson companyPerson) {
        return companyPerson.getId () == null
        && companyPerson.getCompanyId () != null && companyPerson.getCompanyId () > 0
        && companyPerson.getPersonId () == null;
    }

    private static Boolean accessesByPersonId (CompanyPerson companyPerson) {
        return companyPerson.getId () == null
        && companyPerson.getCompanyId () == null
        && companyPerson.getPersonId () != null && companyPerson.getPersonId () > 0;
    }

    private static Boolean accessesByCompanyIdPersonId (CompanyPerson companyPerson) {
        return companyPerson.getId () == null
        && companyPerson.getCompanyId () != null && companyPerson.getCompanyId () > 0
        && companyPerson.getPersonId () != null && companyPerson.getPersonId () > 0;
    }

    /*Response companyPersonsResponse () {
        return Response
            .ok (dao.companyPersons ())
            .build ();
    }

    Response companyPersonsByCompanyIdResponse (Integer companyId) {
        accept (companyId);
        return Response
            .ok (dao.companyPersonsByCompanyId (companyId))
            .build ();
    }

    Response companyPersonsByPersonIdResponse (Integer personId) {
        accept (personId);
        return Response
            .ok (dao.companyPersonsByPersonId (personId))
            .build ();
    }*/

    /*Response companyPersonByIdResponse (Integer id) {
        accept (id);
        return Response
            .ok (dao.companyPersonById (id))
            .build ();
    }

    Response companyPersonByCompanyIdPersonIdResponse (Integer companyId, Integer personId) {
        accept (companyId);
        accept (personId);
        return Response
            .ok (dao.companyPersonByCompanyIdPersonId (companyId, personId))
            .build ();
    }*/

    Response createdCompanyPersonResponse (CompanyPerson companyPerson) {
        accept (companyPerson);
        return Response
            .ok (dao.insertedCompanyPerson (companyPerson))
            .build ();
    }

    private static void accept (Integer id) {
        except (id == null || id < 1);
    }

    private static void accept (CompanyPerson companyPerson) {
        except (companyPerson.getId () != null);
        accept (companyPerson.getCompanyId ());
        accept (companyPerson.getPersonId ());
    }

    private static void except (Boolean exceptable) {
        if (exceptable)
            throw new WebApplicationException (Status.BAD_REQUEST);
    }

}
