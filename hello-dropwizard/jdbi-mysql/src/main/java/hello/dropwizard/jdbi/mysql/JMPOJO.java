
package hello.dropwizard.jdbi.mysql;

public class JMPOJO {

    private Integer id;
    private String name;

    public JMPOJO () {}

    public JMPOJO (Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId () {
        return id;
    }

    public String getName () {
        return name;
    }

    public String toHTML () {
        return "<pre>"
            + "JMPOJO"
            + "\nid  : " + id
            + "\nname: " + name
            + "</pre>";
    }

}
