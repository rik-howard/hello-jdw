
package hello.dropwizard.date.range;

import io.dropwizard.jersey.params.DateTimeParam;
import org.joda.time.DateTime;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.util.Arrays;
import java.util.List;

public class Responder {

    private DAO dao;

    public Responder (DAO dao) {
        this.dao = dao;
    }

    Response accessedPojosResponse () {
        List<Pojo> pojos = dao.pojos ();
        return Response.ok (pojos).build ();
    }

    Response accessedPojosByRangeResponse (DateTimeParam beginning, DateTimeParam ending) {
        List <Pojo> pojos = Arrays.asList ();
        if (isFromBeginning (beginning, ending))
            pojos = dao.pojosFromBeginning (DateTime.parse (beginning.toString ()));
        else if (isToEnding (beginning, ending))
            pojos = dao.pojosToEnding (DateTime.parse (ending.toString ()));
        else if (isByRange (beginning, ending))
            pojos = dao.pojosByRange (DateTime.parse (beginning.toString ()), DateTime.parse (beginning.toString ()));
        else if (isForAll (beginning, ending))
            pojos = dao.pojos ();
        else
            throw new WebApplicationException (Response.Status.INTERNAL_SERVER_ERROR);
        return Response.ok (pojos).build ();
    }

    private static Boolean isFromBeginning (DateTimeParam beginning, DateTimeParam ending) {
        return beginning != null && ending == null;
    }

    private static Boolean isToEnding (DateTimeParam beginning, DateTimeParam ending) {
        return beginning == null && ending != null;
    }

    private static Boolean isByRange (DateTimeParam beginning, DateTimeParam ending) {
        return beginning != null && ending != null;
    }

    private static Boolean isForAll (DateTimeParam beginning, DateTimeParam ending) {
        return beginning == null && ending == null;
    }

    Response createdPojo (Pojo pojo) {
        accept (pojo);
        pojo = dao.insertedPojo (pojo);
        return Response.ok (pojo).build ();
    }

    private static void accept (Pojo pojo) {
        if (
            pojo.getId () != null
            || pojo.getActorId () == null
            || pojo.getStamp () == null
            || pojo.getActorId () < 1
            || pojo.getMessage () == null
        )
            throw  new WebApplicationException (Status.NOT_ACCEPTABLE);
    }

}
