
package hello.dropwizard.datastax.cassandra;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import hello.dropwizard.datastax.cassandra.imp.ImpResource;
import hello.dropwizard.datastax.cassandra.imp.ImpDAOCheck;
import hello.dropwizard.datastax.cassandra.imp.ImpDAO;
import hello.dropwizard.datastax.cassandra.imp.ImpResponder;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import java.util.Map;

public class DCApplication
extends Application <DCConfiguration> {

    @Override
    public String getName () {
        return super.getName ();
    }

    @Override
    public void initialize (Bootstrap <DCConfiguration> bootstrap) {
        super.initialize (bootstrap);
    }

    @Override
    public void run (DCConfiguration dcConfiguration, Environment environment)
    throws Exception {
        final Session session = session (dcConfiguration.getDatabaseConfiguration ());
        final ImpDAO impDAO = new ImpDAO (session);
        final ImpDAOCheck impDAOCheck = new ImpDAOCheck (impDAO);
        final ImpResponder impResponder = new ImpResponder (impDAO);
        final ImpResource impResource = new ImpResource (impResponder);
        //
        environment.healthChecks ().register ("impDAO", impDAOCheck);
        environment.jersey ().register (impResource);
    }

    public static void main (String [] args)
    throws Exception {
        new DCApplication ().run (args);
    }

    private static Session session (Map <String, String> databaseConfiguration) {
        return Cluster
            .builder ()
            .addContactPoint (databaseConfiguration.get ("contactPoint"))
            .build ()
            .connect (databaseConfiguration.get ("connectee"));
    }

}
