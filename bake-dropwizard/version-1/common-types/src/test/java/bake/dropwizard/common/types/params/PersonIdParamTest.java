
package bake.dropwizard.common.types.params;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javax.ws.rs.WebApplicationException;

public class PersonIdParamTest {

    PersonIdParam testee;

    @Rule public ExpectedException thrown = ExpectedException.none ();

    @Test
    public void testConstructorNull ()
    throws Exception {
        thrown.expect (WebApplicationException.class);
        testee = new PersonIdParam (null);
    }

    @Test
    public void testConstructorStringNoninteger ()
    throws Exception {
        thrown.expect (WebApplicationException.class);
        testee = new PersonIdParam ("1.2");
    }

    @Test
    public void testConstructorStringNegativeInteger ()
    throws Exception {
        thrown.expect (WebApplicationException.class);
        testee = new PersonIdParam ("-1");
    }

    @Test
    public void testConstructorOkay ()
    throws Exception {
        testee = new PersonIdParam ("0");
    }

}
