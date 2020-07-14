package server.properties;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class DataProps {
    private final String url = "jdbc:mysql://localhost/govorunclinic?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private final String schemaName = "govorunclinic";
    private final String username =  "root";
    private final String password = "root";
    private final String driverClassName = "com.mysql.jdbc.Driver";
}
