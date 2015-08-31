
package bake.dropwizard.common.types.params;

import bake.dropwizard.common.types.fields.PersonId;
import io.dropwizard.jersey.params.AbstractParam;

public class PersonIdParam
extends AbstractParam <PersonId> {

    public PersonIdParam (String input) {
        super (input);
    }

    @Override
    protected PersonId parse (String string)
    throws Exception {
        Integer value = Integer.parseInt (string);
        if (value < 0)
            throw new Exception ("negative integer");
        return new PersonId (value);
    }

}
