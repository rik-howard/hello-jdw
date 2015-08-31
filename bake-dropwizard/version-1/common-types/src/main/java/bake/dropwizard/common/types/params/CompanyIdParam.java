
package bake.dropwizard.common.types.params;

import bake.dropwizard.common.types.fields.CompanyId;
import io.dropwizard.jersey.params.AbstractParam;

public class CompanyIdParam
extends AbstractParam <CompanyId> {

    public CompanyIdParam (String input) {
        super (input);
    }

    @Override
    protected CompanyId parse (String string)
    throws Exception {
        Integer value = Integer.parseInt (string);
        if (value < 0)
            throw new Exception ("negative integer");
        return new CompanyId (value);
    }

}
