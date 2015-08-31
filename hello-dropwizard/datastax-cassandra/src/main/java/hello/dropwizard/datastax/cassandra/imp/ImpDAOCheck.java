
package hello.dropwizard.datastax.cassandra.imp;

import com.codahale.metrics.health.HealthCheck;

public class ImpDAOCheck
extends HealthCheck {

    private final ImpDAO impDAO;

    public ImpDAOCheck (ImpDAO impDAO) {
        this.impDAO = impDAO;
    }

    @Override
    protected Result check ()
    throws Exception {
        return impDAO.isConnected ()?
            Result.healthy ():
        Result.unhealthy ("the Imp DAO is not connected");
    }

}
