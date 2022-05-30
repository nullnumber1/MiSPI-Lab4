package nullnumber1.monitoring.impl;

import lombok.extern.slf4j.Slf4j;
import nullnumber1.monitoring.MissPercentageMXBean;

import javax.annotation.ManagedBean;
import javax.management.MBeanServer;
import javax.management.ObjectName;

@Slf4j
@ManagedBean
public class MissPercentage implements MissPercentageMXBean {
    private MBeanServer mBeanServer;
    private ObjectName objectName;
    private double missRate;

    private static MissPercentage missPercentage = new MissPercentage();

    public static MissPercentage getInstance(){
        return missPercentage;
    }
    @Override
    public void calculatePercentage(int count, int hit) {
        missRate = (double) (count - hit) / count * 100;
    }

    public void setMissRate(double missRate) {
        this.missRate = missRate;
    }

    public double getMissRate() {
        return missRate;
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
