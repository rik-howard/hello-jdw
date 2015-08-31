
package bake.dropwizard.common.exceptions.dao;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class NonuniquenessExceptionTest {

    @Rule public ExpectedException thrown = ExpectedException.none ();

    @Test
    public void testException ()
    throws Exception {
        thrown.expect (NonuniquenessException.class);
        throw new NonuniquenessException ();
    }

}
