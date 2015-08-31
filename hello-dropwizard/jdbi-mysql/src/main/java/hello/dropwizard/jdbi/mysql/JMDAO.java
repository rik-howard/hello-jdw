
package hello.dropwizard.jdbi.mysql;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;
import java.util.List;

@RegisterMapper (JMPOJOMapper.class)
public interface JMDAO {

    @SqlUpdate ("insert into users (name) values (:name)")
    void insert (@Bind ("name") String name);

    @SqlQuery ("select id from users where name = :name")
    Integer findIdByName (@Bind ("name") String name);

    @SqlQuery ("select id, name from users where id = :id")
    JMPOJO findUserById (@Bind ("id") Integer id);

    @SqlUpdate ("delete from users where name = :name")
    void delete (@Bind ("name") String name);

    @SqlQuery ("select name from users")
    List <String> findNames ();

    @SqlQuery ("select id, name from users")
    List <JMPOJO> findUsers ();

    @SqlUpdate ("update users set name = :name where id = :id")
    void update (@Bind ("id") Integer id, @Bind ("name") String name);

    void close ();

}
