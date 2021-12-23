package nullnumber1.service.impl;

import nullnumber1.entity.Point;
import nullnumber1.repository.PointRepository;
import nullnumber1.service.PointService;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named("service.pointService")
@SessionScoped
public class PointServiceImpl implements PointService, Serializable {

    @Inject
    @Named("repository.pointRepository")
    PointRepository pointRepository;

    @Override
    public Point addEntity(Point point) {
        return pointRepository.addEntity(point);
    }

    @Override
    public List<Point> addEntityList(List<Point> list) {
        return pointRepository.addEntityList(list);
    }

    @Override
    public List<Point> deleteSessionEntityList() {
        return pointRepository.deleteSessionEntityList();
    }
}

