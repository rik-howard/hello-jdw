
package bake.dropwizard.common.types.params;

import bake.dropwizard.common.types.fields.CompanyPersonId;
import io.dropwizard.jersey.params.AbstractParam;

public class CompanyPersonIdParam
extends AbstractParam <CompanyPersonId> {

    public CompanyPersonIdParam (String input) {
        super (input);
    }

    @Override
    protected CompanyPersonId parse (String string)
    throws Exception {
        Integer value = Integer.parseInt (string);
        if (value < 0)
            throw new Exception ("negative integer");
        return new CompanyPersonId (value);
    }

}
