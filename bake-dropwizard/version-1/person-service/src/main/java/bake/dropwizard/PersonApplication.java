
package bake.dropwizard;

import bake.dropwizard.person.DAO;
import bake.dropwizard.person.Director;
import bake.dropwizard.person.Manager;
import bake.dropwizard.person.Resource;
import bake.dropwizard.person.Worker;
import bake.dropwizard.person.health.Check;
import bake.dropwizard.person.health.SessionCheck;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import java.util.Map;

public class PersonApplication
extends Application <PersonConfiguration> {

    @Override
    public String getName () {
        return super.getName ();
    }

    @Override
    public void initialize (Bootstrap <PersonConfiguration> bootstrap) {
        super.initialize (bootstrap);
    }

    @Override
    public void run (PersonConfiguration personConfiguration, Environment environment)
    throws Exception {
        environment.getObjectMapper ().disable (SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        environment.getObjectMapper ().disable (DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        final Session session = session (personConfiguration.getDaoConfiguration ());
        //
        final Check check = new Check ();
        final SessionCheck sessionCheck = new SessionCheck (session);
        environment.healthChecks ().register ("check", check);
        environment.healthChecks ().register ("sessionCheck", sessionCheck);
        final DAO dao = new DAO (session);
        final Worker worker = new Worker (dao);
        final Manager manager = new Manager (worker);
        final Director director = new Director (manager);
        final Resource resource = new Resource (director);
        environment.jersey ().register (resource);
    }

    public static void main (String [] args)
    throws Exception {
        new PersonApplication ().run (args);
    }

    private static Session session (Map <String, String> databaseConfiguration) {
        return Cluster
            .builder ()
            .addContactPoint (databaseConfiguration.get ("contactPoint"))
            .build ()
            .connect (databaseConfiguration.get ("keyspace"));
    }

}
