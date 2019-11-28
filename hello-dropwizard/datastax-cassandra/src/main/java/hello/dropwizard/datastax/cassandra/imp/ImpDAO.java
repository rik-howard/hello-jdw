
package hello.dropwizard.datastax.cassandra.imp;

import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

import java.util.List;
import java.util.stream.Collectors;

public class ImpDAO {

    public static final String SELECTION_STATEMENT = "select id, forename, surname from imps;";
    public static final String INSERTION_TEMPLATE = "insert into imps (id, forename, surname) values (%s, %s, %s)";
    public static final String UPDATING_TEMPLATE = "update imps set forename = %s, surname = %s where id = %s;";
    public static final String DELETION_TEMPLATE = "delete from imps where id = %s;";
    private static Integer id = 0;
    private Session session;

    public ImpDAO (Session session) {
        this.session = session;
    }

    public Boolean isConnected () {
        return session
            .execute ("select * from system.schema_keyspaces;")
            .all ()
            .size () > 0;
    }

    List <Imp> selectedImps () {
        return session
            .execute (SELECTION_STATEMENT)
            .all ()
            .stream ()
            .map (row ->
                new Imp (
                    row.getInt ("id"),
                    row.getString ("forename"),
                   row.getString ("surname")
                )
            )
            .collect (Collectors.toList ());
    }

    Imp insertedImp (Imp imp) {
        List <Imp> found = selectedImps ()
            .stream ()
            .filter (i -> i.getForename ().equals (i.getForename ()))
            .filter (i -> i.getSurname ().equals (i.getSurname ()))
            .collect (Collectors.toList ());
        if (found.size () > 0)
            throw new Exception ("existence");
        String statement = String
            .format (
                INSERTION_TEMPLATE,
                ++id,
                imp.getForename (),
                imp.getSurname ()
            );
        session.execute (statement);
        found = selectedImps ()
            .stream ()
            .filter (i -> i.getForename ().equals (i.getForename ()))
            .filter (i -> i.getSurname ().equals (i.getSurname ()))
            .collect (Collectors.toList ());
        if (found.size () == 0)
            throw new Exception ("insertion failed");
        else if (found.size () == 1)
            return found.get (0);
        else
            throw new Exception ("wrf?");
    }

    Imp updatedImp (Imp imp) {
        List <Imp> found = selectedImps ()
            .stream ()
            .filter (i -> i.getId ().equals (i.getId ()))
           .collect (Collectors.toList ());
        if (found.size () == 0)
            throw new Exception ("nonexistence");
        else if (found.size () > 1)
            throw new Exception ("nonuniquess");
        String statement = String
            .format (
                UPDATING_TEMPLATE,
                imp.getForename (),
                imp.getSurname (),
                imp.getId ()
            );
        session.execute (statement);
        return found.get (0);
    }

    Imp deletedImp (Imp imp) {
        List <Imp> found = selectedImps ()
            .stream ()
            .filter (i -> i.getId ().equals (i.getId ()))
            .collect (Collectors.toList ());
        if (found.size () == 0)
            throw new Exception ("nonexistence");
        else if (found.size () > 1)
            throw new Exception ("nonuniquess");
        String statement = String
            .format (DELETION_TEMPLATE, imp.getId ());
        session.execute (statement);
        return found.get (0);
    }

}
