package nullnumber1.monitoring.impl;

import lombok.extern.slf4j.Slf4j;
import nullnumber1.monitoring.PointCounterMXBean;

import javax.annotation.ManagedBean;
import javax.management.MBeanServer;
import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;
import javax.management.ObjectName;

@Slf4j
@ManagedBean
public class PointCounter extends NotificationBroadcasterSupport implements PointCounterMXBean {

    private MBeanServer mBeanServer;
    private ObjectName objectName;

    private int sequenceNumber = 0;
    private int count = 0;
    private int hitCount = 0;

    private static PointCounter pointCounter = new PointCounter();

    public static PointCounter getInstance(){
        return pointCounter;
    }

    public int getCount() {
        return count;
    }

    public int getHitCount() {
        return hitCount;
    }

    @Override
    public void check(boolean result) {
        count++;
        if (result) hitCount++;
        if (count % 10 == 0) {
            sendNotification(new Notification("multiplicity", this, sequenceNumber++, System.currentTimeMillis(), "The number of points is multiple of 10"));
        }
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setHitCount(int hitCount) {
        this.hitCount = hitCount;
    }

//    @PostConstruct
//    public void registerInJmx() {
//        log.info(this.getClass().getName() + " registered in JMX");
//        try {
//            objectName = new ObjectName("nullnumber1.service.impl:type=mbean,name=" + this.getClass().getName());
//            mBeanServer = ManagementFactory.getPlatformMBeanServer();
//            mBeanServer.registerMBean(this, objectName);
//        } catch (Exception exception) {
//            throw new IllegalStateException("Bean " + objectName + " can not be registered");
//        }
//    }
//
//    @PreDestroy
//    public void unregisterFromJmx() {
//        try {
//            mBeanServer.unregisterMBean(this.objectName);
//        } catch (Exception exception) {
//            throw new IllegalStateException("Bean " + objectName + " was unable to unregister from JMX");
//        }
//    }
}