package nullnumber1.repository.impl;

import lombok.extern.slf4j.Slf4j;
import nullnumber1.entity.Point;
import nullnumber1.repository.PointRepository;
import nullnumber1.repository.manager.PersistenceFactory;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("repository.pointRepository")
@SessionScoped
@Slf4j
public class PointRepositoryImpl implements Serializable, PointRepository {

    @Inject
    @Named("repository.persistenceFactory")
    private PersistenceFactory persistenceFactory;

    @Override
    public Point addEntity(Point p) {
        EntityManager entityManager = persistenceFactory.getEntityManagerFactory().createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            entityManager.persist(p);
            entityTransaction.commit();

            entityManager.close();
            return p;
        } catch (Exception e) {
            try {
                log.error("in addEntity() – exception: {}", e.toString());
                entityTransaction.rollback();
            } catch (Exception ex) {
                log.error("in addEntity() – exception when tried to rollback: {}", e.toString());
            }
        }
        entityManager.close();
        return null;
    }

    @Override
    public List<Point> addEntityList(List<Point> pList) {
        List<Point> returnList = new ArrayList<>();
        for (Point p : pList) {
            Point ret = addEntity(p);
            if (ret != null) {
                returnList.add(ret);
            }
        }
        return returnList;
    }

    @Override
    public List<Point> getSessionEntityList() {
        EntityManager entityManager = persistenceFactory.getEntityManagerFactory().createEntityManager();
        List<Point> pList = null;
        try {
            pList = entityManager.createQuery("SELECT p FROM Point p", Point.class).getResultList();
        } catch (Exception ex) {
            log.error("in getSessionEntityList() – exception: {}", ex.toString());
            ex.printStackTrace();
        }
        entityManager.close();
        return pList;
    }

    @Override
    public List<Point> deleteSessionEntityList() {
        List<Point> pList = getSessionEntityList();

        if (pList != null) {
            EntityManager entityManager = persistenceFactory.getEntityManagerFactory().createEntityManager();
            EntityTransaction entityTransaction = entityManager.getTransaction();
            try {
                entityTransaction.begin();
                for (Point p : pList) {
                    entityManager.remove(entityManager.contains(p) ? p : entityManager.merge(p));
                }
                entityTransaction.commit();
            } catch (Exception ex) {
                try {
                    log.error("in deleteSessionEntityList() – exception: {}", ex.toString());
                    entityTransaction.rollback();
                } catch (Exception e) {
                    log.error("in deleteSessionEntityList() – exception when tried to rollback: {}", e.toString());
                }
            }
            entityManager.close();
        }
        return pList;
    }
}
