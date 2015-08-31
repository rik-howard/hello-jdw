
package hello.dropwizard.getting.started;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class GSApplication
extends Application <GSConfiguration> {

    @Override 
    public String getName () {
        return super.getName ();
    }

    @Override 
    public void initialize (Bootstrap <GSConfiguration> bootstrap) {
        super.initialize (bootstrap);
    }

    @Override 
    public void run (GSConfiguration gsConfiguration, Environment environment) 
    throws Exception {
        final GSHealthCheck gsHealthCheck = new GSHealthCheck (gsConfiguration.getTemplate ());
        final GSResource gsResource = new GSResource (gsConfiguration.getTemplate (), gsConfiguration.getDefaultName ());
        environment.healthChecks ().register ("template", gsHealthCheck);
        environment.jersey ().register (gsResource);
    }

    public static void main (String [] args)
    throws Exception {
        new GSApplication ().run (args);
    }

}
