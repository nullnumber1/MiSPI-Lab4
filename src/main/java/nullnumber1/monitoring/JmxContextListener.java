package nullnumber1.monitoring;

import lombok.extern.slf4j.Slf4j;
import nullnumber1.monitoring.impl.MissPercentage;
import nullnumber1.monitoring.impl.PointCounter;
import nullnumber1.service.impl.PointChecker;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.lang.management.ManagementFactory;

@Slf4j
public class JmxContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();

            ObjectName pointCheckerObjectName = new ObjectName("nullnumber1.service.impl:type=mbean,name=PointCounter");
            PointChecker pointChecker = new PointChecker();
            mBeanServer.registerMBean(pointChecker, pointCheckerObjectName);

            MXBeanNotificationListener pointCheckerListener = new MXBeanNotificationListener("pointChecker");
            mBeanServer.addNotificationListener(pointCheckerObjectName, pointCheckerListener, pointCheckerListener.getNotificationFilter(), null);
        } catch (Exception exception) {
            log.error("Error registering MBean: " + exception);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        try {
            MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();

            ObjectName pointCheckerObjectName = new ObjectName("nullnumber1.service.impl:type=mbean,name=PointCounter");
            mBeanServer.unregisterMBean(pointCheckerObjectName);

        } catch (Exception exception) {
            log.error("Error registering MBean: " + exception);
        }
    }
}
