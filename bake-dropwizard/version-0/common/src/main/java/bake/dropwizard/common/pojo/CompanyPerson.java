
package bake.dropwizard.common.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CompanyPerson {


    private Integer id;
    private Integer companyId;
    private Integer personId;

    public  CompanyPerson () {}

    public CompanyPerson (Integer id, Integer companyId, Integer personId) {
        this.id = id;
        this.companyId = companyId;
        this.personId = personId;
    }

    @JsonProperty
    public Integer getId () {
        return id;
    }

    @JsonProperty
    public Integer getCompanyId () {
        return companyId;
    }

    @JsonProperty
    public Integer getPersonId () {
        return  personId;
    }

    @Override
    public boolean equals (Object object) {
        if (object == null)
            return false;
        else if (this.getClass ().equals (object.getClass ())) {
            CompanyPerson that = (CompanyPerson) object;
            return
                this.id.equals (that.getId ())
                && this.companyId.equals (that.getCompanyId ())
                && this.personId.equals (that.getPersonId ());
        }
        else
            return false;
    }

    @Override
    public String toString () {
        String template = "CompanyPerson{id=%s;companyId=%s;personId=%s}";
        return String.format (template, id, companyId, personId);
    }

}
