package nullnumber1.entity;

import lombok.*;
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

    public static Builder newBuilder() {
        return new Point().new Builder();
    }

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

    @Data
    public class Builder {
        private Builder() {
            // private constructor
        }

        public Builder setX(double x) {
            Point.this.x = x;
            return this;
        }

        public Builder setY(double y) {
            Point.this.y = y;
            return this;
        }

        public Builder setR(double r) {
            Point.this.r = r;
            return this;
        }

        public Builder setHit(Boolean hit) {
            Point.this.hit = hit;
            return this;
        }

        public Builder setLocalDateTime(LocalDateTime ldt) {
            Point.this.localDateTime = ldt;
            return this;
        }

        public Builder setScriptTime(Double scriptTime) {
            Point.this.scriptTimeSeconds = scriptTime;
            return this;
        }

        public Builder setOffset(Integer offset) {
            Point.this.offset = offset;
            return this;
        }

        private boolean calculate(double x, double y, double r) {
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

        public Point build() {
            long startTime = System.nanoTime();

            if (localDateTime == null) {
                offset = offset == null ? 0 : offset;
                localDateTime = ZonedDateTime.now(ZoneOffset.UTC).plusMinutes(offset).toLocalDateTime();
            }
            if (hit == null) {
                Point.this.hit = calculate(Point.this.x, Point.this.y, Point.this.r);
            }
            if (scriptTimeSeconds == null) {
                scriptTimeSeconds = ((double) System.nanoTime() - startTime) / Math.pow(10, 9);
            }
            return Point.this;
        }
    }
}
