package nullnumber1.service.impl;

import lombok.extern.slf4j.Slf4j;
import nullnumber1.entity.Point;
import nullnumber1.monitoring.impl.MissPercentage;
import nullnumber1.monitoring.impl.PointCounter;
import nullnumber1.repository.PointRepository;
import nullnumber1.service.PointService;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named("service.pointServiceImpl")
@Slf4j
public class PointServiceImpl implements PointService, Serializable {

    @Inject
    @Named("repository.pointRepository")
    PointRepository pointRepository;

    @Override
    public Point addEntity(Point point) {
        PointCounter.getInstance().check(point.getHit());
        log.info("The count of hits is {}", PointCounter.getInstance().getCount());
        MissPercentage.getInstance().calculatePercentage(PointCounter.getInstance().getCount(), PointCounter.getInstance().getHitCount());
        return pointRepository.addEntity(point);
    }

    @Override
    public List<Point> addEntityList(List<Point> list) {
        for (Point point : list) {
            PointCounter.getInstance().check(point.getHit());
            MissPercentage.getInstance().calculatePercentage(PointCounter.getInstance().getCount(), PointCounter.getInstance().getHitCount());
        }
        return pointRepository.addEntityList(list);
    }

    @Override
    public List<Point> deleteSessionEntityList() {
        PointCounter.getInstance().setCount(0);
        PointCounter.getInstance().setHitCount(0);
        MissPercentage.getInstance().setMissRate(0);
        return pointRepository.deleteSessionEntityList();
    }
}

