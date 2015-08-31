
package bake.dropwizard.common.types.params;

import bake.dropwizard.common.types.fields.Forename;
import io.dropwizard.jersey.params.AbstractParam;

public class ForenameParam
extends AbstractParam <Forename> {

    public ForenameParam (String input) {
        super (input);
    }

    @Override
    protected Forename parse (String string)
    throws Exception {
        if (string == null)
            throw new Exception ("nullness");
        return new Forename (string);
    }

}
