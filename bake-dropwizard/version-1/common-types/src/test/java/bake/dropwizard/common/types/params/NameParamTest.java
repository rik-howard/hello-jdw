
package bake.dropwizard.common.types.params;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javax.ws.rs.WebApplicationException;

public class NameParamTest {

    NameParam testee;

    @Rule public ExpectedException thrown = ExpectedException.none ();

    @Test
    public void testConstructorNull ()
    throws Exception {
        thrown.expect (WebApplicationException.class);
        testee = new NameParam (null);
    }

    @Test
    public void testConstructorOkay ()
    throws Exception {
        testee = new NameParam ("");
    }

}
