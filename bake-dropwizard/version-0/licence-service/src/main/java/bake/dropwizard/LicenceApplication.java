
package bake.dropwizard;

import bake.dropwizard.licence.health.check.DAOCheck;
import bake.dropwizard.licence.Resource;
import bake.dropwizard.licence.Responder;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class LicenceApplication
extends Application <LicenceConfiguration> {

    @Override
    public String getName () {
        return super.getName ();
    }

    @Override
    public void initialize (Bootstrap <LicenceConfiguration> bootstrap) {
        super.initialize (bootstrap);
    }

    @Override
    public void run (LicenceConfiguration configuration, Environment environment)
    throws Exception {

        final DAOCheck daoCheck = new DAOCheck (configuration.getDAO ());
        final Resource resource = new Resource (new Responder (configuration.getDAO ()));

        environment.healthChecks ().register ("daoCheck", daoCheck);
        environment.jersey ().register (resource);

    }

    public static void main (String [] args)
    throws Exception {
        new LicenceApplication ().run (args);
    }

}
