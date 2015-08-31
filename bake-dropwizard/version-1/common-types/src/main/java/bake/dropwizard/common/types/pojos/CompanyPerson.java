
package bake.dropwizard.common.types.pojos;

import bake.dropwizard.common.types.fields.CompanyId;
import bake.dropwizard.common.types.fields.CompanyPersonId;
import bake.dropwizard.common.types.fields.PersonId;
import bake.dropwizard.common.types.stamps.Deleted;
import bake.dropwizard.common.types.stamps.Inserted;
import bake.dropwizard.common.types.stamps.Updated;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.joda.time.DateTime;

import java.io.Serializable;
import java.util.Objects;

public class CompanyPerson
implements Serializable {

    private CompanyPersonId id;
    private CompanyId companyId;
    private PersonId personId;
    private Inserted inserted;
    private Updated updated;
    private Deleted deleted;

    public CompanyPerson () {}

    public CompanyPerson (
        CompanyPersonId id,
        CompanyId companyId,
        PersonId personId,
        Inserted inserted,
        Updated updated,
        Deleted deleted
    ) {
        this.id = id;
        this.companyId = companyId;
        this.personId = personId;
        this.inserted = inserted;
        this.updated = updated;
        this.deleted = deleted;
    }

    public static CompanyPerson companyPerson (
        Integer idInteger,
        Integer companyIdInteger,
        Integer personIdInteger,
        DateTime insertedDateTime,
        DateTime updatedDateTime,
        DateTime deletedDateTime
    ) {
        return new CompanyPerson (
            idInteger == null? null: new CompanyPersonId (idInteger),
            companyIdInteger == null? null: new CompanyId (companyIdInteger),
            personIdInteger == null? null: new PersonId (personIdInteger),
            insertedDateTime == null? null: new Inserted (insertedDateTime),
            updatedDateTime == null? null: new Updated (updatedDateTime),
            deletedDateTime == null? null: new Deleted (deletedDateTime)
        );
    }

    @JsonProperty ("id")
    public CompanyPersonId getId () {
        return id;
    }

    @JsonProperty ("company-id")
    public CompanyId getCompanyId () {
        return companyId;
    }

    @JsonProperty ("person-id")
    public PersonId getPersonId () {
        return personId;
    }

    @JsonProperty ("inserted")
    public Inserted getInserted () {
        return inserted;
    }

    @JsonProperty ("updated")
    public Updated getUpdated () {
        return updated;
    }

    @JsonProperty ("deleted")
    public Deleted getDeleted () {
        return deleted;
    }

    @Override
    public int hashCode () {
        return Objects.hash (id, companyId, personId, inserted, updated, deleted);
    }

    @Override
    public boolean equals (Object obj) {
        if (obj == null) return false;
        if (! getClass ().equals (obj.getClass ())) return false;
        CompanyPerson companyPerson = (CompanyPerson) obj;
        return Objects.equals (this.id, companyPerson.id)
        && Objects.equals (this.companyId, companyPerson.companyId)
        && Objects.equals (this.personId, companyPerson.personId)
        && Objects.equals (this.inserted, companyPerson.inserted)
        && Objects.equals (this.updated, companyPerson.updated)
        && Objects.equals (this.deleted, companyPerson.deleted);
    }

    @Override
    public String toString () {
        String template = "CompanyPerson{id:%s;companyId:%s;personId:%s;inserted:%s;updated:%s;deleted:%s}";
        return String.format (template, id, companyId, personId, inserted, updated, deleted);
    }

    public Boolean canBeCreated () {
        return id == null
        && companyId != null
        && personId != null
        && inserted == null
        && updated == null
        && deleted == null;
    }

    public Boolean canBeRecreated () {
        return id != null
        && companyId != null
        && personId != null
        && inserted != null
        // updated ==|!= null
        && deleted == null;
    }

    public Boolean canBeDecreated () {
        return id != null
        && deleted == null;
    }

    public Boolean queriesById () {
        return id != null;
    }

    public Boolean queriesByCompanyIdAndPersonId () {
        return id == null
        && companyId != null
        && personId != null;
    }

    public Boolean queriesByCompanyId () {
        return id == null
        && companyId != null;
    }

    public Boolean queriesByPersonId () {
        return id == null
        && personId != null;
    }

}
