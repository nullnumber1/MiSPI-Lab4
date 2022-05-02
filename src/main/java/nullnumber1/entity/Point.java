package nullnumber1.entity;

import lombok.*;
import nullnumber1.dto.PointDTO;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Objects;

@Entity
@Table(name = "points")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Point {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "coordinate_x")
    private double x;
    @Column(name = "coordinate_y")
    private double y;
    @Column(name = "radius")
    private double r;
    @Column(name = "hit")
    private Boolean hit;
    @Column(name = "local_time")
    private LocalDateTime localDateTime;
    @Column(name = "execution_time")
    private Double scriptTimeSeconds;
    @Transient
    private Integer offset;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Point point = (Point) o;
        return id != null && Objects.equals(id, point.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public boolean calculate(double x, double y, double r) {
        if (x >= 0 && y <= 0) {
            return (x >= -r && y >= -r / 2);
        }
        if (x <= 0 && y >= 0) {
            return (Math.pow(x, 2) + Math.pow(y, 2) <= Math.pow(r / 2, 2));
        }
        if (x >= 0 && y >= 0) {
            return (r / 2 - x >= y);
        }
        return false;
    }

    public void build() {
        long startTime = System.nanoTime();

        if (localDateTime == null) {
            offset = offset == null ? 0 : offset;
            localDateTime = ZonedDateTime.now(ZoneOffset.UTC).plusMinutes(offset).toLocalDateTime();
        }
        if (hit == null) {
            this.hit = calculate(Point.this.x, Point.this.y, Point.this.r);
        }
        if (scriptTimeSeconds == null) {
            scriptTimeSeconds = ((double) System.nanoTime() - startTime) / Math.pow(10, 9);
        }
    }

    public static Point fromDto(PointDTO pointDTO) {
        Point point = new Point();
        point.setX(pointDTO.getX());
        point.setY(pointDTO.getY());
        point.setR(pointDTO.getR());
        point.setHit(pointDTO.getHit());
        point.setLocalDateTime(pointDTO.getLocalDateTime());
        point.setScriptTimeSeconds(pointDTO.getScriptTime());
        point.setOffset(pointDTO.getOffset());
        point.build();
        return point;
    }
}

