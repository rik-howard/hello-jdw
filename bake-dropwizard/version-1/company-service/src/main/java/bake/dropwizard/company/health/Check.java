
package bake.dropwizard.company.health;

import com.codahale.metrics.health.HealthCheck;

public class Check
extends HealthCheck {

    @Override
    protected Result check ()
    throws Exception {
        return Result.healthy ();
    }

}
