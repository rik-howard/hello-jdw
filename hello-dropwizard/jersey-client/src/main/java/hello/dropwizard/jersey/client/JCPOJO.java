
package hello.dropwizard.jersey.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

public class JCPOJO {

    private  Long id;
    @Length (max = 3) private String content;

    public JCPOJO () {}

    public JCPOJO (Long id, String content) {
        this.id = id;
        this.content = content;
    }

    @JsonProperty
    public Long getId () {
        return id;
    }

    @JsonProperty public String getContent () {
        return content;
    }

    @Override
    public String toString () {
        return "JCPOJO"
            + "\nid     : " + id
            + "\ncontent: " + content;
    }

}
