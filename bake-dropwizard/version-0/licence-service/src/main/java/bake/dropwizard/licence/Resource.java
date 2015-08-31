
package bake.dropwizard.licence;

import bake.dropwizard.common.pojo.Licence;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path ("")
public class Resource {

    private Responder responder;

    public Resource (Responder responder) {
        this.responder = responder;
    }

    @GET
    @Path ("/licences")
    @Produces (MediaType.APPLICATION_JSON)
    public Response getLicences () {
        return responder.licencesResponse ();
    }

    @GET
    @Path ("/licences-by/company-id")
    @Produces (MediaType.APPLICATION_JSON)
    public Response getLicences (@QueryParam ("company-id") Integer companyId) {
        return responder.licencesByCompanyIdResponse (companyId);
    }

    @GET
    @Path ("/licence-by/id")
    @Produces (MediaType.APPLICATION_JSON)
    public Response getLicence (@QueryParam ("id") Integer id) {
        return responder.licenceByIdResponse (id);
    }

    @POST
    @Path ("/licence")
    @Consumes (MediaType.APPLICATION_JSON)
    @Produces (MediaType.APPLICATION_JSON)
    public Response postLicence (Licence licence) {
        return responder.createdLicence (licence);
    }

}
