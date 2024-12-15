package paulo.recrutamentointerno.domain.model.dataService;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@Component
public abstract class AbstractDAOConnection {

    @Getter
    @Setter
    @Value("${spring.datasource.url}")
    public   String url;

    @Getter
    @Setter
    @Value("${spring.datasource.username}")
    private String username;

    @Getter
    @Setter
    @Value("${spring.datasource.password}")
    private String password;

    @Getter
    @Setter
    Connection conn;

    @Getter
    @Setter
    Statement stmt;

    public void connection() {
        {
            try {
                setConn(DriverManager.getConnection(getUrl(), getUsername(), getPassword()));
                stmt = getConn().createStatement();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
    }

    public void closeConnection() {
        {
            try {
                getStmt().close();
                getConn().close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
