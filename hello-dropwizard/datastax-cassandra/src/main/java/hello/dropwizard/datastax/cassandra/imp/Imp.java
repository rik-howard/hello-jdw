
package hello.dropwizard.datastax.cassandra.imp;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Imp {

    Integer id;
    String forename;
    String surname;

    public Imp (Integer id, String forename, String surname) {
        this.id = id;
        this.forename = forename;
        this.surname = surname;
    }

    @JsonProperty
    public Integer getId () {
        return id;
    }

    @JsonProperty
    public String getForename () {
        return forename;
    }

    @JsonProperty
    public String getSurname () {
        return surname;
    }

    @Override
    public String toString () {
        String template = "Imp{id=%s;forname=%s;surname=%s}";
        return String.format (template, id, forename, surname);
    }

}
