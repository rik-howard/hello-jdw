
package bake.dropwizard.company;

import bake.dropwizard.common.types.params.NameParam;
import bake.dropwizard.common.types.params.CompanyIdParam;
import bake.dropwizard.common.types.params.SurnameParam;
import bake.dropwizard.common.types.pojos.Company;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path ("")
public class Resource {

    private Director director;

    public Resource (Director director) {
        this.director = director;
    }

    @GET
    @Path ("/companys")
    @Produces (MediaType.APPLICATION_JSON)
    public Response getCompanys (
        @QueryParam ("id") CompanyIdParam idParam,
        @QueryParam ("name") NameParam nameParam,
        @QueryParam ("surname") SurnameParam surnameParam
    ) {
        List <Company> companys = director.gottenCompanys (
            idParam == null? null: idParam.get (),
            nameParam == null? null: nameParam.get (),
            surnameParam == null? null: surnameParam.get ()
        );
        return Response.ok (companys).build ();
    }

    @POST
    @Path ("/company")
    @Consumes (MediaType.APPLICATION_JSON)
    @Produces (MediaType.APPLICATION_JSON)
    public Response postCompany (Company company) {
        Company postedCompany = director.postedCompany (company);
        return Response.ok (postedCompany).build ();
    }

    @PUT
    @Path ("/company")
    @Consumes (MediaType.APPLICATION_JSON)
    @Produces (MediaType.APPLICATION_JSON)
    public Response putCompany (Company company) {
        Company putCompany = director.putCompany (company);
        return Response.ok (putCompany).build ();
    }

    @DELETE
    @Path ("/company")
    @Produces (MediaType.APPLICATION_JSON)
    public Response deleteCompany (
        @QueryParam ("id") CompanyIdParam idParam
    ) {
        Company deletedCompany = director.deletedCompany (idParam == null? null: idParam.get ());
        return Response.ok (deletedCompany).build ();
    }

}
