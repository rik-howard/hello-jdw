
package bake.dropwizard;

import bake.dropwizard.company.DAO;
import bake.dropwizard.company.Director;
import bake.dropwizard.company.Manager;
import bake.dropwizard.company.Resource;
import bake.dropwizard.company.Worker;
import bake.dropwizard.company.health.Check;
import bake.dropwizard.company.health.SessionCheck;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import java.util.Map;

public class CompanyApplication
extends Application <CompanyConfiguration> {

    @Override
    public String getName () {
        return super.getName ();
    }

    @Override
    public void initialize (Bootstrap <CompanyConfiguration> bootstrap) {
        super.initialize (bootstrap);
    }

    @Override
    public void run (CompanyConfiguration companyConfiguration, Environment environment)
    throws Exception {
        environment.getObjectMapper ().disable (SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        environment.getObjectMapper ().disable (DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        final Session session = session (companyConfiguration.getDaoConfiguration ());
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
        new CompanyApplication ().run (args);
    }

    private static Session session (Map <String, String> databaseConfiguration) {
        return Cluster
            .builder ()
            .addContactPoint (databaseConfiguration.get ("contactPoint"))
            .build ()
            .connect (databaseConfiguration.get ("keyspace"));
    }

}
