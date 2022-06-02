package nullnumber1.monitoring.impl;

import lombok.extern.slf4j.Slf4j;
import nullnumber1.monitoring.PointCounterMXBean;

import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;

@Slf4j
public class PointCounter extends NotificationBroadcasterSupport implements PointCounterMXBean {
    private int sequenceNumber = 0;
    private int count = 0;
    private int hitCount = 0;

    private static final PointCounter pointCounter = new PointCounter();

    public static PointCounter getInstance() {
        return pointCounter;
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public int getHitCount() {
        return hitCount;
    }

    @Override
    public void check(boolean result) {
        count++;
        if (result) hitCount++;
        if (count % 10 == 0) {
            sendNotification(new Notification("multiple_of_10", this, sequenceNumber++, System.currentTimeMillis(), "The number of points is " + count));
        }
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setHitCount(int hitCount) {
        this.hitCount = hitCount;
    }
}