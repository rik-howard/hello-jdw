
package hello.dropwizard.common.types.params;

import hello.dropwizard.common.types.fields.PersonId;
import io.dropwizard.jersey.params.AbstractParam;

public class PersonIdParam
extends AbstractParam <PersonId> {

    public PersonIdParam (String input) {
        super (input);
    }

    @Override
    protected PersonId parse (String s)
    throws Exception {
        Integer value = Integer.parseInt (s);
        if (value < 0)
            throw new Exception ("negative integer");
        return new PersonId (value);
    }

}
