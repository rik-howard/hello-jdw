
package bake.dropwizard;

import bake.dropwizard.company.person.health.check.DAOCheck;
import bake.dropwizard.company.person.Resource;
import bake.dropwizard.company.person.Responder;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class CompanyPersonApplication
extends Application <CompanyPersonConfiguration> {

    @Override
    public String getName () {
        return super.getName ();
    }

    @Override
    public void initialize (Bootstrap <CompanyPersonConfiguration> bootstrap) {
        super.initialize (bootstrap);
    }

    @Override
    public void run (CompanyPersonConfiguration configuration, Environment environment)
    throws Exception {
        final DAOCheck daoCheck = new DAOCheck (configuration.getDAO ());
        final Resource resource = new Resource (new Responder (configuration.getDAO ()));
        environment.healthChecks ().register ("daoCheck", daoCheck);
        environment.jersey ().register (resource);
    }

    public static void main (String [] args)
    throws Exception {
        new CompanyPersonApplication ().run (args);
    }

}
