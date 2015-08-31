
package hello.dropwizard.common.types.params;

import hello.dropwizard.common.types.fields.Surname;
import io.dropwizard.jersey.params.AbstractParam;

public class SurnameParam
extends AbstractParam <Surname> {

    public SurnameParam (String input) {
        super (input);
    }

    @Override
    protected Surname parse (String s)
    throws Exception {
        if (s == null)
            throw new Exception ("nullness");
        return new Surname (s);
    }

}
