
package hello.dropwizard.jdbi.mysql;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class JMPOJOMapper
implements ResultSetMapper <JMPOJO> {

    public JMPOJO map (int index, ResultSet resultSet, StatementContext statementContext)
    throws SQLException {
        return new JMPOJO (resultSet.getInt ("id"), resultSet.getString ("name"));
    }

}
