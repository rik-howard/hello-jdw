
package hello.dropwizard.date.range;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import io.dropwizard.jersey.params.DateTimeParam;
import org.joda.time.DateTime;

@Path ("")  // This must be present.
public class Resource {

    private Responder responder;

    public Resource (Responder responder) {
        this.responder = responder;
    }

    @GET
    @Path ("/pojos")
    @Produces (MediaType.APPLICATION_JSON)
    public Response getPojos () {
        return responder.accessedPojosResponse ();
    }

    @GET
    @Path ("/pojos/by-range")
    @Produces (MediaType.APPLICATION_JSON)
    public Response getPojosByRange (
        @QueryParam ("beginning") DateTimeParam beginning,
        @QueryParam ("ending") DateTimeParam ending
    ) {
        return responder.accessedPojosByRangeResponse (beginning, ending);
    }

    @POST
    @Path ("/pojo")
    @Consumes (MediaType.APPLICATION_JSON)
    @Produces (MediaType.APPLICATION_JSON)
    public Response postPojo (Pojo pojo) {
        return responder.createdPojo (pojo);
    }

    @GET
    @Path ("/echo")
    @Produces (MediaType.APPLICATION_JSON)
    public Response echo (@QueryParam ("date-time-param") DateTimeParam dateTimeParam) {
        System.out.println (dateTimeParam);
        DateTime dateTime = DateTime.parse (dateTimeParam.toString ());
        System.out.println (dateTime);
        return Response.ok (dateTime).build ();
    }

}
