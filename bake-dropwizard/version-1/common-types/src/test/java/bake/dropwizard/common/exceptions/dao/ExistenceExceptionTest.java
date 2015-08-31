
package bake.dropwizard.common.exceptions.dao;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ExistenceExceptionTest {

    @Rule public ExpectedException thrown = ExpectedException.none ();

    @Test
    public void testException ()
    throws Exception {
        thrown.expect (ExistenceException.class);
        throw new ExistenceException ();
    }

}
