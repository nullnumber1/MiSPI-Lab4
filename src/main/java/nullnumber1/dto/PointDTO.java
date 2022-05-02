package nullnumber1.dto;

import lombok.Data;
import nullnumber1.entity.Point;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Data
public class PointDTO implements Serializable {

    private double x;
    private double y;
    private double r;
    private LocalDateTime localDateTime;
    private Boolean hit;
    private Double scriptTime;
    private Integer offset;

    public String getLocalDateTimeFormatted() {
        return localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PointDTO pointDTO = (PointDTO) o;
        return Double.compare(pointDTO.x, x) == 0 && Double.compare(pointDTO.y, y) == 0 && Double.compare(pointDTO.r, r) == 0
                && Objects.equals(localDateTime, pointDTO.localDateTime) && Objects.equals(hit, pointDTO.hit)
                && Objects.equals(scriptTime, pointDTO.scriptTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, r, localDateTime, hit, scriptTime);
    }

    public static PointDTO fromPoint(Point point) {
        PointDTO pointDTO = new PointDTO();
        pointDTO.setX(point.getX());
        pointDTO.setY(point.getY());
        pointDTO.setR(point.getR());
        pointDTO.setHit(point.getHit());
        pointDTO.setLocalDateTime(point.getLocalDateTime());
        pointDTO.setScriptTime(point.getScriptTimeSeconds());
        pointDTO.setOffset(point.getOffset());
        return pointDTO;
    }
}
