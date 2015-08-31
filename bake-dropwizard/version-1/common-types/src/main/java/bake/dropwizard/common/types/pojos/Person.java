
package bake.dropwizard.common.types.pojos;

import bake.dropwizard.common.types.fields.Forename;
import bake.dropwizard.common.types.fields.PersonId;
import bake.dropwizard.common.types.fields.Surname;
import bake.dropwizard.common.types.stamps.Deleted;
import bake.dropwizard.common.types.stamps.Inserted;
import bake.dropwizard.common.types.stamps.Updated;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.joda.time.DateTime;

import java.io.Serializable;
import java.util.Objects;

public class Person
implements Serializable {

    private PersonId id;
    private Forename forename;
    private Surname surname;
    private Inserted inserted;
    private Updated updated;
    private Deleted deleted;

    public Person () {}

    public Person (
        PersonId id,
        Forename forename,
        Surname surname,
        Inserted inserted,
        Updated updated,
        Deleted deleted
    ) {
        this.id = id;
        this.forename = forename;
        this.surname = surname;
        this.inserted = inserted;
        this.updated = updated;
        this.deleted = deleted;
    }

    public static Person person (
        Integer idInteger,
        String forenameString,
        String surnameString,
        DateTime insertedDateTime,
        DateTime updatedDateTime,
        DateTime deletedDateTime
    ) {
        return new Person (
            idInteger == null? null: new PersonId (idInteger),
            forenameString == null? null: new Forename (forenameString),
            surnameString == null? null: new Surname (surnameString),
            insertedDateTime == null? null: new Inserted (insertedDateTime),
            updatedDateTime == null? null: new Updated (updatedDateTime),
            deletedDateTime == null? null: new Deleted (deletedDateTime)
        );
    }

    @JsonProperty ("id")
    public PersonId getId () {
        return id;
    }

    @JsonProperty ("forename")
    public Forename getForename () {
        return forename;
    }

    @JsonProperty ("surname")
    public Surname getSurname () {
        return surname;
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
        return Objects.hash (id, forename, surname, inserted, updated, deleted);
    }

    @Override
    public boolean equals (Object obj) {
        if (obj == null) return false;
        if (! getClass ().equals (obj.getClass ())) return false;
        Person person = (Person) obj;
        return Objects.equals (this.id, person.id)
        && Objects.equals (this.forename, person.forename)
        && Objects.equals (this.surname, person.surname)
        && Objects.equals (this.inserted, person.inserted)
        && Objects.equals (this.updated, person.updated)
        && Objects.equals (this.deleted, person.deleted);
    }

    @Override
    public String toString () {
        String template = "Person{id:%s;forename:'%s';surname:'%s';inserted:%s;updated:%s;deleted:%s}";
        return String.format (template, id, forename, surname, inserted, updated, deleted);
    }

    public Boolean canBeCreated () {
        return id == null
        && forename != null
        && surname != null
        && inserted == null
        && updated == null
        && deleted == null;
    }

    public Boolean canBeRecreated () {
        return id != null
        && forename != null
        && surname != null
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

    public Boolean queriesByForenameAndSurname () {
        return id == null
        && forename != null
        && surname != null;
    }

    public Boolean queriesByForename () {
        return id == null
        && forename != null;
    }

    public Boolean queriesBySurname () {
        return id == null
        && surname != null;
    }

}
