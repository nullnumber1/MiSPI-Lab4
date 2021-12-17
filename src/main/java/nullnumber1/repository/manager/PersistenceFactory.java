package nullnumber1.repository.manager;

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
public class PersistenceFactory implements Serializable {

    private EntityManagerFactory emf;

    @Inject
    private Credentials credentials;

    @PostConstruct
    public void init() {
        Map<String, String> result = new HashMap<>();
        result.put("javax.persistence.jdbc.user", credentials.getUsername());
        result.put("javax.persistence.jdbc.password", credentials.getPassword());
        try {
            emf = Persistence.createEntityManagerFactory("Points", result);
        } catch (Exception e) {
            log.info("Failed to start local machine settings\nsetting up parameters for helios");
            try {
                result.put("javax.persistence.jdbc.url", "jdbc:postgresql://pg:5432/studs");
                emf = Persistence.createEntityManagerFactory("Points", result);
            } catch (PersistenceException persistenceException) {
                log.error(persistenceException.toString());
            }
        }
    }

    public EntityManagerFactory getEntityManagerFactory() {
        return emf;
    }
}
