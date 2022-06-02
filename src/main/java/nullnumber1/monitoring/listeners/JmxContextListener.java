package nullnumber1.monitoring.listeners;

import lombok.extern.slf4j.Slf4j;
import nullnumber1.monitoring.impl.MissPercentage;
import nullnumber1.monitoring.impl.PointCounter;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.lang.management.ManagementFactory;

@Slf4j
public class JmxContextListener implements ServletContextListener {
    private static final String pointCounterName = "nullnumber1.service.impl:type=mbean,name=PointCounter";
    private static final String missPercentageName = "nullnumber1.service.impl:type=mbean,name=MissPercentage";

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();

            ObjectName pointCounterObjectName = new ObjectName(pointCounterName);
            PointCounter pointCounter = PointCounter.getInstance();
            mBeanServer.registerMBean(pointCounter, pointCounterObjectName);

            ObjectName missPercentageObjectName = new ObjectName(missPercentageName);
            MissPercentage missPercentage = MissPercentage.getInstance();
            mBeanServer.registerMBean(missPercentage, missPercentageObjectName);

            MXBeanNotificationListener pointCounterListener = new MXBeanNotificationListener("multiplicity");
            mBeanServer.addNotificationListener(pointCounterObjectName, pointCounterListener, pointCounterListener.getNotificationFilter(), null);
        } catch (Exception exception) {
            log.error("Error registering MBean: " + exception);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        try {
            MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();

            ObjectName pointCounterObjectName = new ObjectName(pointCounterName);
            mBeanServer.unregisterMBean(pointCounterObjectName);

            ObjectName missPercentageObjectName = new ObjectName(missPercentageName);
            mBeanServer.unregisterMBean(missPercentageObjectName);

        } catch (Exception exception) {
            log.error("Error registering MBean: " + exception);
        }
    }
}
