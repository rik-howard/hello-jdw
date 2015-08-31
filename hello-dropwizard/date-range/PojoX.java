
package hello.dropwizard.date.range;

import org.joda.time.DateTime;

public class PojoX {

    private Integer id;
    private String name;
    private DateTime born;
    private DateTime died;

    public Pojo () {}

    public Pojo (
            Integer id,
            String name,
            DateTime creationDateTime,
            DateTime decreationDateTime
    ) {
        this.id = id;
        this.name = name;
        this.born = creationDateTime;
        this.died = decreationDateTime;
    }

    public Integer getId () {
        return id;
    }

    public String getName () {
        return name;
    }

    public DateTime getBorn () {
        return born;
    }

    public DateTime getDied () {
        return died;
    }

    @Override
    public String toString () {
        String template = "Pojo {id=%s,name=%s,born=%s,died=%s}";
        return String.format (template, id, name, born, died);
    }

}
