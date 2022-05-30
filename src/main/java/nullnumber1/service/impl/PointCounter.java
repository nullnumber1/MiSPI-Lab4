package nullnumber1.service.impl;

import com.sun.org.glassfish.gmbal.ManagedOperation;
import nullnumber1.service.PointCounterMBean;

import javax.annotation.ManagedBean;
import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;

public class PointCounter extends NotificationBroadcasterSupport implements PointCounterMBean {
    private int count = 0;
    private int hitCount = 0;

    private static final PointCounter pointCounter = new PointCounter();

    public int getCount() {
        return count;
    }

    public int getHitCount() {
        return hitCount;
    }

    public static PointCounter getInstance() {
        return pointCounter;
    }

    @Override
    public void check(boolean result) {
        count++;
        if (result) hitCount++;
        if (count % 10 == 0) {
           // empty
        }
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setHitCount(int hitCount) {
        this.hitCount = hitCount;
    }
}