
package hello.dropwizard.minimal.service;

import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;

public class MinimalService
extends Service <MinimalConfiguration> {

    @Override
    public void initialize (Bootstrap <MinimalConfiguration> bootstrap) {
    }

    @Override
    public void run (MinimalConfiguration minimalConfiguration, Environment environment)
    throws Exception {
        environment.addResource (new MinimalResource ());
    }

    public static void main (String [] args)
    throws Exception {
        new MinimalService ().run (args);
    }

}
