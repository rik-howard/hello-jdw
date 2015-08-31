
package hello.dropwizard.common.types.pojos;

import hello.dropwizard.common.types.fields.Forename;
import hello.dropwizard.common.types.fields.PersonId;
import hello.dropwizard.common.types.fields.Surname;

import java.util.Objects;

public class Person {

    private PersonId id;
    private Forename forename;
    private Surname surname;

    public Person (PersonId id, Forename forename, Surname surname) {
        this.id = id;
        this.forename = forename;
        this.surname = surname;
    }

    public PersonId getId () {
        return id;
    }

    public Forename getForename () {
        return forename;
    }

    public Surname getSurname () {
        return surname;
    }

    @Override
    public int hashCode () {
        return Objects.hash (id, forename, surname);
    }

    @Override
    public boolean equals (Object obj) {
        if (obj == null) return false;
        if (! getClass ().equals (obj.getClass ())) return false;
        Person person = (Person) obj;
        return Objects.equals (this.id, person.id)
        && Objects.equals (this.forename, person.forename)
        && Objects.equals (this.surname, person.surname);
    }

    @Override
    public String toString () {
        String template = "Person{id:%s;forename:'%s';surname:'%s'}";
        return String.format (template, id, forename, surname);
    }

    public static Person person (Integer id, String forename, String surname) {
        return new Person (
            new PersonId (id),
            new Forename (forename),
            new Surname (surname)
        );
    }

}
