
package hello.dropwizard.common.types.params;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javax.ws.rs.WebApplicationException;

public class ForenameParamTest {

    ForenameParam testee;

    @Rule public ExpectedException thrown = ExpectedException.none ();

    @Test
    public void testConstructorNull ()
    throws Exception {
        thrown.expect (WebApplicationException.class);
        testee = new ForenameParam (null);
    }

    @Test
    public void testConstructorOkay ()
    throws Exception {
        testee = new ForenameParam ("");
    }

}
