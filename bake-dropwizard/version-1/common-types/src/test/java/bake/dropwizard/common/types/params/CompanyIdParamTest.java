
package bake.dropwizard.common.types.params;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javax.ws.rs.WebApplicationException;

public class CompanyIdParamTest {

    CompanyIdParam testee;

    @Rule public ExpectedException thrown = ExpectedException.none ();

    @Test
    public void testConstructorNull ()
    throws Exception {
        thrown.expect (WebApplicationException.class);
        testee = new CompanyIdParam (null);
    }

    @Test
    public void testConstructorStringNoninteger ()
    throws Exception {
        thrown.expect (WebApplicationException.class);
        testee = new CompanyIdParam ("1.2");
    }

    @Test
    public void testConstructorStringNegativeInteger ()
    throws Exception {
        thrown.expect (WebApplicationException.class);
        testee = new CompanyIdParam ("-1");
    }

    @Test
    public void testConstructorOkay ()
    throws Exception {
        testee = new CompanyIdParam ("0");
    }

}
