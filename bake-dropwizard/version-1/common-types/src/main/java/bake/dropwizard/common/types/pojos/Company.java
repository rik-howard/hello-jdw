
package bake.dropwizard.common.types.pojos;

import bake.dropwizard.common.types.fields.Name;
import bake.dropwizard.common.types.fields.CompanyId;
import bake.dropwizard.common.types.stamps.Deleted;
import bake.dropwizard.common.types.stamps.Inserted;
import bake.dropwizard.common.types.stamps.Updated;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.joda.time.DateTime;

import java.io.Serializable;
import java.util.Objects;

public class Company
implements Serializable {

    private CompanyId id;
    private Name name;
    private Inserted inserted;
    private Updated updated;
    private Deleted deleted;

    public Company () {}

    public Company (
        CompanyId id,
        Name name,
        Inserted inserted,
        Updated updated,
        Deleted deleted
    ) {
        this.id = id;
        this.name = name;
        this.inserted = inserted;
        this.updated = updated;
        this.deleted = deleted;
    }

    public static Company company (
        Integer idInteger,
        String nameString,
        DateTime insertedDateTime,
        DateTime updatedDateTime,
        DateTime deletedDateTime
    ) {
        return new Company (
            idInteger == null? null: new CompanyId (idInteger),
            nameString == null? null: new Name (nameString),
            insertedDateTime == null? null: new Inserted (insertedDateTime),
            updatedDateTime == null? null: new Updated (updatedDateTime),
            deletedDateTime == null? null: new Deleted (deletedDateTime)
        );
    }

    @JsonProperty ("")
    public CompanyId getId () {
        return id;
    }

    @JsonProperty ("")
    public Name getName () {
        return name;
    }

    @JsonProperty ("")
    public Inserted getInserted () {
        return inserted;
    }

    @JsonProperty ("")
    public Updated getUpdated () {
        return updated;
    }

    @JsonProperty ("")
    public Deleted getDeleted () {
        return deleted;
    }

    @Override
    public int hashCode () {
        return Objects.hash (id, name, inserted, updated, deleted);
    }

    @Override
    public boolean equals (Object obj) {
        if (obj == null) return false;
        if (! getClass ().equals (obj.getClass ())) return false;
        Company company = (Company) obj;
        return Objects.equals (this.id, company.id)
        && Objects.equals (this.name, company.name)
        && Objects.equals (this.inserted, company.inserted)
        && Objects.equals (this.updated, company.updated)
        && Objects.equals (this.deleted, company.deleted);
    }

    @Override
    public String toString () {
        String template = "Company{id:%s;name:'%s';inserted:%s;updated:%s;deleted:%s}";
        return String.format (template, id, name, inserted, updated, deleted);
    }

    public Boolean canBeCreated () {
        return id == null
        && name != null
        && inserted == null
        && updated == null
        && deleted == null;
    }

    public Boolean canBeRecreated () {
        return id != null
        && name != null
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

    public Boolean queriesByName () {
        return id == null
        && name != null;
    }

}
