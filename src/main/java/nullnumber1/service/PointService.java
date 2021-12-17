package nullnumber1.service;

import nullnumber1.entity.Point;

import java.util.List;

public interface PointService {

    Point addEntity(Point p);

    List<Point> addEntityList(List<Point> pList);

    List<Point> getSessionEntityList();

    List<Point> deleteSessionEntityList();

}
