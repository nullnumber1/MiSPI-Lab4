package nullnumber1.service.impl;

import nullnumber1.entity.Point;
import nullnumber1.repository.PointRepository;
import nullnumber1.service.PointService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named("service.pointService")
@SessionScoped
public class PointServiceImpl implements PointService, Serializable {
    private MissPercentage missPercentage;
    private PointCounter pointCounter;

    @PostConstruct
    private void postConstruct() {
        missPercentage = MissPercentage.getInstance();
        pointCounter = PointCounter.getInstance();
    }

    @Inject
    @Named("repository.pointRepository")
    PointRepository pointRepository;

    @Override
    public Point addEntity(Point point) {
        pointCounter.check(point.getHit());
        missPercentage.getPercentage(pointCounter.getCount(), pointCounter.getHitCount());
        return pointRepository.addEntity(point);
    }

    @Override
    public List<Point> addEntityList(List<Point> list) {
        for (Point point:list) {
            pointCounter.check(point.getHit());
            missPercentage.getPercentage(pointCounter.getCount(), pointCounter.getHitCount());
        }
        return pointRepository.addEntityList(list);
    }

    @Override
    public List<Point> deleteSessionEntityList() {
        pointCounter.setCount(0);
        pointCounter.setHitCount(0);
        missPercentage.setMissRate(0);
        return pointRepository.deleteSessionEntityList();
    }
}

