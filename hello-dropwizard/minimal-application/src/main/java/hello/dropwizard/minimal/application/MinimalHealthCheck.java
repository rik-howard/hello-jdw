
package hello.dropwizard.minimal.application;

import com.codahale.metrics.health.HealthCheck;

public class MinimalHealthCheck
extends HealthCheck {

    @Override
    protected Result check () throws Exception {
        return Result.healthy ();
    }

}
