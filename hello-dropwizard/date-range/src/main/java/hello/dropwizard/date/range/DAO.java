
package hello.dropwizard.date.range;

import io.dropwizard.jersey.params.DateTimeParam;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;

public class DAO {

    private static Integer id = 0;
    private List <Pojo> pojos = new ArrayList <Pojo> ();

    public Boolean isConnected () {
        return true;
    }

    List <Pojo> pojos () {
        return pojos;
    }

    List <Pojo> pojosById (Integer id) {
        return pojos
            .stream ()
            .filter (cp -> cp.getId ().equals (id))
            .collect (Collectors.toList ());
    }

    List <Pojo> pojosByActorIdStamp (Integer actorId, DateTime stamp) {
        return pojos
            .stream ()
            .filter (pojo -> pojo.getActorId ().equals (actorId))
            .filter (pojo -> DateTime.parse (pojo.getStamp ()).equals (stamp))
            .collect (Collectors.toList ());
    }

    List <Pojo> pojosFromBeginning (DateTime beginning) {
        return pojos
            .stream ()
            .filter (pojo -> isAfter (pojo, beginning))
            .collect (Collectors.toList ());
    }

    List <Pojo> pojosToEnding (DateTime ending) {
        return pojos
            .stream ()
            .filter (pojo -> isBefore (pojo, ending))
            .collect (Collectors.toList ());
    }

    List <Pojo> pojosByRange (DateTime beginning, DateTime ending) {
        return pojos
            .stream ()
            .filter (pojo -> isAfter (pojo, beginning))
            .filter (pojo -> isBefore (pojo, ending))
            .collect (Collectors.toList ());
    }

    private static Boolean isAfter (Pojo pojo, DateTime beginning) {
        return DateTime.parse (pojo.getStamp ()).isEqual (beginning)
                || DateTime.parse (pojo.getStamp ()).isAfter (beginning);
    }

    private static Boolean isBefore (Pojo pojo, DateTime ending) {
        return DateTime.parse (pojo.getStamp ()).isBefore (ending)
        && ! DateTime.parse (pojo.getStamp ()).isEqual (ending);
    }

    Pojo pojoById (Integer id) {
        List <Pojo> pojos = pojosById (id);
        throwNonexistence (pojos);
        throwNonuniqueness (pojos);
        return pojos.get (0);
    }

    Pojo insertedPojo (Pojo pojo) {
        Integer actorId = pojo.getActorId ();
        String stamp = pojo.getStamp ();
        String message = pojo.getMessage ();
        List <Pojo> pojosBy = pojosByActorIdStamp (actorId, DateTime.parse (stamp));
        throwExistence (pojosBy);
        Pojo addee = new Pojo (++id, actorId, stamp, message);
        pojos.add (addee);
        return pojoById (addee.getId ());
    }

    private static void throwExistence (List <Pojo> pojos) {
        if (! pojos.isEmpty ())
            throw new WebApplicationException (Status.CONFLICT);
    }

    private static void throwNonexistence (List <Pojo> pojos) {
        if (pojos.isEmpty ())
            throw new WebApplicationException (Status.CONFLICT);
    }

    private static void throwNonuniqueness (List <Pojo> pojos) {
        if (pojos.size () > 1)
            throw new WebApplicationException (Status.CONFLICT);
    }

}
