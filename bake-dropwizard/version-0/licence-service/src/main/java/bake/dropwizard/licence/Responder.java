
package bake.dropwizard.licence;

import bake.dropwizard.common.pojo.Licence;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

public class Responder {

    private DAO dao;

    public Responder (DAO dao) {
        this.dao = dao;
    }

    Response licencesResponse () {
        return Response
            .ok (dao.licences ())
            .build ();
    }

    Response licencesByCompanyIdResponse (Integer companyId) {
        accept (companyId);
        return Response
            .ok (dao.licencesByCompanyId (companyId))
            .build ();
    }

    Response licenceByIdResponse (Integer id) {
        accept (id);
        return Response
            .ok (dao.licenceById (id))
            .build ();
    }

    Response createdLicence (Licence licence) {
        accept (licence);
        return Response
            .ok (dao.insertedLicence (licence))
            .build ();
    }

    private static void accept (Integer id) {
        if (
            id == null
            || id < 1
        ) throw new WebApplicationException (Status.BAD_REQUEST);
    }

    private static void accept (Licence licence) {
        if (
            licence.getId () != null
            || licence.getCompanyId () == null
        ) throw new WebApplicationException (Status.BAD_REQUEST);
    }

}
