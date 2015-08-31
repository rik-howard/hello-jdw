
package bake.dropwizard.company.person;

import bake.dropwizard.common.pojo.CompanyPerson;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path ("")
public class Resource {

    private Responder responder;

    public Resource (Responder responder) {
        this.responder = responder;
    }

    @GET
    @Path ("/company-persons")
    @Produces (MediaType.APPLICATION_JSON)
    public Response getCompanyPersons (
        @QueryParam ("id") Integer id,
        @QueryParam ("company-id") Integer companyId,
        @QueryParam ("person-id") Integer personId
    ) {
        CompanyPerson companyPerson = new CompanyPerson (id, companyId, personId);
        return responder.accessedCompanyPersons (companyPerson);
    }

    @GET
    @Path ("/company-person")
    @Produces (MediaType.APPLICATION_JSON)
    public Response getCompanyPerson (
            @QueryParam ("id") Integer id,
            @QueryParam ("company-id") Integer companyId,
            @QueryParam ("person-id") Integer personId
    ) {
        CompanyPerson companyPerson = new CompanyPerson (id, companyId, personId);
        return responder.accessedCompanyPerson (companyPerson);
    }

    /*@GET
    @Path ("/company-persons")
    @Produces (MediaType.APPLICATION_JSON)
    public Response getCompanyPersons () {
        return responder.companyPersonsResponse ();
    }

    @GET
    @Path ("/company-persons-by/company-id")
    @Produces (MediaType.APPLICATION_JSON)
    public Response getCompanyPersonsByCompanyId (@QueryParam ("companyId") Integer companyId) {
        return responder.companyPersonsByCompanyIdResponse (companyId);
    }

    @GET
    @Path ("/company-persons-by/person-id")
    @Produces (MediaType.APPLICATION_JSON)
    public Response getCompanyPersonsByPersonId (@QueryParam ("personId") Integer personId) {
        return responder.companyPersonsByPersonIdResponse (personId);
    }*/

    /*@GET
    @Path ("/company-person-by/id")
    @Produces (MediaType.APPLICATION_JSON)
    public Response getCompanyPerson (@QueryParam ("id") Integer id) {
        return responder.companyPersonByIdResponse (id);
    }

    @GET
    @Path ("/company-person-by/company-id/person-id")
    @Produces (MediaType.APPLICATION_JSON)
    public Response getCompanyPerson (@QueryParam ("companyId") Integer companyId, @QueryParam ("personId") Integer personId) {
        return responder.companyPersonByCompanyIdPersonIdResponse (companyId, personId);
    }*/

    @POST
    @Path ("/company-person")
    @Consumes (MediaType.APPLICATION_JSON)
    @Produces (MediaType.APPLICATION_JSON)
    public Response postCompanyPerson (CompanyPerson companyPerson) {
        return responder.createdCompanyPersonResponse (companyPerson);
    }

}
