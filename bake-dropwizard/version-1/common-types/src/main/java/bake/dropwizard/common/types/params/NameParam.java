
package bake.dropwizard.common.types.params;

import bake.dropwizard.common.types.fields.Name;
import io.dropwizard.jersey.params.AbstractParam;

public class NameParam
extends AbstractParam <Name> {

    public NameParam (String input) {
        super (input);
    }

    @Override
    protected Name parse (String string)
    throws Exception {
        if (string == null)
            throw new Exception ("nullness");
        return new Name (string);
    }

}
