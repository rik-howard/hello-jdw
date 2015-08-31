
package bake.dropwizard.licence.person;

import bake.dropwizard.common.pojo.LicencePerson;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.logging.Logger;

@Path ("")
public class Resource {

    private Responder responder;

    public Resource (Responder responder) {
        this.responder = responder;
    }

    @GET
    @Path ("/licence-persons")
    @Produces (MediaType.APPLICATION_JSON)
    public Response getLicencePersons () {
        return  responder.licencePersonsResponse ();
    }

    @POST
    @Path ("/licence-person")
    @Consumes (MediaType.APPLICATION_JSON)
    @Produces (MediaType.APPLICATION_JSON)
    public Response postLicencePerson (LicencePerson licencePerson) {
        return responder.createdLicencePersonResponse (licencePerson);
    }

    @PUT
    @Path ("/licence-person")
    @Consumes (MediaType.APPLICATION_JSON)
    @Produces (MediaType.APPLICATION_JSON)
    public Response putLicencePerson (LicencePerson licencePerson) {
        return responder.recreatedLicencePersonResponse (licencePerson);
    }

    @DELETE
    @Path ("licence-person-by/id")
    @Produces (MediaType.APPLICATION_JSON)
    public Response deleteLicencePersonById (@QueryParam ("id") Integer id) {
        return responder.decreatedLicencePersonResponse (id);
    }

}
