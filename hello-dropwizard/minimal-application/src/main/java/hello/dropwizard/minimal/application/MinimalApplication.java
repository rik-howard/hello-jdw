
package hello.dropwizard.minimal.application;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class MinimalApplication
extends Application <MinimalConfiguration> {

    @Override
    public void initialize (Bootstrap<MinimalConfiguration> bootstrap) {}

    @Override
    public void run (MinimalConfiguration minimalConfiguration, Environment environment)
    throws Exception {
        environment.healthChecks ().register ("yamlName", new MinimalHealthCheck ());
        environment.jersey ().register (new MinimalResource ());
    }

    public static void main (String [] args)
    throws Exception {
        new MinimalApplication ().run (args);
    }

}
