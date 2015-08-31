
package hello.dropwizard.datastax.cassandra.imp;

import hello.dropwizard.datastax.cassandra.imp.Imp;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.function.Function;

@Path ("")
public class ImpResource {

    private ImpResponder responder;

    public ImpResource (ImpResponder responder) {
        this.responder = responder;
    }

    @GET
    @Path ("/imp")
    @Produces (MediaType.APPLICATION_JSON)
    public Response getImp () {
        Imp imp = new Imp (0, "anne", "donald");
        return Response.ok (imp).build ();
    }

    @GET
    @Path ("/imps")
    @Produces (MediaType.APPLICATION_JSON)
    public Response getImps (
        @QueryParam ("id") Integer id
    ) {
        Imp imp = new Imp (0, "anne", "donald");
        List <Imp> imps = imps (id, null, null);
        return Response.ok (imps).build ();
    }

    private List <Imp> imps (Integer id, String forename, String surname) {
        return
            id == null?
                forename == null?
                    surname == null?
                        responder.accessedImps ():
                    responder.accessedImpsBySurname (surname):
                surname == null?
                    responder.accessedImpsByForename (forename):
                responder.accessedImps (forename, surname):
            responder.accessedImps (id);
    }

    @POST
    @Path ("/imp")
    @Consumes (MediaType.APPLICATION_JSON)
    @Produces (MediaType.APPLICATION_JSON)
    public  Response postImp (Imp imp) {
        return null;
    }

    /*private Imp postedImp (Imp imp) {
        try {
            imp = responder.createdImp (imp);
        }
        catch ()
    }*/

}
