
package bake.dropwizard.common.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LicencePerson {

    private Integer id;
    private Integer licenceId;
    private Integer personId;

    public  LicencePerson () {}

    public LicencePerson (Integer id, Integer licenceId, Integer personId) {
        this.id = id;
        this.licenceId = licenceId;
        this.personId = personId;
    }

    @JsonProperty
    public Integer getId () {
        return id;
    }

    @JsonProperty
    public Integer getLicenceId () {
        return licenceId;
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
            LicencePerson that = (LicencePerson) object;
            return
                this.id.equals (that.getId ())
                && this.licenceId.equals (that.getLicenceId ())
                && this.personId.equals (that.getPersonId ());
        }
        else
            return false;
    }

    @Override
    public String toString () {
        String template = "LicencePerson{id=%s;licenceId=%s;personId=%s}";
        return String.format (template, id, licenceId, personId);
    }

}
