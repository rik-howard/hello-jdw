
package bake.dropwizard.common.types.params;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javax.ws.rs.WebApplicationException;

public class SurnameParamTest {

    SurnameParam testee;

    @Rule public ExpectedException thrown = ExpectedException.none ();

    @Test
    public void testConstructorNull ()
    throws Exception {
        thrown.expect (WebApplicationException.class);
        testee = new SurnameParam (null);
    }

    @Test
    public void testConstructorOkay ()
    throws Exception {
        testee = new SurnameParam ("");
    }

}
