
package bake.dropwizard.common.types.params;

import bake.dropwizard.common.types.fields.Surname;
import io.dropwizard.jersey.params.AbstractParam;

public class SurnameParam
extends AbstractParam <Surname> {

    public SurnameParam (String input) {
        super (input);
    }

    @Override
    protected Surname parse (String string)
    throws Exception {
        if (string == null)
            throw new Exception ("nullness");
        return new Surname (string);
    }

}
