package nullnumber1.repository.manager;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Named("repository.persistenceFactory")
@SessionScoped
@Slf4j
@Data
public class PersistenceFactory implements Serializable {

    private EntityManagerFactory emf;

    @PostConstruct
    public void init() {
        Map<String, String> result = new HashMap<>();
        result.put("javax.persistence.jdbc.user", System.getenv("DB_USERNAME"));
        result.put("javax.persistence.jdbc.password", System.getenv("DB_PASSWORD"));
        result.put("javax.persistence.jdbc.url", "jdbc:postgresql://" + System.getenv("DB_HOST") + ":" + System.getenv("DB_PORT") + "/" + System.getenv("DB_NAME"));
        try {
            emf = Persistence.createEntityManagerFactory("Points", result);
        } catch (PersistenceException persistenceException) {
            log.error("Failed to start connection. Check if database is running and credentials are correct");
            log.error(persistenceException.toString());
        }
    }
}
