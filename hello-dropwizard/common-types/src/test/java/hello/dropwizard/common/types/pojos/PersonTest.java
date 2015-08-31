
package hello.dropwizard.common.types.pojos;

import hello.dropwizard.common.types.fields.Forename;
import hello.dropwizard.common.types.fields.PersonId;
import hello.dropwizard.common.types.fields.Surname;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PersonTest {

    Person person0;
    Person person1;
    Person person2;

    @Before
    public void setUp ()
    throws Exception {
        person0 = Person.person (0, "f", "s");
        person1 = Person.person (0, "f", "s");
        person2 = Person.person (0, "f", "s");
    }

    @After
    public void tearDown ()
    throws Exception {}

    @Test
    public void testGetId ()
    throws Exception {
        PersonId expected = new PersonId (0);
        PersonId actual = person0.getId ();
        assert expected.equals (actual);
    }

    @Test
    public void testGetForename ()
    throws Exception {
        Forename expected = new Forename ("f");
        Forename actual = person0.getForename ();
        assert expected.equals (actual);
    }

    @Test
    public void testGetSurname ()
    throws Exception {
        Surname expected = new Surname ("s");
        Surname actual = person0.getSurname ();
        assert expected.equals (actual);
    }

    @Test
    public void testHashCode ()
    throws Exception {
        int expected = person0.hashCode ();
        int actual = person1.hashCode ();
        assert expected == actual;
    }

    @Test
    public void testEquals ()
    throws Exception {
        assert person0.equals (person0);
        assert person0.equals (person1);
        assert person1.equals (person0);
        assert person0.equals (person1);
        assert person1.equals (person2);
        assert person0.equals (person2);
    }

    @Test
    public void testToString ()
    throws Exception {
        String expected = "Person{id:0;forename:'f';surname:'s'}";
        String actual = person0.toString ();
        assert expected.equals (actual);
    }

}
