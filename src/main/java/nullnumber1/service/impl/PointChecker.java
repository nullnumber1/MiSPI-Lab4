package nullnumber1.service.impl;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import nullnumber1.entity.Point;
import nullnumber1.monitoring.PointCheckerMXBean;
import nullnumber1.monitoring.impl.MissPercentage;
import nullnumber1.monitoring.impl.PointCounter;
import nullnumber1.repository.PointRepository;
import nullnumber1.service.PointService;

import javax.inject.Inject;
import javax.inject.Named;
import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;
import java.io.Serializable;
import java.util.List;

@Named("service.pointService")
@Slf4j
@Data
public class PointChecker extends NotificationBroadcasterSupport implements PointService, PointCheckerMXBean, Serializable {

    private int sequenceNumber = 0;
    private int count = 0;
    private int hitCount = 0;
    private double missRate = 0;

    @Inject
    @Named("repository.pointRepository")
    PointRepository pointRepository;

    @Override
    public Point addEntity(Point point) {
        check(point.getHit());
        calculatePercentage(count, hitCount);
        return pointRepository.addEntity(point);
    }

    @Override
    public List<Point> addEntityList(List<Point> list) {
        for (Point point:list) {
            check(point.getHit());
            calculatePercentage(count, hitCount);
        }
        return pointRepository.addEntityList(list);
    }

    @Override
    public List<Point> deleteSessionEntityList() {
        count = 0;
        hitCount = 0;
        missRate = 0;
        return pointRepository.deleteSessionEntityList();
    }

    @Override
    public void calculatePercentage(int count, int hit) {
        missRate = (double) (count - hit) / count * 100;
    }

    @Override
    public void check(boolean result) {
        count++;
        if (result) hitCount++;
        if (count % 10 == 0) {
            sendNotification(new Notification("multiplicity", this, sequenceNumber++, System.currentTimeMillis(), "The number of points is multiple of 10"));
        }
    }
}

