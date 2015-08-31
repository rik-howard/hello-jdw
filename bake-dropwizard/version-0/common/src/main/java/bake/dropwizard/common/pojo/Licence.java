
package bake.dropwizard.common.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Licence {

    private Integer id;
    private Integer companyId;

    public Licence () {}

    public Licence (Integer id, Integer companyId) {
        this.id = id;
        this.companyId = companyId;
    }

    @JsonProperty
    public Integer getId () {
        return id;
    }

    @JsonProperty
    public Integer getCompanyId () {
        return companyId;
    }

    @Override
    public boolean equals (Object object) {
        if (object == null)
            return false;
        else if (this.getClass ().equals (object.getClass ())) {
            Licence that = (Licence) object;
            return
                this.id.equals (that.getId ())
                && this.companyId.equals (that.getCompanyId ());
        }
        else
            return false;
    }

    @Override
    public String toString () {
        String template = "Licence{id=%s;companyId=%s}";
        return String.format (template, id, companyId);
    }

}
