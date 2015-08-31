
package hello.dropwizard.getting.started;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

public class GSPOJO {

    private  Long id;
    @Length (max = 3) private String content;

    public GSPOJO () {}

    public GSPOJO (Long id, String content) {
        this.id = id;
        this.content = content;
    }

    @JsonProperty public Long getId () {
        return id;
    }

    @JsonProperty public String getContent () {
        return content;
    }

}
