
package hello.dropwizard.common.types.params;

import hello.dropwizard.common.types.fields.Forename;
import io.dropwizard.jersey.params.AbstractParam;

public class ForenameParam
extends AbstractParam <Forename> {

    public ForenameParam (String input) {
        super (input);
    }

    @Override
    protected Forename parse (String s)
    throws Exception {
        if (s == null)
            throw new Exception ("nullness");
        return new Forename (s);
    }

}
