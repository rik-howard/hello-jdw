
package hello.dropwizard.date.range;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.joda.time.DateTime;

public class Pojo {

    @JsonProperty private Integer id;
    @JsonProperty private Integer actorId;
    @JsonProperty private String stamp;
    @JsonProperty private String message;

    public Pojo () {}

    @JsonCreator
    public Pojo (
        @JsonProperty Integer id,
        @JsonProperty Integer actorId,
        @JsonProperty String stamp,
        @JsonProperty String message
    ) {
        this.id = id;
        this.actorId = actorId;
        this.stamp = stamp;
        this.message = message;
    }

    @JsonProperty
    public Integer getId () {
        return id;
    }

    @JsonProperty
    public Integer getActorId () {
        return actorId;
    }

    @JsonProperty
    public String getStamp () {
        return stamp;
    }

    @JsonProperty
    public String getMessage () {
        return message;
    }

    @Override
    public String toString () {
        String template = "Pojo{id=%s;actorId=%s;stamp=%s;message='%s'}";
        return String.format (template, id, actorId, stamp, message);
    }

}
