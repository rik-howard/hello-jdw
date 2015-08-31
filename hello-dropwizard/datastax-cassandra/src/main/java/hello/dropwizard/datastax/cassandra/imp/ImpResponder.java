
package hello.dropwizard.datastax.cassandra.imp;

import java.util.List;
import java.util.stream.Collectors;

public class ImpResponder {

    private ImpDAO impDAO;

    public  ImpResponder (ImpDAO impDAO) {
        this.impDAO = impDAO;
    }

    List <Imp> accessedImps () {
        return impDAO.selectedImps ();
    }

    List <Imp> accessedImps (Integer id) {
        return impDAO
            .selectedImps ()
            .stream ()
            .filter (imp -> imp.getId ().equals (id))
            .collect (Collectors.toList ());
    }

    List <Imp> accessedImps (String forename, String surname) {
        return impDAO
            .selectedImps ()
            .stream ()
            .filter (imp -> imp.getForename ().equals (forename))
            .filter (imp -> imp.getSurname ().equals (surname))
            .collect (Collectors.toList ());
    }

    List <Imp> accessedImpsByForename (String forename) {
        return impDAO
            .selectedImps ()
            .stream ()
            .filter (imp -> imp.getForename ().equals (forename))
            .collect (Collectors.toList ());
    }

    List <Imp> accessedImpsBySurname (String surname) {
        return impDAO
            .selectedImps ()
            .stream ()
            .filter (imp -> imp.getSurname ().equals (surname))
            .collect (Collectors.toList ());
    }

    Imp createdImp (Imp imp) {

    }

}
