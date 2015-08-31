
package bake.dropwizard.person;

import bake.dropwizard.common.types.params.ForenameParam;
import bake.dropwizard.common.types.params.PersonIdParam;
import bake.dropwizard.common.types.params.SurnameParam;
import bake.dropwizard.common.types.pojos.Person;

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
    @Path ("/persons")
    @Produces (MediaType.APPLICATION_JSON)
    public Response getPersons (
        @QueryParam ("id") PersonIdParam idParam,
        @QueryParam ("forename") ForenameParam forenameParam,
        @QueryParam ("surname") SurnameParam surnameParam
    ) {
        List <Person> persons = director.gottenPersons (
            idParam == null? null: idParam.get (),
            forenameParam == null? null: forenameParam.get (),
            surnameParam == null? null: surnameParam.get ()
        );
        return Response.ok (persons).build ();
    }

    @POST
    @Path ("/person")
    @Consumes (MediaType.APPLICATION_JSON)
    @Produces (MediaType.APPLICATION_JSON)
    public Response postPerson (Person person) {
        Person postedPerson = director.postedPerson (person);
        return Response.ok (postedPerson).build ();
    }

    @PUT
    @Path ("/person")
    @Consumes (MediaType.APPLICATION_JSON)
    @Produces (MediaType.APPLICATION_JSON)
    public Response putPerson (Person person) {
        Person putPerson = director.putPerson (person);
        return Response.ok (putPerson).build ();
    }

    @DELETE
    @Path ("/person")
    @Produces (MediaType.APPLICATION_JSON)
    public Response deletePerson (
        @QueryParam ("id") PersonIdParam idParam
    ) {
        Person deletedPerson = director.deletedPerson (idParam == null? null: idParam.get ());
        return Response.ok (deletedPerson).build ();
    }

}
